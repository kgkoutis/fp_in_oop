package org.reusable.lazy;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThunkTests {
    @Test
    public void simpleSupplier() {
        final Thunk<String> thunk = Thunk.of(() -> "Hello");
        assertEquals("Hello", thunk.get());
    }

    /**
     * Expensive test that shows that the supplier is only called once.
     * 10 threads are started and each one calls the supplier.
     */
    @Test
    public void cacheValue() throws InterruptedException {
        final List<UUID> uuids = new CopyOnWriteArrayList<>();

        final Thunk<UUID> thunk = Thunk.of(UUID::randomUUID);

        final List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            final var thread = new Thread(() -> {
                // slight delay to make sure all threads are started
                try {
                    Thread.sleep(1000);
                } catch (final InterruptedException e) {
                    throw new RuntimeException(e);
                }

                final UUID uuid = thunk.get();
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

