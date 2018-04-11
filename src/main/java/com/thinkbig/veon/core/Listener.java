package com.thinkbig.veon.core;

import org.apache.ignite.events.CacheEvent;
import org.apache.ignite.events.EventType;
import org.apache.ignite.lang.IgnitePredicate;
import org.apache.log4j.Logger;

import static com.thinkbig.veon.lifecycle.StartUp.IGNITE;

public class Listener {

    private static Logger logger = Logger.getLogger("Listener");



    public void start(){


        logger.info("Starting LocalCache Listener [node ID=" +IGNITE.cluster().localNode().id()+"]");

        // Local listener that listenes to local events.
        IgnitePredicate<CacheEvent> locLsnr = new IgnitePredicate<CacheEvent>() {
            @Override
            public boolean apply(CacheEvent evt) {

                if (IGNITE.affinity(evt.cacheName()).isPrimary(IGNITE.cluster().localNode(), evt.key()))
                {
                    logger.info("INSIDE CACHE");
                    boolean test=RouterLogic.process(evt);
                }


                return true; // Continue listening.
            }
        };

        // Subscribe to specified cache events occurring on local node.
        IGNITE.events().localListen(locLsnr,
                EventType.EVT_CACHE_OBJECT_PUT);
    }
}
