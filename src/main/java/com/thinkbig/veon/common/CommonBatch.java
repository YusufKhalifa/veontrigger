package com.thinkbig.veon.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkbig.veon.curation.L2_DPI_Processor;
import com.thinkbig.veon.dao.Kafka_L0_RECORD;
import com.thinkbig.veon.dao.L1_TriggerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Logger;

import java.io.IOException;

import static com.thinkbig.veon.lifecycle.StartUp.IGNITE_CACHE_OBJECT_L1_TRIGGERRECORD;
import static com.thinkbig.veon.util.IConstants.TOPIC_COMPARATOR_DPI;


public class CommonBatch {
    private static Logger logger = Logger.getLogger("CommonBatch");

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void process(ConsumerRecord consumerRecord) throws IOException {

        Kafka_L0_RECORD str = objectMapper.readValue(consumerRecord.value().toString(), Kafka_L0_RECORD.class);

        logger.info("RECIEVED L0 Record : " + str.toString());

        L1_TriggerRecord tr = new L1_TriggerRecord(str.id, str.srcId, str.payload, str.batchId);

        logger.info("RECIEVED L1 Record : " + tr.toString());

        IGNITE_CACHE_OBJECT_L1_TRIGGERRECORD.put(str.id, tr);

        if (tr.getSrcId().equals(TOPIC_COMPARATOR_DPI))
            L2_DPI_Processor.process(tr);

    }
}
