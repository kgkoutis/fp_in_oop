package org.course.functionalstyle.lazyevaluation.before;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Let's start with a very important observation: a function is a language construct that exists more or less in all programming languages.
 * Most people learn functions in their early days of programming. The justification their teachers give for their existence is that they serve for code reuse.
 * <p>
 * You have a named block of code and you can reuse it everywhere you want, and you can also parametrize it.
 * <p>
 * What is the other top reason for the existence of functions in ALL programming languages?
 * Maybe you never thought of it formally, but functions are the number one mechanism to express deferred execution (also known as lazy evaluation).
 * <p>
 * To convince yourself compare these two statements in pseudocode:
 * <p>
 * var x = 0;                // eager (or strict) evaluation, x is initialized now
 * x = 1;                    // eager (or strict) evaluation, x is set now
 * var f = () -> { x = 1; }; // lazy (or deferred) evaluation, x is not set now, but it will be set whenever f is called
 * <p>
 * What is the main advantage of lazy evaluation? (think)
 * What is the main disadvantage of lazy evaluation? (think)
 * <p>
 * This class has concurrency issues. The dbConnection is a shared resource and multiple threads try to initialize it.
 * We would like to a) initialize it lazily, only once and b) let it be tolerant to thread race condition, when
 * multiple threads try to initialize it at the same time. Only the first thread should initialize it, the rest should obtain the instance
 * that was already initialized.
 * <p>
 * The first step is taken, the Supplier<T> callback that we have delays the initialization of the dbConnection.
 * But it is not thread-safe and can be called multiple times. How do we fix it?
 */
public class Main {

    static FakeDbConnection dbConnection = new FakeDbConnection("no current thread");
    private static int counter;

    public static void main(final String[] args) {

        final Supplier<FakeDbConnection> initializeDbConnection = () -> {
            counter++;
            System.out.println("Counter: " + counter);
            return new FakeDbConnection(Thread.currentThread().getName());
        };

        // Create 10 threads
        final List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            final var thread = new Thread(() -> tryInitializeDbConnection(initializeDbConnection));
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
