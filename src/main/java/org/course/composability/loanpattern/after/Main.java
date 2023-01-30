package org.course.composability.loanpattern.after;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Notice how the resource management code is handled only in one place: in the executeWithResourceManagement method.
 * Everything else is retrofitted to be a callback that is passed not only its original dependencies (waitTimeInMs)
 * but also the resource that is being brought into scope by "somewhere else".
 *
 * This pattern of abstracting away the resource management code into a separate method is called the Loan Pattern.
 * The callbacks are "borrowing" the resource from the resource management code and they are "returning" it back.
 *
 * Composing the resource management code with business logic code leads to the same program behavior as before.
 *
 * */

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        long waitTimeInMs1 = 1000;
        long waitTimeInMs2 = 2000;

        Function<Resource, Function<Long, String>> doSomethingCallback = resource -> waitTimeInMs -> {
            try {
                return resource.doSomething(waitTimeInMs);
            } catch (Exception e) {
                System.out.println("Exception caught" + e);
                return null;
            }
        };

        Function<Resource, Function<Long, String>> doSomethingElseCallback = resource -> waitTimeInMs -> {
            try {
                return resource.doSomethingElse(waitTimeInMs);
            } catch (Exception e) {
                System.out.println("Exception caught" + e);
                return null;
            }
        };

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> Main.executeWithResourceManagement(waitTimeInMs1, doSomethingCallback), executorService);
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> Main.executeWithResourceManagement(waitTimeInMs2, doSomethingElseCallback), executorService);

        CompletableFuture.allOf(cf1, cf2).join();

        awaitTerminationAfterShutdown(executorService);
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

    private static <R> R executeWithResourceManagement(long waitTimeInMs, Function<Resource, Function<Long, R>> callback) {
        R result = null;
        // try-with-resources
        try (StopWatch resource = new StopWatch()) {
            result = callback.apply(resource).apply(waitTimeInMs);
        } catch (Exception e) {
            System.out.println("Exception caught" + e);
        }
        return result;
    }
}
