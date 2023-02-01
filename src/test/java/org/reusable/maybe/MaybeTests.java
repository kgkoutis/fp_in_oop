package org.reusable.maybe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MaybeTests {

    @Test

    public void equality() {
        final Maybe<String> maybe1 = Maybe.just("Hello");
        final Maybe<String> maybe2 = Maybe.just("Hello");
        final Maybe<String> maybe3 = Maybe.just("World");
        final Maybe<String> maybe4 = Maybe.nothing();
        final Maybe<String> maybe5 = Maybe.nothing();
        final Maybe<Long> maybe6 = Maybe.nothing();
        assertEquals(maybe1, maybe2);
        assertNotEquals(maybe1, maybe3);
        assertNotEquals(maybe1, maybe4);
        assertNotEquals(maybe1, maybe5);
        assertEquals(maybe4, maybe5);
        assertEquals(maybe4, maybe6);
    }

    @Test
    public void nothing() {
        final Maybe<String> maybe = Maybe.nothing();
        final Maybe<Integer> maybe2 = Maybe.nothing();
        assertTrue(maybe.isNothing());
        assertFalse(maybe.isJust());
        assertEquals(Maybe.nothing(), maybe);
        assertSame(maybe, maybe2);
    }

    @Test
    public void just() {
        final Maybe<String> maybe = Maybe.just("Hello");
        assertTrue(maybe.isJust());
        assertFalse(maybe.isNothing());
        assertEquals(Maybe.just("Hello"), maybe);
    }

    @Test

    public void of() {
        final Maybe<String> maybe = Maybe.of("Hello");
        final Maybe<String> maybe2 = Maybe.of(null);
        assertTrue(maybe.isJust());
        assertFalse(maybe.isNothing());
        assertEquals(Maybe.just("Hello"), maybe);
        assertTrue(maybe2.isNothing());
        assertFalse(maybe2.isJust());
        assertEquals(Maybe.nothing(), maybe2);
    }

    @Test
    public void isJust() {
        final Maybe<String> maybe = Maybe.just("Hello");
        final Maybe<String> maybe2 = Maybe.nothing();
        assertTrue(maybe.isJust());
        assertFalse(maybe2.isJust());
    }

    @Test
    public void isNothing() {
        final Maybe<String> maybe = Maybe.just("Hello");
        final Maybe<String> maybe2 = Maybe.nothing();
        assertFalse(maybe.isNothing());
        assertTrue(maybe2.isNothing());
    }

    @Test
    public void ifJust() {
        final Maybe<String> maybe = Maybe.just("Hello");
        final Maybe<String> maybe2 = Maybe.nothing();
        final StringBuilder sb = new StringBuilder();
        maybe.ifJust(sb::append);
        assertEquals("Hello", sb.toString());
        maybe2.ifJust(sb::append);
        assertEquals("Hello", sb.toString()); // no change
    }

    @Test
    public void ifNothing() {
        final Maybe<String> maybe = Maybe.just("Hello");
        final Maybe<String> maybe2 = Maybe.nothing();
        final StringBuilder sb = new StringBuilder();
        maybe.ifNothing(() -> sb.append("Nothing"));
        assertEquals("", sb.toString());
        maybe2.ifNothing(() -> sb.append("Nothing"));
        assertEquals("Nothing", sb.toString());
    }

    @Test
    public void map() {
        final Maybe<String> maybe = Maybe.just("Hello");
        final Maybe<String> maybe2 = Maybe.nothing();
        final Maybe<Integer> mapped = maybe.map(String::length);
        final Maybe<Integer> mapped2 = maybe2.map(String::length);
        assertEquals(Maybe.just(5), mapped);
        assertEquals(Maybe.nothing(), mapped2);
    }

    @Test
    public void bind() {
        final Maybe<String> maybe = Maybe.just("Hello");
        final Maybe<String> maybe2 = Maybe.nothing();
        final Maybe<Integer> mapped = maybe.bind(s -> Maybe.just(s.length()));
        final Maybe<Integer> mapped2 = maybe2.bind(s -> Maybe.just(s.length()));
        assertEquals(Maybe.just(5), mapped);
        assertEquals(Maybe.nothing(), mapped2);
    }

    @Test
    public void toOptional() {
        final Maybe<String> maybe = Maybe.just("Hello");
        final Maybe<String> maybe2 = Maybe.nothing();
        assertEquals(java.util.Optional.of("Hello"), maybe.toOptional());
        assertEquals(java.util.Optional.empty(), maybe2.toOptional());
    }

    @Test
    public void match() {
        final Maybe<String> maybe = Maybe.just("Hello");
        final Maybe<String> maybe2 = Maybe.nothing();
        final String result = maybe.match(
                s -> s + " World",
                "Should not be called"
        );
        final String result2 = maybe2.match(
                s -> "Should not be called",
                "Nothing"
        );
        assertEquals("Hello World", result);
        assertEquals("Nothing", result2);
    }

    @Test
    public void handle() {
        final Maybe<String> maybe = Maybe.just("Hello");
        final Maybe<String> maybe2 = Maybe.nothing();
        final StringBuilder sb = new StringBuilder();
        maybe.handle(
                sb::append,
                () -> sb.append("Nothing")
        );
        assertEquals("Hello", sb.toString());
        maybe2.handle(
                sb::append,
                () -> sb.append("Nothing")
        );
        assertEquals("HelloNothing", sb.toString());
    }
}
