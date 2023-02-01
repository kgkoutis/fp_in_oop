package org.reusable.throwing;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class ThrowingFunctionTests {

    /**
     * Notice that we don't need to indicate the checked exception in the method.
     */
    @Test
    public void uncheck() {
        final StringBuilder stringBuilder = new StringBuilder();
        final Function<String, Character> function = ThrowingFunction.uncheck(s -> s.charAt(0));
        assertDoesNotThrow(() -> stringBuilder.append(function.apply("Hello")));
        assertEquals("H", stringBuilder.toString());
        final Function<String, Character> function1 = ThrowingFunction.uncheck(s -> {
            throw new Exception();
        });
        assertThrows(Exception.class, () -> stringBuilder.append(function1.apply("Hello")));
        assertEquals("H", stringBuilder.toString());
    }
}