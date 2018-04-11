package com.thinkbig.veon.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.log4j.Logger;

import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.thinkbig.veon.util.IConstants.*;

public class KafkaIngestionConsumer {

    private static final Logger logger = Logger.getLogger(KafkaIngestionConsumer.class);

    /** Declare general consumer object,
     * second parameter will be inferred during initialization */
    private static KafkaConsumer<String, String> consumer;

    /** Threadpool of consumers*/
    private static ExecutorService executor;



    /** Start */
    public KafkaIngestionConsumer() {
        logger.info("Instantiating Ingestion Consumer .. ");

        Properties props = createConsumerConfig();
        // Create Consumer
        consumer = new KafkaConsumer<>(props);

        // Subscribe to topics list
        consumer.subscribe(INGESTION_KAFKA_CONSUMER_TOPICS);

        for (String topic: INGESTION_KAFKA_CONSUMER_TOPICS) {
            logger.info("Topics" + topic);

        }

        logger.info("Created Trigger Consumer for topic : " + INGESTION_KAFKA_CONSUMER_TOPICS.toString());

    }


    /**
     * Creates a {@link ThreadPoolExecutor} with a given number of threads to consume the messages
     * from the producer broker.
     *
     */
    public static void start() {

        // Eg. start with a threadpool of X, and blocking queue size Y
        // hold read from producer submitted tasks.
        System.out.println("from start consumer");
        executor = new ThreadPoolExecutor(
                INGESTION_KAFKA_CONSUMER_THREADS,
                INGESTION_KAFKA_CONSUMER_THREADS,
                INGESTION_KAFKA_CONSUMER_KEEP_ALIVE_TIME,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(INGESTION_KAFKA_CONSUMER_ARRAY_BLOCKING_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );


        while (true) {


            ConsumerRecords<String, String> records = consumer.poll(INGESTION_KAFKA_CONSUMER_ARRAY_POLL_TIMEOUT);
            for (final ConsumerRecord record : records){
                logger.info("recieved record : " + record.toString());
                executor.submit(new ConsumerThreadHandler(record, record.topic()));
            }

        }
    }






    /**
     * Sets Kafka Consumer Properties.
     * @return Properties props
     */
    private static Properties createConsumerConfig() {
        Properties props = new Properties();
        props.put("bootstrap.servers", INGESTION_KAFKA_CONSUMER_BROKERS);
        props.put("group.id", INGESTION_KAFKA_CONSUMER_GROUP_ID);
        props.put("enable.auto.commit", INGESTION_KAFKA_CONSUMER_ENABLE_AUTO_COMMIT);
        props.put("auto.commit.interval.ms", INGESTION_KAFKA_CONSUMER_AUTO_COMMIT_INTERVAL_MS);
        props.put("session.timeout.ms", INGESTION_KAFKA_CONSUMER_SESSION_TIMEOUT_MS);
        props.put("auto.offset.reset", INGESTION_KAFKA_CONSUMER_AUTO_OFFSET_RESET); // TODO: change back to "earliest"
        props.put("key.deserializer", INGESTION_KAFKA_CONSUMER_KEY_DESERIALIZER);
        props.put("value.deserializer", INGESTION_KAFKA_CONSUMER_VALUE_DESERIALIZER);

        // Kerberos is enabled, get the required configs
        if(INGESTION_KAFKA_CONSUMER_KERBEROS_ENABLED)
            props.put("sasl.kerberos.service.name", INGESTION_KAFKA_CONSUMER_KERBEROS_SERVICE_NAME);


        return props;
    }


    /**
     * Not Required, as the Kafka Consumers are supposed to run forever
     *  Included here for completeness
     */

    public static void shutdown() {
        if (consumer != null) {
            consumer.close();
        }
        if (executor != null) {
            executor.shutdown();
        }
        try {
            if (!executor.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
                logger.error("Timed out waiting for consumer threads to shut down, exiting uncleanly");
            }
        } catch (InterruptedException e) {
            logger.error("Interrupted during shutdown, exiting uncleanly");
        }
    }
}
