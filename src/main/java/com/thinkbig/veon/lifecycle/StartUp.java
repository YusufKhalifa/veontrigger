package com.thinkbig.veon.lifecycle;

import com.thinkbig.veon.core.CoreController;
import com.thinkbig.veon.dao.L1_TriggerRecord;
import com.thinkbig.veon.dao.L2_DPI;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteException;
import org.apache.ignite.lifecycle.LifecycleBean;
import org.apache.ignite.lifecycle.LifecycleEventType;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.apache.log4j.Logger;

import static com.thinkbig.veon.util.IConstants.*;

public class StartUp implements LifecycleBean {


    @IgniteInstanceResource
    private Ignite ignite;


    final static Logger logger = Logger.getLogger(StartUp.class);


    public static IgniteCache<String, L1_TriggerRecord> IGNITE_CACHE_OBJECT_L1_TRIGGERRECORD;
    public static IgniteCache<String, L2_DPI> IGNITE_CACHE_OBJECT_L2_DPI;

    public static Ignite IGNITE;

    public void onLifecycleEvent(LifecycleEventType evt) throws IgniteException {
        if (evt == LifecycleEventType.BEFORE_NODE_START) {
            logger.info("evt = [" + evt + "]");
        }
        if (evt == LifecycleEventType.AFTER_NODE_START) {
            logger.info("evt = [" + evt + "]");

            try {
                 IGNITE = ignite;
//                ignite.cache(IGNITE_CACHE_OBJECT_L3_T03_AGGREGATION).localLoadCache(null);
//                ignite.cache(IGNITE_CACHE_OBJECT_L3_T04_AGGREGATION).localLoadCache(null);
//                ignite.cache(IGNITE_CACHE_OBJECT_L3_T05_AGGREGATION).localLoadCache(null);
//                ignite.cache(IGNITE_CACHE_OBJECT_L3_T06_AGGREGATION).localLoadCache(null);
                logger.info("Successfully pre-loaded from cassandra tables");
                IGNITE_CACHE_OBJECT_L1_TRIGGERRECORD = IGNITE.cache(IGNITE_CACHE_NAME_L1_TRIGGERRECORD);
                IGNITE_CACHE_OBJECT_L2_DPI = IGNITE.cache(IGNITE_CACHE_L2_DPI);
                new CoreController().startModules();


            } catch (Exception e) {
                logger.error("Failed to preload caches");
            }


        }

    }
}
