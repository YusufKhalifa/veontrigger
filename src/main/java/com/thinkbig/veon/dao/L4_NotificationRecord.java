package com.thinkbig.veon.dao;

import org.apache.ignite.cache.affinity.AffinityKeyMapped;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class L4_NotificationRecord implements Serializable {
        /** L4_Notification (indexed).
         *
         *
         * This is Notification ID.
         * This ID will be used to send out notification systems,
         * and used as a callback to update the Notification time.
         *
         *
         * How Notification ID is created:
         *
         * triggerId + "_" + aggregateId + "_" +  UUID.randomUUID().toString().replaceAll("-", "")
         *
         *
         * */
        @QuerySqlField(index = true)
        public String id;

        /** triggers id for which this Cache belongs to */
        @QuerySqlField(index = true)
        public String triggerId;

        /** triggers Description
         *
         * eg. DUT PayUG
         * */
        public String triggerDescription;

        /** msisdn */
        @QuerySqlField(index = true)
        public String MSISDN;


        /** AFK_MSISDN (indexed)
         *
         *  For Layer 4 Notification
         *  we are using TRIGGERID + MSISDN as affinity key.
         *
         *  */
        @AffinityKeyMapped
        public String AFK_MSISDN;


        /** thas this been Processed by external systems. */
        @QuerySqlField(index = true)
        public String isProcessed;

        /** aggregate ID */
        @QuerySqlField(index = true)
        public String aggregateId;


        /** Incoming time from Source System, as per original record*/
        @QuerySqlField
        public String incomingTime;



        /** Notification Time (when this notification was notified by Downstream Apps)*/
        @QuerySqlField(index = true)
        public String notificationTime;



        /**
         *  Value placeholders, use to send triggers
         *  specific values for downstream applications
         *
         *  All casted as String, default "";
         * */
        @QuerySqlField
        public String TRIGGER_ATTR_01;

        @QuerySqlField
        public String TRIGGER_ATTR_02;

        @QuerySqlField
        public String TRIGGER_ATTR_03;

        @QuerySqlField
        public String TRIGGER_ATTR_04;

        @QuerySqlField
        public String TRIGGER_ATTR_05;

        @QuerySqlField
        public String TRIGGER_ATTR_06;

        @QuerySqlField
        public String TRIGGER_ATTR_07;

        @QuerySqlField
        public String TRIGGER_ATTR_08;

        @QuerySqlField
        public String TRIGGER_ATTR_09;

        @QuerySqlField
        public String TRIGGER_ATTR_10;




        /**
         * Default constructor.
         */
        public L4_NotificationRecord() {
            // No-op.
        }


        public L4_NotificationRecord(String id, String triggerId, String triggerDescription, String MSISDN, String aggregateId, String incomingTime,
                                     String TRIGGER_ATTR_01, String TRIGGER_ATTR_02, String TRIGGER_ATTR_03, String TRIGGER_ATTR_04, String TRIGGER_ATTR_05,
                                     String TRIGGER_ATTR_06, String TRIGGER_ATTR_07, String TRIGGER_ATTR_08, String TRIGGER_ATTR_09, String TRIGGER_ATTR_10
        ) {

            this.id = id;
            this.triggerId = triggerId;
            this.triggerDescription = triggerDescription;
            this.MSISDN = MSISDN;
            this.aggregateId = aggregateId;

            //incoming time format here





            this.incomingTime = incomingTime;


            this.AFK_MSISDN =  MSISDN;
            this.isProcessed = "FALSE";
            //String currentTimeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());


            DateFormat pstFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            pstFormat.setTimeZone(TimeZone.getTimeZone("CET"));
            //notification time format here


            this.notificationTime = pstFormat.format(Calendar.getInstance().getTime());

            // Default, empty
            this.TRIGGER_ATTR_01 = TRIGGER_ATTR_01;
            this.TRIGGER_ATTR_02 = TRIGGER_ATTR_02;
            this.TRIGGER_ATTR_03 = TRIGGER_ATTR_03;
            this.TRIGGER_ATTR_04 = TRIGGER_ATTR_04;
            this.TRIGGER_ATTR_05 = TRIGGER_ATTR_05;
            this.TRIGGER_ATTR_06 = TRIGGER_ATTR_06;
            this.TRIGGER_ATTR_07 = TRIGGER_ATTR_07;
            this.TRIGGER_ATTR_08 = TRIGGER_ATTR_08;
            this.TRIGGER_ATTR_09 = TRIGGER_ATTR_09;
            this.TRIGGER_ATTR_10 = TRIGGER_ATTR_10;
        }

        /**
         * {@inheritDoc}
         */
        @Override public String toString() {
            return "L4_TriggerNotification [id=" + id +
                    ", triggerId=" + triggerId +
                    ", triggerDescription=" + triggerDescription +
                    ", AFK_MSISDN=" + AFK_MSISDN +
                    ", MSISDN=" + MSISDN +
                    ", isProcessed=" + isProcessed +
                    ", aggregateId=" + aggregateId +
                    ", incomingTime=" + incomingTime +
                    ", notificationTime=" + notificationTime +
                    ", TRIGGER_ATTR_01=" + TRIGGER_ATTR_01 +
                    ", TRIGGER_ATTR_01=" + TRIGGER_ATTR_02 +
                    ", TRIGGER_ATTR_01=" + TRIGGER_ATTR_03 +
                    ", TRIGGER_ATTR_01=" + TRIGGER_ATTR_04 +
                    ", TRIGGER_ATTR_01=" + TRIGGER_ATTR_05 +
                    ", TRIGGER_ATTR_01=" + TRIGGER_ATTR_06 +
                    ", TRIGGER_ATTR_01=" + TRIGGER_ATTR_07 +
                    ", TRIGGER_ATTR_01=" + TRIGGER_ATTR_08 +
                    ", TRIGGER_ATTR_01=" + TRIGGER_ATTR_09 +
                    ", TRIGGER_ATTR_01=" + TRIGGER_ATTR_10 +
                    ']';
        }
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTriggerId() {
            return triggerId;
        }

        public void setTriggerId(String triggerId) {
            this.triggerId = triggerId;
        }

        public String getTriggerDescription() {
            return triggerDescription;
        }

        public void setTriggerDescription(String triggerDescription) {
            this.triggerDescription = triggerDescription;
        }

        public String getMSISDN() {
            return MSISDN;
        }

        public void setMSISDN(String MSISDN) {
            this.MSISDN = MSISDN;
        }

        public String getAFK_MSISDN() {
            return AFK_MSISDN;
        }

        public void setAFK_MSISDN(String AFK_MSISDN) {
            this.AFK_MSISDN = AFK_MSISDN;
        }

        public String getIsProcessed() {
            return isProcessed;
        }

        public void setIsProcessed(String isProcessed) {
            this.isProcessed = isProcessed;
        }

        public String getAggregateId() {
            return aggregateId;
        }

        public void setAggregateId(String aggregateId) {
            this.aggregateId = aggregateId;
        }

        public String getIncomingTime() {
            return incomingTime;
        }

        public void setIncomingTime(String incomingTime) {
            this.incomingTime = incomingTime;
        }

        //added String to notification Time
        public String getNotificationTime() {
            return notificationTime;
        }

        //added String
        public void setNotificationTime(String notificationTime) {
            this.notificationTime = notificationTime;
        }

        public String getTRIGGER_ATTR_01() {
            return TRIGGER_ATTR_01;
        }

        public void setTRIGGER_ATTR_01(String TRIGGER_ATTR_01) {
            this.TRIGGER_ATTR_01 = TRIGGER_ATTR_01;
        }

        public String getTRIGGER_ATTR_02() {
            return TRIGGER_ATTR_02;
        }

        public void setTRIGGER_ATTR_02(String TRIGGER_ATTR_02) {
            this.TRIGGER_ATTR_02 = TRIGGER_ATTR_02;
        }

        public String getTRIGGER_ATTR_03() {
            return TRIGGER_ATTR_03;
        }

        public void setTRIGGER_ATTR_03(String TRIGGER_ATTR_03) {
            this.TRIGGER_ATTR_03 = TRIGGER_ATTR_03;
        }

        public String getTRIGGER_ATTR_04() {
            return TRIGGER_ATTR_04;
        }

        public void setTRIGGER_ATTR_04(String TRIGGER_ATTR_04) {
            this.TRIGGER_ATTR_04 = TRIGGER_ATTR_04;
        }

        public String getTRIGGER_ATTR_05() {
            return TRIGGER_ATTR_05;
        }

        public void setTRIGGER_ATTR_05(String TRIGGER_ATTR_05) {
            this.TRIGGER_ATTR_05 = TRIGGER_ATTR_05;
        }

        public String getTRIGGER_ATTR_06() {
            return TRIGGER_ATTR_06;
        }

        public void setTRIGGER_ATTR_06(String TRIGGER_ATTR_06) {
            this.TRIGGER_ATTR_06 = TRIGGER_ATTR_06;
        }

        public String getTRIGGER_ATTR_07() {
            return TRIGGER_ATTR_07;
        }

        public void setTRIGGER_ATTR_07(String TRIGGER_ATTR_07) {
            this.TRIGGER_ATTR_07 = TRIGGER_ATTR_07;
        }

        public String getTRIGGER_ATTR_08() {
            return TRIGGER_ATTR_08;
        }

        public void setTRIGGER_ATTR_08(String TRIGGER_ATTR_08) {
            this.TRIGGER_ATTR_08 = TRIGGER_ATTR_08;
        }

        public String getTRIGGER_ATTR_09() {
            return TRIGGER_ATTR_09;
        }

        public void setTRIGGER_ATTR_09(String TRIGGER_ATTR_09) {
            this.TRIGGER_ATTR_09 = TRIGGER_ATTR_09;
        }

        public String getTRIGGER_ATTR_10() {
            return TRIGGER_ATTR_10;
        }

        public void setTRIGGER_ATTR_10(String TRIGGER_ATTR_10) {
            this.TRIGGER_ATTR_10 = TRIGGER_ATTR_10;
        }

        /**
         * Set isProcessed : invoked when the next layer has updated the next caches.
         *
         */
        public void markProcessed(){
            this.isProcessed = "TRUE";
        }
}
