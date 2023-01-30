package org.course.functionalstyle.lazyevaluation.after;

import org.reusable.lazy.Thunk;

import java.util.ArrayList;
import java.util.List;


/**
 * The first way is to introduce a new class Thunk<T> that wraps our callback, it is thread-safe and once evaluated it once
 * it will always return the same value. You can study the class Thunk<T> in the package org.reusable.lazy.
 *
 * Extra info:
 * A thunk in real life is something like an iceberg. It is a large block of ice that has been broken off from a glacier
 * or ice shelf (answer given by ChatGPT). A thunk of ice is formed by freezing water and when it is liquidated is
 * "thawed".
 *
 * */
public class Main {

    static FakeDbConnection dbConnection = new FakeDbConnection("no current thread");
    private static int counter;

    public static void main(String[] args) {

        Thunk<FakeDbConnection> initializeDbConnectionThunk = Thunk.of(() -> {
            counter++;
            System.out.println("Counter: " + counter);
            return new FakeDbConnection(Thread.currentThread().getName());
        });

        // Create 10 threads
        List<Thread> threads = new ArrayList<>();

        for (int i=0; i < 10; i++) {
            var thread = new Thread(() -> tryInitializeDbConnection(initializeDbConnectionThunk));
            threads.add(thread);
        }
        // Start the threads
        threads.forEach(Thread::start);
    }

    private static void tryInitializeDbConnection(Thunk<FakeDbConnection> initializeDbConnectionThunk) {
        System.out.println("Before update : current thread name: " + dbConnection.currentThreadName());
        dbConnection = initializeDbConnectionThunk.get();
        System.out.println("After update : current thread name: " + dbConnection.currentThreadName());
    }
}