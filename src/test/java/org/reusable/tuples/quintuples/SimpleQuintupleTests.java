package org.reusable.tuples.quintuples;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleQuintupleTests {
    private SimpleQuintuple<String, Integer, Boolean, Character, Long> quintuple;

    @BeforeEach
    void setUp() {
        quintuple = SimpleQuintuple.of("Hello", 42, true, 'a', 123L);
    }

    @Test
    public void first() {
        assertEquals("Hello", quintuple.first());
    }

    @Test
    public void second() {
        assertEquals(42, quintuple.second());
    }

    @Test
    public void third() {
        assertEquals(true, quintuple.third());
    }

    @Test
    public void fourth() {
        assertEquals('a', quintuple.fourth());
    }

    @Test
    public void fifth() {
        assertEquals(123L, quintuple.fifth());
    }

    @Test
    public void mapFirst() {
        final Quintuple<String, Integer, Boolean, Character, Long> mappedQuintuple = quintuple.mapFirst(s -> s +
                " World");
        assertEquals("Hello World", mappedQuintuple.first());
        assertEquals(42, mappedQuintuple.second());
        assertEquals(true, mappedQuintuple.third());
    }

    @Test
    public void mapSecond() {
        final Quintuple<String, Integer, Boolean, Character, Long> mappedQuintuple = quintuple.mapSecond(i -> i + 1);
        assertEquals("Hello", mappedQuintuple.first());
        assertEquals(43, mappedQuintuple.second());
        assertEquals(true, mappedQuintuple.third());
    }

    @Test
    public void mapThird() {
        final Quintuple<String, Integer, Boolean, Character, Long> mappedQuintuple = quintuple.mapThird(b -> !b);
        assertEquals("Hello", mappedQuintuple.first());
        assertEquals(42, mappedQuintuple.second());
        assertEquals(false, mappedQuintuple.third());
    }

    @Test
    public void mapFourth() {
        final Quintuple<String, Integer, Boolean, Character, Long> mappedQuintuple = quintuple.mapFourth(c -> (char) (c +
                1));
        assertEquals("Hello", mappedQuintuple.first());
        assertEquals(42, mappedQuintuple.second());
        assertEquals(true, mappedQuintuple.third());
        assertEquals('b', mappedQuintuple.fourth());
    }

    @Test
    public void mapFifth() {
        final Quintuple<String, Integer, Boolean, Character, Long> mappedQuintuple = quintuple.mapFifth(l -> l + 1);
        assertEquals("Hello", mappedQuintuple.first());
        assertEquals(42, mappedQuintuple.second());
        assertEquals(true, mappedQuintuple.third());
        assertEquals('a', mappedQuintuple.fourth());
        assertEquals(124L, mappedQuintuple.fifth());
    }

    @Test
    public void map() {
        final Quintuple<String, Integer, Boolean, Character, Long> mappedQuintuple = quintuple.map(
                s -> s + " World",
                i -> i + 1,
                b -> !b,
                c -> (char) (c + 1),
                l -> l + 1
        );
        assertEquals("Hello World", mappedQuintuple.first());
        assertEquals(43, mappedQuintuple.second());
        assertEquals(false, mappedQuintuple.third());
        assertEquals('b', mappedQuintuple.fourth());
        assertEquals(124L, mappedQuintuple.fifth());
    }

    @Test
    public void bindFirst() {
        final Quintuple<String, Integer, Boolean, Character, Long> boundQuintuple =
                quintuple.bindFirst(s -> SimpleQuintuple.of(s + " World", 42, true, 'a', 123L));
        assertEquals("Hello World", boundQuintuple.first());
        assertEquals(42, boundQuintuple.second());
        assertEquals(true, boundQuintuple.third());
    }

    @Test
    public void bindSecond() {
        final Quintuple<String, Integer, Boolean, Character, Long> boundQuintuple =
                quintuple.bindSecond(i -> SimpleQuintuple.of("Hello", i + 1, true, 'a', 123L));
        assertEquals("Hello", boundQuintuple.first());
        assertEquals(43, boundQuintuple.second());
        assertEquals(true, boundQuintuple.third());
    }

    @Test
    public void bindThird() {
        final Quintuple<String, Integer, Boolean, Character, Long> boundQuintuple =
                quintuple.bindThird(b -> SimpleQuintuple.of("Hello", 42, !b, 'a', 123L));
        assertEquals("Hello", boundQuintuple.first());
        assertEquals(42, boundQuintuple.second());
        assertEquals(false, boundQuintuple.third());
    }

    @Test
    public void bindFourth() {
        final Quintuple<String, Integer, Boolean, Character, Long> boundQuintuple =
                quintuple.bindFourth(c -> SimpleQuintuple.of("Hello", 42, true, (char) (c + 1), 123L));
        assertEquals("Hello", boundQuintuple.first());
        assertEquals(42, boundQuintuple.second());
        assertEquals(true, boundQuintuple.third());
        assertEquals('b', boundQuintuple.fourth());
    }

    @Test
    public void bindFifth() {
        final Quintuple<String, Integer, Boolean, Character, Long> boundQuintuple =
                quintuple.bindFifth(l -> SimpleQuintuple.of("Hello", 42, true, 'a', l + 1));
        assertEquals("Hello", boundQuintuple.first());
        assertEquals(42, boundQuintuple.second());
        assertEquals(true, boundQuintuple.third());
        assertEquals('a', boundQuintuple.fourth());
        assertEquals(124L, boundQuintuple.fifth());
    }
}
