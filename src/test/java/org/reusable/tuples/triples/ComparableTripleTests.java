package org.reusable.tuples.triples;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class ComparableTripleTests {
    private ComparableTriple<String, Integer, Boolean> triple;

    @BeforeEach
    void setUp() {
        triple = ComparableTriple.of("Hello", 42, true);
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
        assertTrue(triple.third());
    }

    @Test
    public void mapFirst() {
        final Triple<String, Integer, Boolean> mappedTriple = triple.mapFirst(s -> s + " World");
        assertEquals("Hello World", mappedTriple.first());
        assertEquals(42, mappedTriple.second());
        assertTrue(mappedTriple.third());
    }

    @Test
    public void mapSecond() {
        final Triple<String, Integer, Boolean> mappedTriple = triple.mapSecond(i -> i + 1);
        assertEquals("Hello", mappedTriple.first());
        assertEquals(43, mappedTriple.second());
        assertTrue(mappedTriple.third());
    }

    @Test
    public void mapThird() {
        final Triple<String, Integer, Boolean> mappedTriple = triple.mapThird(b -> !b);
        assertEquals("Hello", mappedTriple.first());
        assertEquals(42, mappedTriple.second());
        assertFalse(mappedTriple.third());
    }

    @Test
    public void map() {
        final Triple<String, Integer, Boolean> mappedTriple = triple.map(s -> s + " World", i -> i + 1, b -> !b);
        assertEquals("Hello World", mappedTriple.first());
        assertEquals(43, mappedTriple.second());
        assertFalse(mappedTriple.third());
    }

    @Test
    public void bindFirst() {
        final Triple<String, Integer, Boolean> mappedTriple =
                triple.bindFirst(s -> SimpleTriple.of(s + " World", 43, false));
        assertEquals("Hello World", mappedTriple.first());
        assertEquals(43, mappedTriple.second());
        assertFalse(mappedTriple.third());
    }

    @Test
    public void bindSecond() {
        final Triple<String, Integer, Boolean> mappedTriple =
                triple.bindSecond(i -> SimpleTriple.of("Hello World", i + 1, false));
        assertEquals("Hello World", mappedTriple.first());
        assertEquals(43, mappedTriple.second());
        assertFalse(mappedTriple.third());
    }

    @Test
    public void bindThird() {
        final Triple<String, Integer, Boolean> mappedTriple =
                triple.bindThird(b -> SimpleTriple.of("Hello World", 43, !b));
        assertEquals("Hello World", mappedTriple.first());
        assertEquals(43, mappedTriple.second());
        assertFalse(mappedTriple.third());
    }

    @Test
    public void compare() {
        // equal ordering
        final ComparableTriple<String, Integer, Boolean> triple2 = ComparableTriple.of("Hello", 42, true);
        assertEquals(0, triple.compareTo(triple2));
        assertEquals(0, triple2.compareTo(triple));
        // first comparator check
        final ComparableTriple<String, Integer, Boolean> triple3 = ComparableTriple.of("Hello World", 42, true);
        assertTrue(triple.compareTo(triple3) < 0);
        assertTrue(triple3.compareTo(triple) > 0);
        // second comparator check
        final ComparableTriple<String, Integer, Boolean> triple4 = ComparableTriple.of("Hello", 43, true);
        assertTrue(triple.compareTo(triple4) < 0);
        assertTrue(triple4.compareTo(triple) > 0);
        // third comparator check
        final ComparableTriple<String, Integer, Boolean> triple5 = ComparableTriple.of("Hello", 42, false);
        assertTrue(triple.compareTo(triple5) > 0);
        assertTrue(triple5.compareTo(triple) < 0);
        // first comparator has priority over second and third
        final ComparableTriple<String, Integer, Boolean> triple6 = ComparableTriple.of("Hello World", 43, false);
        assertTrue(triple.compareTo(triple6) < 0);
        assertTrue(triple6.compareTo(triple) > 0);
        // second comparator has priority over third
        final ComparableTriple<String, Integer, Boolean> triple7 = ComparableTriple.of("Hello", 43, false);
        assertTrue(triple.compareTo(triple7) < 0);
        assertTrue(triple7.compareTo(triple) > 0);
    }

    /***
     * This test shows how to use a custom comparator for the first element of the triple.
     * NOTE: both triples should have the same comparators!
     */
    @Test
    void customFirstComparison() {
        // first comparator is custom
        final Comparator<String> stringComparator = String.CASE_INSENSITIVE_ORDER; // custom comparison
        final Comparator<Integer> integerComparator = Integer::compare; // normal comparison
        final Comparator<Boolean> booleanComparator = Boolean::compare; // normal comparison

        final ComparableTriple<String, Integer, Boolean> triple1 = ComparableTriple.of("Hello", 42, true, stringComparator, integerComparator, booleanComparator);

        // first comparison works
        final ComparableTriple<String, Integer, Boolean> triple2 = ComparableTriple.of("hello", 42, true, stringComparator, integerComparator, booleanComparator);
        assertEquals(0, triple1.compareTo(triple2));
        assertEquals(0, triple2.compareTo(triple1));
        // second comparison works
        final ComparableTriple<String, Integer, Boolean> triple3 = ComparableTriple.of("Hello", 43, true, stringComparator, integerComparator, booleanComparator);
        assertTrue(triple1.compareTo(triple3) < 0);
        assertTrue(triple3.compareTo(triple1) > 0);
        // third comparison works
        final ComparableTriple<String, Integer, Boolean> triple4 = ComparableTriple.of("Hello", 42, false, stringComparator, integerComparator, booleanComparator);
        assertTrue(triple1.compareTo(triple4) > 0);
        assertTrue(triple4.compareTo(triple1) < 0);
        // first comparison has priority over second and third comparison
        final ComparableTriple<String, Integer, Boolean> triple5 = ComparableTriple.of("hello world", 30, false, stringComparator, integerComparator, booleanComparator);
        assertTrue(triple1.compareTo(triple5) < 0);
        assertTrue(triple5.compareTo(triple1) > 0);
        // second comparison has priority over third comparison
        final ComparableTriple<String, Integer, Boolean> triple6 = ComparableTriple.of("Hello", 43, false, stringComparator, integerComparator, booleanComparator);
        assertTrue(triple1.compareTo(triple6) < 0);
        assertTrue(triple6.compareTo(triple1) > 0);
    }

    /***
     * This test shows how to use a custom comparator for the first element of the triple.
     * NOTE: both triples should have the same comparators!
     */
    @Test
    void customSecondComparison() {
        // second comparator is custom
        final Comparator<String> stringComparator = String::compareTo; // normal comparison
        final Comparator<Integer> integerComparator = (i, j) -> Integer.compare(j, i); // custom comparison, reverse
        final Comparator<Boolean> booleanComparator = Boolean::compare; // normal comparison

        final ComparableTriple<String, Integer, Boolean> triple1 = ComparableTriple.of("Hello", 42, true, stringComparator, integerComparator, booleanComparator);

        // first comparison works
        final ComparableTriple<String, Integer, Boolean> triple2 = ComparableTriple.of("hello", 42, true, stringComparator, integerComparator, booleanComparator);
        assertTrue(triple1.compareTo(triple2) < 0);
        assertTrue(triple2.compareTo(triple1) > 0);
        // second comparison works
        final ComparableTriple<String, Integer, Boolean> triple3 = ComparableTriple.of("Hello", 43, true, stringComparator, integerComparator, booleanComparator);
        assertTrue(triple1.compareTo(triple3) > 0);
        assertTrue(triple3.compareTo(triple1) < 0);
        // third comparison works
        final ComparableTriple<String, Integer, Boolean> triple4 = ComparableTriple.of("Hello", 42, false, stringComparator, integerComparator, booleanComparator);
        assertTrue(triple1.compareTo(triple4) > 0);
        assertTrue(triple4.compareTo(triple1) < 0);
        // first comparison has priority over second and third comparison
        final ComparableTriple<String, Integer, Boolean> triple5 = ComparableTriple.of("hello world", 30, false, stringComparator, integerComparator, booleanComparator);
        assertTrue(triple1.compareTo(triple5) < 0);
        assertTrue(triple5.compareTo(triple1) > 0);
        // second comparison has priority over third comparison
        final ComparableTriple<String, Integer, Boolean> triple6 = ComparableTriple.of("Hello", 43, false, stringComparator, integerComparator, booleanComparator);
        assertTrue(triple1.compareTo(triple6) > 0);
        assertTrue(triple6.compareTo(triple1) < 0);
    }

    /***
     * This test shows how to use a custom comparator for the first element of the triple.
     * NOTE: both triples should have the same comparators!
     */
    @Test
    void customThirdComparison() {
        // third comparator is custom
        final Comparator<String> stringComparator = String::compareTo; // normal comparison
        final Comparator<Integer> integerComparator = Integer::compareTo; // normal comparison
        final Comparator<Boolean> booleanComparator = (a, b) -> Boolean.compare(b, a); // custom comparison, reverse

        final ComparableTriple<String, Integer, Boolean> triple1 = ComparableTriple.of("Hello", 42, true, stringComparator, integerComparator, booleanComparator);
        // first comparison works
        final ComparableTriple<String, Integer, Boolean> triple2 = ComparableTriple.of("hello", 42, true, stringComparator, integerComparator, booleanComparator);
        assertTrue(triple1.compareTo(triple2) < 0);
        assertTrue(triple2.compareTo(triple1) > 0);
        // second comparison works
        final ComparableTriple<String, Integer, Boolean> triple3 = ComparableTriple.of("Hello", 43, true, stringComparator, integerComparator, booleanComparator);
        assertTrue(triple1.compareTo(triple3) < 0);
        assertTrue(triple3.compareTo(triple1) > 0);
        // third comparison works
        final ComparableTriple<String, Integer, Boolean> triple4 = ComparableTriple.of("Hello", 42, false, stringComparator, integerComparator, booleanComparator);
        assertTrue(triple1.compareTo(triple4) < 0);
        assertTrue(triple4.compareTo(triple1) > 0);
        // first comparison has priority over second and third comparison
        final ComparableTriple<String, Integer, Boolean> triple5 = ComparableTriple.of("hello world", 30, false, stringComparator, integerComparator, booleanComparator);
        assertTrue(triple1.compareTo(triple5) < 0);
        assertTrue(triple5.compareTo(triple1) > 0);
        // second comparison has priority over third comparison
        final ComparableTriple<String, Integer, Boolean> triple6 = ComparableTriple.of("Hello", 43, false, stringComparator, integerComparator, booleanComparator);
        assertTrue(triple1.compareTo(triple6) < 0);
        assertTrue(triple6.compareTo(triple1) > 0);
    }
}
