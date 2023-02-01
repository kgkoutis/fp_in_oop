package org.reusable.throwing;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

public class ThrowingConsumerTests {
    /**
     * Notice that we don't need to indicate the checked exception in the method.
     */
    @Test
    public void uncheck() {
        final StringBuilder stringBuilder = new StringBuilder();
        final Consumer<Object> consumer = ThrowingConsumer.uncheck(stringBuilder::append);
        assertDoesNotThrow(() -> consumer.accept("Hello"));
        assertEquals("Hello", stringBuilder.toString());
        final Consumer<Object> consumer1 = ThrowingConsumer.uncheck(s -> { throw new Exception(); });
        assertThrows(Exception.class, () -> consumer1.accept("Hello"));
    }
}