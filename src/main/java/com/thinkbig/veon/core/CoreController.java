package com.thinkbig.veon.core;

import com.thinkbig.veon.kafka.KafkaIngestionConsumer;
import com.thinkbig.veon.kafka.KafkaProducer;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

import static com.thinkbig.veon.util.IConstants.*;

public class CoreController {
    private static Logger logger = Logger.getLogger("CoreController");


    private static ThreadPoolExecutor startup_executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(THREADS_MAX_STARTUP_EXECUTOR);

    /**
     * Loads all modules.
     */
    public void startModules() {


        logger.info("Starting modules ..");

        try {
            Boolean outcome = processStartupTasks();
            if (!outcome) {
                startup_executor.shutdown(); //always reclaim resources
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException ex) {
            logger.error("Problem executing worker: " + ex.getMessage());
        }


    }


    /**
     * Used to concurrently run modules.
     *
     * @return Status when Stopped
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private Boolean processStartupTasks() throws InterruptedException, ExecutionException {

        logger.info("From startup tasks");
        Collection<Callable<Boolean>> tasks = new ArrayList<>();

        tasks.add(new startListenerModules());
        tasks.add(new startKafkaModules());

        List<Future<Boolean>> results = startup_executor.invokeAll(tasks);

        Boolean holder = Boolean.TRUE; // to combine results

        for (Future<Boolean> result : results) {
            Boolean taskResult = result.get();
            holder = holder && taskResult;
        }

        return holder;
    }





    /** Start the current module */
    private final class startListenerModules implements Callable<Boolean> {

        /** return false, continuous running task */
        @Override public Boolean call() throws Exception {
            new Listener().start();
            return false;
        }

    }



    private final class startKafkaModules implements Callable<Boolean>{


        @Override
        public Boolean call() throws Exception {

            new KafkaProducer();

            new KafkaIngestionConsumer();
            KafkaIngestionConsumer.start();


            return false;
        }
    }



    /**
     * Stops Ingestion Module (Kafka Consumer) and Service Module (Kafka Producer)
     * Other modules are GC normally and dont require additional Stop Step.
     */

    public static void stopModules() {

        KafkaIngestionConsumer.shutdown();


    }

}
