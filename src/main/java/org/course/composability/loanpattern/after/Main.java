package org.course.composability.loanpattern.after;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * Notice how the resource management code is handled only in one place: in the executeWithResourceManagement method.
 * Everything else is retrofitted to be a callback that is passed not only its original dependencies (waitTimeInMs)
 * but also the resourceHolder that is being brought into scope by "somewhere else".
 * <p>
 * This pattern of abstracting away the resource management code into a separate method is called the Loan Pattern.
 * The callbacks are "borrowing" the resource from the resource management code.
 * <p>
 * Composing the resource management code with business logic code leads to the same program behavior as before.
 */

public class Main {
    public static void main(final String[] args) {

        final long waitTimeInMs1 = 1000; // 1 second
        final long waitTimeInMs2 = 2000; // 2 seconds

        final CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> Main.executeWithResourceManagement(waitTimeInMs1, Main::delayMs));
        final CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> Main.executeWithResourceManagement(waitTimeInMs2, Main::delayMs));

        CompletableFuture.allOf(cf1, cf2).join();
    }

    // business logic code
    // has nothing to do with try-with-resources
    private static Function<Long, String> delayMs(final ResourceHolder resourceHolder) {
        return resourceHolder::delayMs;
    }

    private static <R> R executeWithResourceManagement(final long waitTimeInMs,
                                                       final Function<ResourceHolder, Function<Long, R>> callback) {
        R result = null;
        // try-with-resources
        try (final StopWatch stopWatch = new StopWatch()) {
            result = callback.apply(stopWatch).apply(waitTimeInMs);
        } catch (final Exception e) {
            System.out.println("Exception caught" + e);
        }
        return result;
    }
}
