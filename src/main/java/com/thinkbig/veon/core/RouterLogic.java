package com.thinkbig.veon.core;
import com.thinkbig.veon.dao.L2_DPI;
import com.thinkbig.veon.dao.L4_NotificationRecord;
import com.thinkbig.veon.kafka.KafkaProducer;
import org.apache.ignite.events.CacheEvent;
import org.apache.log4j.Logger;


public class RouterLogic {
    private static Logger logger = Logger.getLogger("RouterLogic");

    public static boolean process(CacheEvent evt){
        switch (evt.cacheName()){
            case "CACHE_L2_DPI":
                L2_DPI l2_dpi = (L2_DPI) evt.newValue();
                L4_NotificationRecord notif = new L4_NotificationRecord(l2_dpi.getId(), l2_dpi.getSrcId(), "constant value", l2_dpi.getMsisdn(), "1", l2_dpi.getStartTimeMilSec(), "","","","","","","","","","");
                KafkaProducer.sendMessage(notif);
                break;
            default:
                break;
        }
        return true;
    }
}
