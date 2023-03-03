package org.course.functionalstyle.lazyevaluation.after;

import org.reusable.lazy.Memoizer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Another approach is the Memoizer. It is a container around not a callback, but a concurrent map that can hold one or
 * multiple values, whose production is specified by callbacks.
 * <p>
 * Which one should you use?
 * <p>
 * Basically they work the same but...
 * <p>
 * The memoizer as can be seen is a much more simplistic piece of code to understand and can be used for multiple callbacks.
 * However, the thunk allows more processing of the callback it wraps (check map and flatMap methods) since it is a specialized
 * container around one singular callback, so it can attach more transformations to the callback it wraps.
 * <p>
 * Question: in which situation are they both not adequate? What other means do exist to replace them? (think)
 */
public class MainMemoiser {

    static FakeDbConnection dbConnection = new FakeDbConnection("no thread");
    private static int counter;

    public static void main(final String[] args) {

        final Supplier<FakeDbConnection> initializeDbConnection = Memoizer.memoize("initializeDbConnection", () -> {
            counter++;
            System.out.println("Counter: " + counter);
            return new FakeDbConnection(Thread.currentThread().getName());
        });

        // Create 10 threads
        final List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            final Thread thread = new Thread(() -> tryInitializeDbConnection(initializeDbConnection));
            threads.add(thread);
        }
        // Start the threads
        threads.forEach(Thread::start);
    }

    private static void tryInitializeDbConnection(final Supplier<FakeDbConnection> initializeDbConnection) {
        System.out.println("Before update : current thread name: " + dbConnection.currentThreadName());
        dbConnection = initializeDbConnection.get();
        System.out.println("After update : current thread name: " + dbConnection.currentThreadName());
    }
}
