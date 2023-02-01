package org.reusable.either;

import org.junit.jupiter.api.Test;
import org.reusable.Unit;

import static org.junit.jupiter.api.Assertions.*;

public class EitherTests {
    @Test
    public void equality() {
        final Either<String, Integer> either1 = Either.left("Hello");
        final Either<String, Integer> either2 = Either.left("Hello");
        final Either<String, Integer> either3 = Either.left("World");
        final Either<String, Integer> either4 = Either.right(42);
        final Either<String, Integer> either5 = Either.right(42);
        final Either<String, Integer> either6 = Either.right(43);
        final Either<String, Long> either7 = Either.right(42L);
        assertEquals(either1, either2);
        assertNotEquals(either1, either3);
        assertNotEquals(either1, either4);
        assertNotEquals(either1, either5);
        assertEquals(either4, either5);
        assertNotEquals(either4, either6);
        assertNotEquals(either4, either7);
    }

    @Test
    public void left() {
        final Either<String, Integer> either = Either.left("Hello");
        final Either<String, Integer> either2 = Either.left("Hello");
        assertTrue(either.isLeft());
        assertFalse(either.isRight());
        assertEquals(Either.left("Hello"), either);
        assertNotSame(either, either2);
    }

    @Test
    public void right() {
        final Either<String, Integer> either = Either.right(42);
        assertTrue(either.isRight());
        assertFalse(either.isLeft());
        assertEquals(Either.right(42), either);
    }

    @Test
    public void isLeft() {
        final Either<String, Integer> either = Either.left("Hello");
        final Either<String, Integer> either2 = Either.right(42);
        assertTrue(either.isLeft());
        assertFalse(either.isRight());
        assertTrue(either2.isRight());
        assertFalse(either2.isLeft());
    }

    @Test
    public void isRight() {
        final Either<String, Integer> either = Either.left("Hello");
        final Either<String, Integer> either2 = Either.right(42);
        assertTrue(either.isLeft());
        assertFalse(either.isRight());
        assertTrue(either2.isRight());
        assertFalse(either2.isLeft());
    }

    @Test
    public void mapLeft() {
        final Either<String, Integer> either = Either.left("Hello");
        final Either<String, Integer> either2 = Either.right(42);
        final Either<Character, Integer> either3 = either.mapLeft(s -> s.charAt(0));
        final Either<Character, Integer> either4 = either2.mapLeft(s -> s.charAt(0));
        assertEquals(Either.left('H'), either3);
        assertEquals(Either.right(42), either4);
    }

    @Test
    public void mapRight() {
        final Either<String, Integer> either = Either.left("Hello");
        final Either<String, Integer> either2 = Either.right(42);
        final Either<String, Double> either3 = either.mapRight(i -> Double.valueOf(i + 1));
        final Either<String, Double> either4 = either2.mapRight(i -> Double.valueOf(i + 1));
        assertEquals(Either.left("Hello"), either3);
        assertEquals(Either.right(43.0), either4);
    }

    @Test
    public void biMap() {
        final Either<String, Integer> either = Either.left("Hello");
        final Either<String, Integer> either2 = Either.right(42);
        final Either<String, Integer> either3 = either.biMap(s -> s + " World", i -> i + 1);
        final Either<String, Integer> either4 = either2.biMap(s -> s + " World", i -> i + 1);
        assertEquals(Either.left("Hello World"), either3);
        assertEquals(Either.right(43), either4);
    }

    @Test
    public void bindLeft() {
        final Either<String, Integer> either = Either.left("Hello");
        final Either<String, Integer> either2 = Either.right(42);
        final Either<Integer, Integer> either3 = either.bindLeft(s -> Either.left(s.length()));
        final Either<Integer, Integer> either4 = either2.bindLeft(s -> Either.left(s.length()));
        assertEquals(Either.left(5), either3);
        assertEquals(Either.right(42), either4);
    }

    @Test
    public void bindRight() {
        final Either<String, Integer> either = Either.left("Hello");
        final Either<String, Integer> either2 = Either.right(42);
        final Either<String, Integer> either3 = either.bindRight(i -> Either.right(i + 1));
        final Either<String, Integer> either4 = either2.bindRight(i -> Either.right(i + 1));
        assertEquals(Either.left("Hello"), either3);
        assertEquals(Either.right(43), either4);
    }

    @Test
    public void biBind() {
        final Either<String, Integer> either = Either.left("Hello");
        final Either<String, Integer> either2 = Either.right(42);
        final Either<String, Integer> either3 = either.biBind(s -> Either.left(s + " World"), i -> Either.right(i + 1));
        final Either<String, Integer> either4 = either2.biBind(s -> Either.left(s + " World"), i -> Either.right(i + 1));
        assertEquals(Either.left("Hello World"), either3);
        assertEquals(Either.right(43), either4);
    }

    @Test
    public void ifRight() {
        final StringBuilder sb = new StringBuilder();
        final Either<String, Integer> either = Either.left("Hello");
        final Either<String, Integer> either2 = Either.right(42);
        final Unit _1 = either.ifRight(sb::append);
        final Unit _2 = either2.ifRight(sb::append);
        assertEquals("42", sb.toString());
    }

    @Test
    public void ifLeft() {
        final StringBuilder sb = new StringBuilder();
        final Either<String, Integer> either = Either.left("Hello");
        final Either<String, Integer> either2 = Either.right(42);
        final Unit _1 = either.ifLeft(sb::append);
        final Unit _2 = either2.ifLeft(sb::append);
        assertEquals("Hello", sb.toString());
    }

    @Test
    public void match() {
        final Either<Character, Integer> either = Either.left('H');
        final Either<String, Integer> either2 = Either.right(42);
        final String s1 = either.match(s -> "" + s, Object::toString);
        final String s2 = either2.match(s -> s, Object::toString);
        assertEquals("H", s1);
        assertEquals("42", s2);
    }

    @Test
    public void handle() {
        final StringBuilder sb1 = new StringBuilder();
        final StringBuilder sb2 = new StringBuilder();
        final Either<String, Integer> either = Either.left("Hello");
        final Either<String, Integer> either2 = Either.right(42);
        final Unit _1 = either.handle(sb1::append, (i) -> sb1.append("a number"));
        final Unit _2 = either2.handle(sb2::append, (i) -> sb2.append("a number"));
        assertEquals("Hello", sb1.toString());
        assertEquals("a number", sb2.toString());
    }
}
