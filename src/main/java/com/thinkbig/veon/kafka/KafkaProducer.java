package com.thinkbig.veon.kafka;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkbig.veon.dao.L4_NotificationRecord;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;


import java.util.Properties;

import static com.thinkbig.veon.util.IConstants.*;

public class KafkaProducer {
    private static Logger logger = Logger.getLogger("KafkaProducer");

    public static Producer<String, String> producer;

    public KafkaProducer() {
        Properties props = createProducerConfig();
        producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props);
    }


    private static Properties createProducerConfig() {
        Properties props = new Properties();
        props.put("bootstrap.servers", SERVICE_KAFKA_PRODUCER_BROKERS);
        props.put("acks", SERVICE_KAFKA_PRODUCER_ACKS);
        props.put("retries", SERVICE_KAFKA_PRODUCER_RETRIES);
        props.put("batch.size", SERVICE_KAFKA_PRODUCER_BATCH_SIZE);
        props.put("linger.ms", SERVICE_KAFKA_PRODUCER_LINGER_MS);
        props.put("buffer.memory", SERVICE_KAFKA_PRODUCER_BUFFER_MEMORY);
        props.put("key.serializer", SERVICE_KAFKA_PRODUCER_KEY_SERIALIZER);
        props.put("value.serializer", SERVICE_KAFKA_PRODUCER_VALUE_SERIALIZER);
        // Kerberos is enabled, get the required configs
        if(SERVICE_KAFKA_PRODUCER_KERBEROS_ENABLED)
            props.put("sasl.kerberos.service.name", SERVICE_KAFKA_PRODUCER_KERBEROS_SERVICE_NAME);

        props.put("security.protocol", SERVICE_KAFKA_PRODUCER_SECURITY_PROTOCOL);
        return props;
    }


    public static void sendMessage(L4_NotificationRecord notif){

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(mapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));



        //Notification Object to JSON in String
        String notifJSON = null;

        try {
            notifJSON = mapper.writeValueAsString(notif);

            // Making notifJSON effectively final for access from within inner class.
            String finalNotifJSON = notifJSON;

            // send
            producer.send(new ProducerRecord<>(SERVICE_KAFKA_PRODUCER_TOPICS.get(0), notif.getId(), notifJSON), (metadata, e) -> {
                if (e != null) {
                    logger.error(e.toString());
                    e.printStackTrace();

                }
                logger.info("L5_KafkaJSONRecord [module=Service, partition="+ metadata.partition()
                        + ", offset="+ metadata.offset() +", NotifID=" + notif.getId() + ", topic="
                        + SERVICE_KAFKA_PRODUCER_TOPICS.get(0) + ", JSONPayload:"+ finalNotifJSON +"]");

            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }



    }

    public static void stop(){
        producer.flush();
        producer.close();
    }

}
