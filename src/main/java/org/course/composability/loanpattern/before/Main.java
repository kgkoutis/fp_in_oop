package org.course.composability.loanpattern.before;

import java.util.concurrent.CompletableFuture;

/**
 * Here we see two asynchronous tasks are being fired off and the main thread waits for both of them to complete.
 * <p>
 * The two actions that the tasks perform (execute1 and execute2) are wrapped in a try-with-resources block.
 * It is advisable when you are using resources from the Operating System to use try-with-resources
 * and dispose them as soon as you don't need them anymore, and NOT rely on the garbage collector to do the job.
 * Some of these resources are scarce and you don't want to run out of them. So it is always a good idea to close
 * them as soon as you are done with them.
 * <p>
 * But we may just forget to close them, especially if we are using a lot of them (here we only use one).
 * Or more likely, we don't know that we have to do that because we are not experienced enough (i.e. we are inexperienced).
 * <p>
 * How can we separate the resource management code from the business logic code?
 * Wouldn't it be nice to pass the business logic code as a callback to the resource management code and let it manage
 * the resource for us? How would that work?
 */
public class Main {
    public static void main(final String[] args) {

        final long waitTimeInMs1 = 1000; // 1 second
        final long waitTimeInMs2 = 2000; // 2 seconds

        final CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> Main.execute(waitTimeInMs1)); // asynchronous operation of execute1
        final CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> Main.execute(waitTimeInMs2)); // asynchronous operation of execute2

        CompletableFuture.allOf(cf1, cf2).join(); // wait for both operations to complete
    }

    private static String execute(final long waitTimeInMs) {
        String result = null;
        // try-with-resources
        try (final ResourceHolder resourceHolder = new StopWatch()) {
            result = resourceHolder.delayMs(waitTimeInMs);
        } catch (final Exception e) {
            System.out.println("Exception caught" + e);
        }
        return result;
    }
}