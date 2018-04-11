package com.thinkbig.veon.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Logger;
import com.thinkbig.veon.common.CommonBatch;

import java.io.IOException;
import java.util.concurrent.Callable;

import static com.thinkbig.veon.util.IConstants.TOPIC_COMPARATOR_DPI;

public class ConsumerThreadHandler implements Runnable {

    private final Logger logger = Logger.getLogger(this.getClass());

    /** JSON Record */
    private ConsumerRecord record;

    /** Topic from Kafka Channel Topic*/
    private String topic;

    /** Object Mapper instance */
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public ConsumerThreadHandler(ConsumerRecord record, String src_topic) {
        logger.info("GOT CALLED : " + record + " : " + src_topic);
        this.record = record;
        this.topic = src_topic.replaceAll("[^a-zA-Z_]","");
    }

    @Override
    public void run() {
        try{
            logger.info("TOPIC NAME : " + topic);
            if(topic.equals(TOPIC_COMPARATOR_DPI))
                CommonBatch.process(record);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
