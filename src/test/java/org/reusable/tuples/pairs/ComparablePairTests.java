package org.reusable.tuples.pairs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class ComparablePairTests {
    private ComparablePair<String, Integer> pair;

    @BeforeEach
    void setUp() {
        pair = ComparablePair.of("Hello", 42);
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

    @Test
    public void compare() {
        // equal ordering
        final ComparablePair<String, Integer> pair2 = ComparablePair.of("Hello", 42);
        assertEquals(0, pair.compareTo(pair2));
        assertEquals(0, pair2.compareTo(pair));
        // first comparator check
        final ComparablePair<String, Integer> pair3 = ComparablePair.of("Hello World", 42);
        assertTrue(pair.compareTo(pair3) < 0);
        assertTrue(pair3.compareTo(pair) > 0);
        // second comparator check
        final ComparablePair<String, Integer> pair4 = ComparablePair.of("Hello", 43);
        assertTrue(pair.compareTo(pair4) < 0);
        assertTrue(pair4.compareTo(pair) > 0);
        // first comparator has priority over second
        final ComparablePair<String, Integer> pair5 = ComparablePair.of("Hello World", 38);
        assertTrue(pair.compareTo(pair5) < 0);
        assertTrue(pair5.compareTo(pair) > 0);
    }

    /***
     * This test shows how to use a custom comparator for the first element of the pair.
     * NOTE: both pairs should have the same comparators!
     */
    @Test
    public void customFirstComparison() {

        // first comparator is custom
        final Comparator<String> stringComparator = String.CASE_INSENSITIVE_ORDER; // custom comparison
        final Comparator<Integer> integerComparator = Integer::compare; // normal

        final ComparablePair<String, Integer> pair = ComparablePair.of(SimplePair.of("Hello", 42), stringComparator, integerComparator);

        // first comparison works
        final ComparablePair<String, Integer> pair2 = ComparablePair.of(SimplePair.of("hello", 42), stringComparator, integerComparator);
        assertEquals(0, pair.compareTo(pair2));
        assertEquals(0, pair2.compareTo(pair));
        final ComparablePair<String, Integer> pair3 = ComparablePair.of(SimplePair.of("hello", 43), stringComparator, integerComparator);
        // second comparison works also
        assertTrue(pair.compareTo(pair3) < 0);
        assertTrue(pair3.compareTo(pair) > 0);
        // first comparison has priority over second comparison
        final ComparablePair<String, Integer> pair4 = ComparablePair.of(SimplePair.of("Hello World", 41), stringComparator, integerComparator);
        assertTrue(pair.compareTo(pair4) < 0);
        assertTrue(pair4.compareTo(pair) > 0);
    }

    /***
     * This test shows how to use a custom comparator for the first element of the pair.
     * NOTE: both pairs should have the same comparators!
     */
    @Test
    public void customSecondComparison() {
        // second comparator is custom
        final Comparator<String> stringComparator = String::compareTo; // normal
        final Comparator<Integer> integerComparator = (i, j) -> Integer.compare(j, i); // custom comparison, reverse

        final ComparablePair<String, Integer> pair5 = ComparablePair.of(SimplePair.of("hello", 42), stringComparator, integerComparator);

        // first comparison works
        final ComparablePair<String, Integer> pair6 = ComparablePair.of(SimplePair.of("hello", 42), stringComparator, integerComparator);
        assertEquals(0, pair5.compareTo(pair6));
        assertEquals(0, pair6.compareTo(pair5));
        // second comparison works also
        final ComparablePair<String, Integer> pair7 = ComparablePair.of(SimplePair.of("hello", 43), stringComparator, integerComparator);
        assertTrue(pair5.compareTo(pair7) > 0);
        assertTrue(pair7.compareTo(pair5) < 0);
        // first comparison has priority over second comparison
        final ComparablePair<String, Integer> pair8 = ComparablePair.of(SimplePair.of("hello world", 44), stringComparator, integerComparator);
        assertTrue(pair5.compareTo(pair8) < 0);
        assertTrue(pair8.compareTo(pair5) > 0);
    }

    /***
     * This test shows how to use a custom comparator for the first element of the pair.
     * NOTE: both pairs should have the same comparators!
     */
    @Test
    public void customBothComparisons() {
        // both comparators are custom
        final Comparator<String> stringComparator = String.CASE_INSENSITIVE_ORDER; // custom comparison
        final Comparator<Integer> integerComparator = (i, j) -> Integer.compare(j, i); // custom comparison, reverse

        final ComparablePair<String, Integer> pair9 = ComparablePair.of(SimplePair.of("Hello", 42), stringComparator, integerComparator);

        // first comparison works
        final ComparablePair<String, Integer> pair10 = ComparablePair.of(SimplePair.of("hello", 42), stringComparator, integerComparator);
        assertEquals(0, pair9.compareTo(pair10));
        assertEquals(0, pair10.compareTo(pair9));
        // second comparison works also
        final ComparablePair<String, Integer> pair11 = ComparablePair.of(SimplePair.of("hello", 43), stringComparator, integerComparator);
        assertTrue(pair9.compareTo(pair11) > 0);
        assertTrue(pair11.compareTo(pair9) < 0);
        final ComparablePair<String, Integer> pair12 = ComparablePair.of(SimplePair.of("hello world", 44), stringComparator, integerComparator);
        assertTrue(pair9.compareTo(pair12) < 0);
        assertTrue(pair12.compareTo(pair9) > 0);
    }
}
