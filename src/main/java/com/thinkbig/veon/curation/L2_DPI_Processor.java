package com.thinkbig.veon.curation;

import com.thinkbig.veon.dao.L1_TriggerRecord;
import com.thinkbig.veon.dao.L2_DPI;
import com.thinkbig.veon.util.Helpers;
import org.apache.log4j.Logger;


import java.util.List;

import static com.thinkbig.veon.lifecycle.StartUp.IGNITE_CACHE_OBJECT_L2_DPI;

public class L2_DPI_Processor {

    private static final Helpers helpers = new Helpers();

    private static Logger logger = Logger.getLogger("L2_DPI_Processor");

    public static void process(L1_TriggerRecord tr) {
        List<String> extracted_payload = helpers.splitCSVRecord(tr.payload);
        populateCache(extracted_payload, tr);
    }

    private static void populateCache(List<String> extracted_payload, L1_TriggerRecord tr) {
        String hasParsingErrors = "FALSE";
        String StartTimeSecond = "",
                StartTimeMilSec = "",
                EndTimeSecond = "",
                EndTimeMilSec = "",
                IMSI = "",
                MSISDN = "",
                IMEISV = "",
                MS_IP = "",
                Server_IP = "",
                APN = "",
                Charging_Characteristics = "",
                RAT_Type = "",
                CGI = "",
                SAI = "",
                ECGI = "",
                Uplink_Traffic = "",
                Downlink_Traffic = "",
                Uplink_Packets = "",
                Downlink_Packets = "",
                Protocol_Category = "",
                Application = "",
                Sub_Application = "",
                EGN_Sub_Protocol = "",
                URL = "",
                User_Agent = "";

        try{
            StartTimeSecond = extracted_payload.get(1);
             StartTimeMilSec = extracted_payload.get(2);
             EndTimeSecond = extracted_payload.get(3);
             EndTimeMilSec = extracted_payload.get(4);
             IMSI = extracted_payload.get(5);
             MSISDN = extracted_payload.get(6);
             IMEISV = extracted_payload.get(7);
             MS_IP = extracted_payload.get(8);
             Server_IP = extracted_payload.get(9);
             APN = extracted_payload.get(10);
             Charging_Characteristics = extracted_payload.get(11);
             RAT_Type = extracted_payload.get(12);
             CGI = extracted_payload.get(13);
             SAI = extracted_payload.get(14);
             ECGI = extracted_payload.get(15);
             Uplink_Traffic = extracted_payload.get(16);
             Downlink_Traffic = extracted_payload.get(17);
             Uplink_Packets = extracted_payload.get(18);
             Downlink_Packets = extracted_payload.get(19);
             Protocol_Category = extracted_payload.get(20);
             Application = extracted_payload.get(21);
             Sub_Application = extracted_payload.get(22);
             EGN_Sub_Protocol = extracted_payload.get(23);
             URL = extracted_payload.get(24);
             User_Agent = extracted_payload.get(25);


            //2G/3G Indicator
            if (RAT_Type.contains("5"))
                RAT_Type = "LTE";
            else if (RAT_Type.contains("0"))
                RAT_Type = "3G";
            else if (RAT_Type.contains("1"))
                RAT_Type = "GPRS";
            else if (RAT_Type.contains("3"))
                RAT_Type = "Generic Access Network";
            else if (RAT_Type.contains("4"))
                RAT_Type = "HSPA";
            else if (RAT_Type.contains("6"))
                RAT_Type = "Unknown";
            else if (RAT_Type.contains("2"))
                RAT_Type = "Wifi";



            L2_DPI cache_entry = new L2_DPI(tr.id, tr.srcId, hasParsingErrors, MSISDN,
                    Uplink_Traffic, Downlink_Traffic, StartTimeMilSec,
                    Protocol_Category, Application,
                    IMEISV, IMSI,RAT_Type);

            IGNITE_CACHE_OBJECT_L2_DPI.put(tr.id, cache_entry);

        } catch(Exception ex){

        }


    }
}
