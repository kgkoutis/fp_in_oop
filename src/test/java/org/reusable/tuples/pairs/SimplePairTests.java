package org.reusable.tuples.pairs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimplePairTests {

    private SimplePair<String, Integer> pair;

    @BeforeEach
    void setUp() {
        pair = SimplePair.of("Hello", 42);
    }

    @Test
    public void first() {
        assertEquals("Hello", pair.first());
    }

    @Test
    public void second() {
        assertEquals(42, pair.second());
    }

    @Test
    public void mapFirst() {
        final Pair<String, Integer> mappedPair = pair.mapFirst(s -> s + " World");
        assertEquals("Hello World", mappedPair.first());
        assertEquals(42, mappedPair.second());
    }

    @Test
    public void mapSecond() {
        final Pair<String, Integer> mappedPair = pair.mapSecond(i -> i + 1);
        assertEquals("Hello", mappedPair.first());
        assertEquals(43, mappedPair.second());
    }

    @Test
    public void map() {
        final Pair<String, Integer> mappedPair = pair.map(s -> s + " World", i -> i + 1);
        assertEquals("Hello World", mappedPair.first());
        assertEquals(43, mappedPair.second());
    }

    @Test
    public void bindFirst() {
        final Pair<String, Integer> mappedPair = pair.bindFirst(s -> SimplePair.of(s + " World", 43));
        assertEquals("Hello World", mappedPair.first());
        assertEquals(43, mappedPair.second());
    }

    @Test
    public void bindSecond() {
        final Pair<String, Integer> mappedPair = pair.bindSecond(i -> SimplePair.of("Hello World", i + 1));
        assertEquals("Hello World", mappedPair.first());
        assertEquals(43, mappedPair.second());
    }
}