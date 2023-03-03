package org.reusable.lazy;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemoizerTests {
    @Test
    public void memoizeSupplier() {
        final Supplier<String> memoizer = Memoizer.memoize("memoizeSupplier", () -> "Hello");
        assertEquals("Hello", memoizer.get());
    }

    @Test
    public void memoizeFunction() {
        final Function<Integer, Integer> memoizer = Memoizer.memoize("memoizeFunction", (i) -> i + 1);
        assertEquals(2, memoizer.apply(1));
    }

    @Test
    public void memoizeConsumer() {
        final StringBuilder stringBuilder = new StringBuilder();
        final Consumer<String> consumer = stringBuilder::append;
        final Consumer<String> memoizer = Memoizer.memoize("memoizeConsumer", consumer);
        memoizer.accept("Hello");
        assertEquals("Hello", stringBuilder.toString());
    }

    /**
     * Expensive test that shows that the supplier is only called once.
     * 10 threads are started and each one calls the supplier.
     * For the consumer and the function the logic is the same, so we only test the supplier.
     */
    @Test
    public void cacheValue() throws InterruptedException {
        final Supplier<UUID> memoizer = Memoizer.memoize("cacheValue", UUID::randomUUID);
        final List<UUID> uuids = new CopyOnWriteArrayList<>();

        final List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            final Thread thread = new Thread(() -> {
                // slight delay to make sure all threads are started
                try {
                    Thread.sleep(1000);
                } catch (final InterruptedException e) {
                    throw new RuntimeException(e);
                }

                final UUID uuid = memoizer.get();
                uuids.add(uuid);
            });
            threads.add(thread);
        }
        // Start the threads
        threads.forEach(Thread::start);

        // Wait for the threads to finish
        threads.forEach(t -> {
            try {
                t.join();
            } catch (final InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        final HashSet<UUID> hashSet = new HashSet<UUID>(uuids);

        assertEquals(10, uuids.size());
        assertEquals(1, hashSet.size());
    }
}
