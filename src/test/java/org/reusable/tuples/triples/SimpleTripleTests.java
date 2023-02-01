package org.reusable.tuples.triples;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleTripleTests {
    private SimpleTriple<String, Integer, Boolean> triple;

    @BeforeEach
    void setUp() {
        triple = SimpleTriple.of("Hello", 42, true);
    }

    @Test
    public void first() {
        assertEquals("Hello", triple.first());
    }

    @Test
    public void second() {
        assertEquals(42, triple.second());
    }

    @Test
    public void third() {
        assertEquals(true, triple.third());
    }

    @Test
    public void mapFirst() {
        final Triple<String, Integer, Boolean> mappedTriple = triple.mapFirst(s -> s + " World");
        assertEquals("Hello World", mappedTriple.first());
        assertEquals(42, mappedTriple.second());
        assertEquals(true, mappedTriple.third());
    }

    @Test
    public void mapSecond() {
        final Triple<String, Integer, Boolean> mappedTriple = triple.mapSecond(i -> i + 1);
        assertEquals("Hello", mappedTriple.first());
        assertEquals(43, mappedTriple.second());
        assertEquals(true, mappedTriple.third());
    }

    @Test
    public void mapThird() {
        final Triple<String, Integer, Boolean> mappedTriple = triple.mapThird(b -> !b);
        assertEquals("Hello", mappedTriple.first());
        assertEquals(42, mappedTriple.second());
        assertEquals(false, mappedTriple.third());
    }

    @Test
    public void map() {
        final Triple<String, Integer, Boolean> mappedTriple = triple.map(s -> s + " World", i -> i + 1, b -> !b);
        assertEquals("Hello World", mappedTriple.first());
        assertEquals(43, mappedTriple.second());
        assertEquals(false, mappedTriple.third());
    }

    @Test
    public void bindFirst() {
        final Triple<String, Integer, Boolean> mappedTriple = triple.bindFirst(s -> SimpleTriple.of(s +
                                                                                                            " World", 43, false));
        assertEquals("Hello World", mappedTriple.first());
        assertEquals(43, mappedTriple.second());
        assertEquals(false, mappedTriple.third());
    }

    @Test
    public void bindSecond() {
        final Triple<String, Integer, Boolean> mappedTriple = triple.bindSecond(i -> SimpleTriple.of("Hello World", i +
                1, false));
        assertEquals("Hello World", mappedTriple.first());
        assertEquals(43, mappedTriple.second());
        assertEquals(false, mappedTriple.third());
    }

    @Test
    public void bindThird() {
        final Triple<String, Integer, Boolean> mappedTriple = triple.bindThird(b -> SimpleTriple.of("Hello World", 43, !b));
        assertEquals("Hello World", mappedTriple.first());
        assertEquals(43, mappedTriple.second());
        assertEquals(false, mappedTriple.third());
    }
}