package org.reusable;

import org.reusable.throwing.ThrowingConsumer;

public final class Threads {

    public static void delay(final long millis) {
        ThrowingConsumer.uncheck((Long ms) -> java.lang.Thread.sleep(ms)).accept(millis);
    }
}
