package org.course.composability.loanpattern.before;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Here we see a standard way of using the ExecutorService to execute two asynchronous operations.
 * Two asynchronous tasks are queued on the executorService thread pool and the main thread waits for both of them to complete.
 * Afterwards, the executorService is shutdown gracefully.
 *
 * The two actions that the tasks perform (execute1 and execute2) are wrapped in a try-with-resources block.
 * It is always a good idea when you are using resources from the Operating System to use try-with-resources
 * and NOT rely on the garbage collector to do the job. Some of these resources are scarce and you don't want to
 * run out of them. So it is always a good idea to close them as soon as you are done with them.
 *
 * But we may just forget to close them, especially if we are using a lot of them (here we only use one).
 * Or more likely, we don't know that we have to do that because we are not experienced enough (i.e. we are beginners).
 *
 * How can we separate the resource management code from the business logic code?
 * Wouldn't it be nice to pass the business logic code as a callback to the resource management code and let it manage
 * the resource for us? How would that work?
 * */
public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        long waitTimeInMs1 = 1000;
        long waitTimeInMs2 = 2000;

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> Main.execute1(waitTimeInMs1), executorService); // asynchronous operation of execute1
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> Main.execute2(waitTimeInMs2), executorService); // asynchronous operation of execute2

        CompletableFuture.allOf(cf1, cf2).join(); // wait for both operations to complete

        awaitTerminationAfterShutdown(executorService); // shutdown and terminate the executorService gracefully
    }

    private static String execute1(long waitTimeInMs) {
        String result = null;
        // try-with-resources
        try (Resource resource = new StopWatch()) {
            result = resource.doSomething(waitTimeInMs);
        } catch (Exception e) {
            System.out.println("Exception caught" + e);
        }
        return result;
    }

    private static String execute2(long waitTimeInMs) {
        String result = null;
        // try-with-resources
        try (Resource resource = new StopWatch()) {
            result = resource.doSomethingElse(waitTimeInMs);
        } catch (Exception e) {
            System.out.println("Exception caught" + e);
        }
        return result;
    }

    public static void awaitTerminationAfterShutdown(ExecutorService threadPool) {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException ex) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}