package org.course.functionalstyle.lazyevaluation.after;

import org.reusable.lazy.Thunk;

import java.util.ArrayList;
import java.util.List;


/**
 * The first way is to introduce a new class Thunk<T> that wraps our callback, it is thread-safe and once evaluated it once
 * it will always return the same value. You can study the class Thunk<T> in the package org.reusable.lazy.
 * <p>
 * Extra info:
 * A thunk in real life is something like an iceberg. It is a large block of ice that has been broken off from a glacier
 * or ice shelf (answer given by ChatGPT). A thunk of ice is formed by freezing water and when it is liquidated is
 * "thawed".
 */
public class Main {

    private static FakeDbConnection dbConnection = new FakeDbConnection("no current thread");
    private static int counter = 0;

    public static void main(final String[] args) {

        final Thunk<FakeDbConnection> initializeDbConnectionThunk = Thunk.of(() -> {
            counter++;
            System.out.println("Counter: " + counter);
            return new FakeDbConnection(Thread.currentThread().getName());
        });

        // Create 10 threads
        final List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            final Thread thread = new Thread(() -> tryInitializeDbConnection(initializeDbConnectionThunk));
            threads.add(thread);
        }
        // Start the threads
        threads.forEach(Thread::start);
    }

    private static void tryInitializeDbConnection(final Thunk<FakeDbConnection> initializeDbConnectionThunk) {
        System.out.println("Before update : current thread name: " + dbConnection.currentThreadName());
        dbConnection = initializeDbConnectionThunk.get();
        System.out.println("After update : current thread name: " + dbConnection.currentThreadName());
    }
}