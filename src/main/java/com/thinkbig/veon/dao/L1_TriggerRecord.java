package com.thinkbig.veon.dao;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class L1_TriggerRecord implements Serializable {

    /** L1_TriggerRecord ID (indexed). */
    @QuerySqlField(index = true)
    public String id;

    /** Source ID (indexed) / TOPIC for Kafka. */
    @QuerySqlField(index = true)
    public String srcId;

    /** Payload (not-indexed). */
    @QuerySqlField
    public String payload;

    public String getId() {
        return id;
    }

    public String getSrcId() {
        return srcId;
    }

    public String getPayload() {
        return payload;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public String getIsProcessed() {
        return isProcessed;
    }

    public String getBatchId() {
        return batchId;
    }

    @QuerySqlField(index = true)
    public String entryTime;

    /** Has this record been processed by Second Stage */
    @QuerySqlField(index = true)
    public String isProcessed;

    /**
     * For BATCH sources: Batch ID per File read or timed, eg. 1 hour.
     *
     * For RT sources: Windowed requests.
     *                  Used to track how many requests we processed for 1 hour.
     *                  This is generated from the Source system.
     *                  Say, Date + Hour of day (1..16..24) + source_id
     * */
    @QuerySqlField(index = true)
    public String batchId;

    public L1_TriggerRecord(String id, String srcId, String payload, String batchId) {
        this.id = id;
        this.srcId = srcId;
        this.payload = payload;
        this.isProcessed = "FALSE";
        this.batchId = batchId;
        Calendar cal = Calendar.getInstance();
        Date currentTime = cal.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sdf.setTimeZone(TimeZone.getTimeZone("CET"));
        String date = sdf.format(currentTime);
        this.entryTime=date;
    }


    @Override
    public String toString() {
        return "L1_TriggerRecord{" +
                "id='" + id + '\'' +
                ", srcId='" + srcId + '\'' +
                ", payload='" + payload + '\'' +
                ", entryTime='" + entryTime + '\'' +
                ", isProcessed='" + isProcessed + '\'' +
                ", batchId='" + batchId + '\'' +
                '}';
    }

    /**
     * Default constructor.
     */
    public L1_TriggerRecord() {
        // No-op.
    }
}
