package org.reusable.tuples.quadruples;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleQuadrupleTests {
    private SimpleQuadruple<String, Integer, Boolean, Character> quadruple;

    @BeforeEach
    void setUp() {
        quadruple = SimpleQuadruple.of("Hello", 42, true, 'a');
    }

    @Test
    public void first() {
        assertEquals("Hello", quadruple.first());
    }

    @Test
    public void second() {
        assertEquals(42, quadruple.second());
    }

    @Test
    public void third() {
        assertEquals(true, quadruple.third());
    }

    @Test
    public void fourth() {
        assertEquals('a', quadruple.fourth());
    }

    @Test
    public void mapFirst() {
        final Quadruple<String, Integer, Boolean, Character> mappedQuadruple = quadruple.mapFirst(s -> s + " World");
        assertEquals("Hello World", mappedQuadruple.first());
        assertEquals(42, mappedQuadruple.second());
        assertEquals(true, mappedQuadruple.third());
    }

    @Test
    public void mapSecond() {
        final Quadruple<String, Integer, Boolean, Character> mappedQuadruple = quadruple.mapSecond(i -> i + 1);
        assertEquals("Hello", mappedQuadruple.first());
        assertEquals(43, mappedQuadruple.second());
        assertEquals(true, mappedQuadruple.third());
    }

    @Test
    public void mapThird() {
        final Quadruple<String, Integer, Boolean, Character> mappedQuadruple = quadruple.mapThird(b -> !b);
        assertEquals("Hello", mappedQuadruple.first());
        assertEquals(42, mappedQuadruple.second());
        assertEquals(false, mappedQuadruple.third());
    }

    @Test
    public void mapFourth() {
        final Quadruple<String, Integer, Boolean, Character> mappedQuadruple = quadruple.mapFourth(c -> 'b');
        assertEquals("Hello", mappedQuadruple.first());
        assertEquals(42, mappedQuadruple.second());
        assertEquals(true, mappedQuadruple.third());
        assertEquals('b', mappedQuadruple.fourth());
    }

    @Test
    public void map() {
        final Quadruple<String, Integer, Boolean, Character> mappedQuadruple = quadruple.map(s -> s + " World", i -> i +
                1, b -> !b, c -> c);
        assertEquals("Hello World", mappedQuadruple.first());
        assertEquals(43, mappedQuadruple.second());
        assertEquals(false, mappedQuadruple.third());
    }

    @Test
    public void bindFirst() {
        final Quadruple<String, Integer, Boolean, Character> mappedQuadruple = quadruple.bindFirst(s -> SimpleQuadruple.of(
                s +
                        " World", 43, false, 'a'));
        assertEquals("Hello World", mappedQuadruple.first());
        assertEquals(43, mappedQuadruple.second());
        assertEquals(false, mappedQuadruple.third());
    }

    @Test
    public void bindSecond() {
        final Quadruple<String, Integer, Boolean, Character> mappedQuadruple = quadruple.bindSecond(i -> SimpleQuadruple.of("Hello World",
                                                                                                                            i +
                                                                                                                                    1, false, 'a'));
        assertEquals("Hello World", mappedQuadruple.first());
        assertEquals(43, mappedQuadruple.second());
        assertEquals(false, mappedQuadruple.third());
    }

    @Test
    public void bindThird() {
        final Quadruple<String, Integer, Boolean, Character> mappedQuadruple = quadruple.bindThird(b -> SimpleQuadruple.of("Hello World", 43, !b, 'a'));
        assertEquals("Hello World", mappedQuadruple.first());
        assertEquals(43, mappedQuadruple.second());
        assertEquals(false, mappedQuadruple.third());
    }

    @Test
    public void bindFourth() {
        final Quadruple<String, Integer, Boolean, Character> mappedQuadruple = quadruple.bindFourth(c -> SimpleQuadruple.of("Hello World", 43, false, 'b'));
        assertEquals("Hello World", mappedQuadruple.first());
        assertEquals(43, mappedQuadruple.second());
        assertEquals(false, mappedQuadruple.third());
        assertEquals('b', mappedQuadruple.fourth());
    }
}