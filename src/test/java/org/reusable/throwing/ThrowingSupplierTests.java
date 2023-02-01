package org.reusable.throwing;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ThrowingSupplierTests {
    /**
     * Notice that we don't need to indicate the checked exception in the method.
     */
    @Test
    public void uncheck() {
        final Supplier<String> supplier = ThrowingSupplier.uncheck(() -> "Hello");
        assertEquals("Hello", supplier.get());
        final Supplier<Object> supplier1 = ThrowingSupplier.uncheck(() -> {
            throw new Exception();
        });
        assertThrows(Exception.class, supplier1::get);
    }
}