package org.reusable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ResultTests {

    @Test
    public void equality()
    {
        final Result<String, IOException> result1 = Result.success("Hello");
        final Result<String, IOException> result2 = Result.success("Hello");
        final Result<String, IOException> result3 = Result.success("World");
        final Result<String, IOException> result4 = Result.failure(new IOException("Hello"));
        final Result<String, IOException> result5 = Result.failure(new IOException("Hello"));
        final Result<String, IOException> result6 = Result.failure(new IOException("World"));
        assertEquals(result1, result2);
        assertNotEquals(result1, result3);
        assertNotEquals(result1, result4);
        assertNotEquals(result1, result5);
        assertNotEquals(result1, result6);
        assertNotEquals(result4, result5);
        assertNotEquals(result4, result6);
    }

    @Test
    public void success() {
        final Result<String, IOException> result = Result.success("Hello");
        assertTrue(result.isSuccess());
        assertFalse(result.isFailure());
        assertEquals("Hello", result.value());
        assertNull(result.throwable());
        assertEquals("Hello", result.orElse("World"));
        assertEquals("Hello", result.orElseGet(() -> "World"));
        assertEquals("Hello", result.orElseThrow());
    }

    @Test
    public void failure() {
        final IOException exception = new IOException("Hello");
        final Result<String, IOException> result = Result.failure(exception);
        assertFalse(result.isSuccess());
        assertTrue(result.isFailure());
        assertEquals(exception, result.throwable());
        assertEquals("World", result.orElse("World"));
        assertEquals("World", result.orElseGet(() -> "World"));
        assertEquals(exception, result.throwable());
        assertThrows(IOException.class, result::value);
    }

    @Test
    public void matchSuccess() {
        final Optional<String> result = Result.success("Hello").matchSuccess(s -> s + " World");
        assertEquals(Optional.of("Hello World"), result);
        final Optional<String> result2 = Result.failure(new IOException("Boom")).matchSuccess(s -> s + " World");
        assertEquals(Optional.empty(), result2);
    }

    @Test
    public void matchFailure() {
        final Optional<String> result = Result.success("Hello")
                .matchFailure(e -> "Exception message was: " + e.getMessage());
        assertEquals(Optional.empty(), result);
        final Optional<String> result2 = Result.failure(new IOException("Boom"))
                .matchFailure(e -> "Exception message was: " + e.getMessage());
        assertEquals(Optional.of("Exception message was: Boom"), result2);
    }

    @Test
    public void match() {
        final String result = Result.success("Hello")
                .match(s -> s + " World", e -> "Exception message was: " + e.getMessage());
        assertEquals("Hello World", result);
        final String result2 = Result.failure(new IOException("Boom"))
                .match(s -> s + " World", e -> "Exception message was: " + e.getMessage());
        assertEquals("Exception message was: Boom", result2);
    }

    @Test
    public void ifSuccess() {
        final StringBuilder sb = new StringBuilder();
        Result.success("Hello").ifSuccess(s -> sb.append(s).append(" World"));
        assertEquals("Hello World", sb.toString());
        Result.failure(new IOException("Boom")).ifSuccess(s -> sb.append(s).append(" World"));
        assertEquals("Hello World", sb.toString());
    }

    @Test
    public void ifFailure() {
        final StringBuilder sb = new StringBuilder();
        Result.success("Hello").ifFailure(e -> sb.append("Exception message was: ").append(e.getMessage()));
        assertEquals("", sb.toString());
        Result.failure(new IOException("Boom"))
                .ifFailure(e -> sb.append("Exception message was: ").append(e.getMessage()));
        assertEquals("Exception message was: Boom", sb.toString());
    }

    @Test
    public void handle() {
        final StringBuilder sb = new StringBuilder();
        Result.success("Hello")
                .handle(s -> sb.append(s).append(" World"), e -> {
                    sb.append("Exception message was: ").append(e.getMessage());
                });
        assertEquals("Hello World", sb.toString());

        final StringBuilder sb1 = new StringBuilder();
        Result.failure(new IOException("Boom"))
                .handle(s -> sb1.append(s).append("Not boom"), e -> {
                    sb1.append("Exception message was: ").append(e.getMessage());
                });
        assertEquals("Exception message was: Boom", sb1.toString());
    }
}
