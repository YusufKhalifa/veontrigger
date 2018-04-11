package com.thinkbig.veon.util;


import java.util.List;

public interface IConstants {

    String CONFIG_FILE = "config.properties";

    PropertyHandler instance = PropertyHandler.getInstance(CONFIG_FILE);

    String IGNITE_CLUSTER_NAME = instance.getValue("ignite.cluster.name");
    String TOPIC_COMPARATOR_DPI = instance.getValue("topic.comparator.dpi");
    int THREADS_MAX_STARTUP_EXECUTOR = Integer.parseInt(instance.getValue("threads.max.startup.executors"));
    String IGNITE_CACHE_OBJECT_L3_T03_AGGREGATION = instance.getValue("ignite.cache.trigger.l3.t03.aggregation");
    String IGNITE_CACHE_OBJECT_L3_T04_AGGREGATION = instance.getValue("ignite.cache.trigger.l3.t04.aggregation");
    String IGNITE_CACHE_OBJECT_L3_T05_AGGREGATION = instance.getValue("ignite.cache.trigger.l3.t05.aggregation");
    String IGNITE_CACHE_OBJECT_L3_T06_AGGREGATION = instance.getValue("ignite.cache.trigger.l3.t06.aggregation");
    List<String> INGESTION_KAFKA_CONSUMER_TOPICS = instance.getValue("ingestion.kafka.consumer.topics", ",");
    Integer INGESTION_KAFKA_CONSUMER_THREADS = Integer.parseInt(instance.getValue("ingestion.kafka.consumer.threads"));

    String INGESTION_KAFKA_CONSUMER_BROKERS = instance.getValue("ingestion.kafka.consumer.brokers");
    String INGESTION_KAFKA_CONSUMER_GROUP_ID = instance.getValue("ingestion.kafka.consumer.groupId");
    Integer INGESTION_KAFKA_CONSUMER_ARRAY_BLOCKING_CAPACITY = Integer.parseInt(instance.getValue("ingestion.kafka.consumer.array.blocking.capacity"));
    Integer INGESTION_KAFKA_CONSUMER_ARRAY_POLL_TIMEOUT = Integer.parseInt(instance.getValue("ingestion.kafka.consumer.poll.timeout"));
    Long INGESTION_KAFKA_CONSUMER_KEEP_ALIVE_TIME = Long.parseLong(instance.getValue("ingestion.kafka.consumer.keep.alive.time"));
    String INGESTION_KAFKA_CONSUMER_ENABLE_AUTO_COMMIT = instance.getValue("ingestion.kafka.consumer.enable.auto.commit");
    String INGESTION_KAFKA_CONSUMER_AUTO_COMMIT_INTERVAL_MS = instance.getValue("ingestion.kafka.consumer.auto.commit.interval.ms");
    String INGESTION_KAFKA_CONSUMER_SESSION_TIMEOUT_MS = instance.getValue("ingestion.kafka.consumer.session.timeout.ms");
    String INGESTION_KAFKA_CONSUMER_AUTO_OFFSET_RESET = instance.getValue("ingestion.kafka.consumer.auto.offset.reset");
    String INGESTION_KAFKA_CONSUMER_KEY_DESERIALIZER = instance.getValue("ingestion.kafka.consumer.key.deserializer");
    String INGESTION_KAFKA_CONSUMER_VALUE_DESERIALIZER = instance.getValue("ingestion.kafka.consumer.value.deserializer");
    String INGESTION_KAFKA_CONSUMER_KERBEROS_SERVICE_NAME = instance.getValue("ingestion.kafka.kerberos.service.name");
    String INGESTION_KAFKA_CONSUMER_SECURITY_PROTOCOL = instance.getValue("ingestion.kafka.security.protocol");
    Boolean INGESTION_KAFKA_CONSUMER_KERBEROS_ENABLED = Boolean.parseBoolean(instance.getValue("ingestion.kafka.sasl.kerberos.enabled"));
    String IGNITE_CACHE_NAME_L1_TRIGGERRECORD = instance.getValue("ignite.cache.trigger.l1.triggerrecord");

    String IGNITE_CACHE_L2_DPI = instance.getValue("ignite.cache.trigger.l2.dpi");


    String SERVICE_KAFKA_PRODUCER_BROKERS = instance.getValue("service.kafka.producer.brokers");
    List<String> SERVICE_KAFKA_PRODUCER_TOPICS= instance.getValue("service.kafka.producer.topics", ",");
    String SERVICE_KAFKA_PRODUCER_GROUP_ID= instance.getValue("service.kafka.producer.groupId");
    String SERVICE_KAFKA_PRODUCER_ACKS= instance.getValue("service.kafka.producer.acks");
    Integer SERVICE_KAFKA_PRODUCER_RETRIES= Integer.parseInt(instance.getValue("service.kafka.producer.retries"));
    Integer SERVICE_KAFKA_PRODUCER_BATCH_SIZE= Integer.parseInt(instance.getValue("service.kafka.producer.batch.size"));
    Integer SERVICE_KAFKA_PRODUCER_LINGER_MS= Integer.parseInt(instance.getValue("service.kafka.producer.linger.ms"));
    Integer SERVICE_KAFKA_PRODUCER_BUFFER_MEMORY= Integer.parseInt(instance.getValue("service.kafka.producer.buffer.memory"));
    String SERVICE_KAFKA_PRODUCER_KEY_SERIALIZER= instance.getValue("service.kafka.producer.key.serializer");
    String SERVICE_KAFKA_PRODUCER_VALUE_SERIALIZER= instance.getValue("service.kafka.producer.value.serializer");
    Boolean SERVICE_KAFKA_PRODUCER_KERBEROS_ENABLED= Boolean.parseBoolean(instance.getValue("service.kafka.kerberos.enabled"));
    String SERVICE_KAFKA_PRODUCER_SECURITY_PROTOCOL = instance.getValue("service.kafka.security.protocol");
    String SERVICE_KAFKA_PRODUCER_KERBEROS_SERVICE_NAME = instance.getValue("service.kafka.sasl.kerberos.service.name");


}
