package org.reusable.tuples.quintuples;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComparableQuintupleTests {

    private ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple;

    @BeforeEach
    void setUp() {
        quintuple = ComparableQuintuple.of("Hello", 42, true, 'a', 123L);
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
        assertEquals('a', 123L, quintuple.fourth());
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
        assertEquals(124L, mappedQuintuple.fifth());
    }

    @Test
    public void map() {
        final Quintuple<String, Integer, Boolean, Character, Long> mappedQuintuple = quintuple.map(s -> s +
                " World", i -> i + 1, b -> !b, c -> (char) (c + 1), l -> l + 1);
        assertEquals("Hello World", mappedQuintuple.first());
        assertEquals(43, mappedQuintuple.second());
        assertEquals(false, mappedQuintuple.third());
        assertEquals('b', mappedQuintuple.fourth());
        assertEquals(124L, mappedQuintuple.fifth());
    }

    @Test
    public void bindFirst() {
        final Quintuple<String, Integer, Boolean, Character, Long> boundQuintuple =
                quintuple.bindFirst(s -> Quintuples.of(s + " World", 42, true, 'a', 123L));
        assertEquals("Hello World", boundQuintuple.first());
        assertEquals(42, boundQuintuple.second());
        assertEquals(true, boundQuintuple.third());
    }

    @Test
    public void bindSecond() {
        final Quintuple<String, Integer, Boolean, Character, Long> boundQuintuple =
                quintuple.bindSecond(i -> Quintuples.of("Hello", i + 1, true, 'a', 123L));
        assertEquals("Hello", boundQuintuple.first());
        assertEquals(43, boundQuintuple.second());
        assertEquals(true, boundQuintuple.third());
    }

    @Test
    public void bindThird() {
        final Quintuple<String, Integer, Boolean, Character, Long> boundQuintuple =
                quintuple.bindThird(b -> Quintuples.of("Hello", 42, !b, 'a', 123L));
        assertEquals("Hello", boundQuintuple.first());
        assertEquals(42, boundQuintuple.second());
        assertEquals(false, boundQuintuple.third());
    }

    @Test
    public void bindFourth() {
        final Quintuple<String, Integer, Boolean, Character, Long> boundQuintuple =
                quintuple.bindFourth(c -> Quintuples.of("Hello", 42, true, (char) (c + 1), 123L));
        assertEquals("Hello", boundQuintuple.first());
        assertEquals(42, boundQuintuple.second());
        assertEquals(true, boundQuintuple.third());
        assertEquals('b', boundQuintuple.fourth());
    }

    @Test
    public void bindFifth() {
        final Quintuple<String, Integer, Boolean, Character, Long> boundQuintuple =
                quintuple.bindFifth(l -> Quintuples.of("Hello", 42, true, 'a', l + 1));
        assertEquals("Hello", boundQuintuple.first());
        assertEquals(42, boundQuintuple.second());
        assertEquals(true, boundQuintuple.third());
        assertEquals(124L, boundQuintuple.fifth());
    }

    @Test
    public void compare() {
        // equal ordering
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple2 = ComparableQuintuple.of("Hello", 42, true, 'a', 123L);
        assertEquals(0, quintuple.compareTo(quintuple2));
        assertEquals(0, quintuple2.compareTo(quintuple));
        // first comparator check
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple3 = ComparableQuintuple.of("Hello World", 42, true, 'a', 123L);
        assertTrue(quintuple.compareTo(quintuple3) < 0);
        assertTrue(quintuple3.compareTo(quintuple) > 0);
        // second comparator check
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple4 = ComparableQuintuple.of("Hello", 43, true, 'a', 123L);
        assertTrue(quintuple.compareTo(quintuple4) < 0);
        assertTrue(quintuple4.compareTo(quintuple) > 0);
        // third comparator check
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple5 = ComparableQuintuple.of("Hello", 42, false, 'a', 123L);
        assertTrue(quintuple.compareTo(quintuple5) > 0);
        assertTrue(quintuple5.compareTo(quintuple) < 0);
        // fourth comparator check
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple6 = ComparableQuintuple.of("Hello", 42, true, 'b', 123L);
        assertTrue(quintuple.compareTo(quintuple6) < 0);
        assertTrue(quintuple6.compareTo(quintuple) > 0);
        // first comparator has priority over second, third and fourth
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple8 = ComparableQuintuple.of("Hello World", 43, false, 'a', 123L);
        assertTrue(quintuple.compareTo(quintuple8) < 0);
        assertTrue(quintuple8.compareTo(quintuple) > 0);
        // second comparator has priority over third and fourth
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple9 = ComparableQuintuple.of("Hello", 43, false, 'a', 123L);
        assertTrue(quintuple.compareTo(quintuple9) < 0);
        assertTrue(quintuple9.compareTo(quintuple) > 0);
        // third comparator has priority over fourth
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple10 = ComparableQuintuple.of("Hello", 42, false, 'b', 123L);
        assertTrue(quintuple.compareTo(quintuple10) > 0);
        assertTrue(quintuple10.compareTo(quintuple) < 0);
    }

    /***
     * This test shows how to use a custom comparator for the first element of the quintuple.
     * NOTE: both quintuples should have the same comparators!
     */
    @Test
    public void customFirstComparison() {
        // first comparator is custom
        final Comparator<String> stringComparator = String.CASE_INSENSITIVE_ORDER; // custom comparison
        final Comparator<Integer> integerComparator = Integer::compare; // normal comparison
        final Comparator<Boolean> booleanComparator = Boolean::compare; // normal comparison
        final Comparator<Character> characterComparator = Character::compare; // normal comparison
        final Comparator<Long> longComparator = Long::compare; // normal comparison

        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple1 = ComparableQuintuple.of("Hello", 42, true, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);

        // first comparison works
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple2 = ComparableQuintuple.of("hello", 42, true, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertEquals(0, quintuple1.compareTo(quintuple2));
        assertEquals(0, quintuple2.compareTo(quintuple1));
        // second comparison works
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple3 = ComparableQuintuple.of("Hello", 43, true, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple3) < 0);
        assertTrue(quintuple3.compareTo(quintuple1) > 0);
        // third comparison works
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple4 = ComparableQuintuple.of("Hello", 42, false, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple4) > 0);
        assertTrue(quintuple4.compareTo(quintuple1) < 0);
        // fourth comparison works
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple5 = ComparableQuintuple.of("Hello", 42, true, 'b', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple5) < 0);
        assertTrue(quintuple5.compareTo(quintuple1) > 0);
        // fifth comparison works
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple6 = ComparableQuintuple.of("Hello", 42, true, 'a', 124L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple6) < 0);
        assertTrue(quintuple6.compareTo(quintuple1) > 0);
        // first comparison has priority over second, third, fourth and fifth comparisons
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple7 = ComparableQuintuple.of("hello world", 30, false, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple7) < 0);
        assertTrue(quintuple7.compareTo(quintuple1) > 0);
        // second comparison has priority over third, fourth and fifth comparison
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple8 = ComparableQuintuple.of("Hello", 43, false, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple8) < 0);
        assertTrue(quintuple8.compareTo(quintuple1) > 0);
        // third comparison has priority over fourth and fifth comparison
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple9 = ComparableQuintuple.of("Hello", 42, false, 'b', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple9) > 0);
        assertTrue(quintuple9.compareTo(quintuple1) < 0);
        // fourth comparison has priority over fifth comparison
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple10 = ComparableQuintuple.of("Hello", 42, true, 'b', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple10) < 0);
        assertTrue(quintuple10.compareTo(quintuple1) > 0);
    }

    /***
     * This test shows how to use a custom comparator for the first element of the quintuple.
     * NOTE: both quintuples should have the same comparators!
     */
    @Test
    public void customSecondComparison() {
        // second comparator is custom
        final Comparator<String> stringComparator = String::compareTo; // normal comparison
        final Comparator<Integer> integerComparator = (i, j) -> Integer.compare(j, i); // custom comparison, reverse
        final Comparator<Boolean> booleanComparator = Boolean::compare; // normal comparison
        final Comparator<Character> characterComparator = Character::compare; // normal comparison
        final Comparator<Long> longComparator = Long::compare; // normal comparison

        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple1 = ComparableQuintuple.of("Hello", 42, true, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);

        // first comparison works
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple2 = ComparableQuintuple.of("hello", 42, true, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple2) < 0);
        assertTrue(quintuple2.compareTo(quintuple1) > 0);
        // second comparison works
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple3 = ComparableQuintuple.of("Hello", 43, true, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple3) > 0);
        assertTrue(quintuple3.compareTo(quintuple1) < 0);
        // third comparison works
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple4 = ComparableQuintuple.of("Hello", 42, false, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple4) > 0);
        assertTrue(quintuple4.compareTo(quintuple1) < 0);
        // fourth comparison works
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple5 = ComparableQuintuple.of("Hello", 42, true, 'b', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple5) < 0);
        assertTrue(quintuple5.compareTo(quintuple1) > 0);
        // fifth comparison works
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple6 = ComparableQuintuple.of("Hello", 42, true, 'a', 124L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple6) < 0);
        assertTrue(quintuple6.compareTo(quintuple1) > 0);
        // first comparison has priority over second, third, fourth and fifth comparisons
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple7 = ComparableQuintuple.of("hello world", 30, false, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple7) < 0);
        assertTrue(quintuple7.compareTo(quintuple1) > 0);
        // second comparison has priority over third, fourth and fifth comparison
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple8 = ComparableQuintuple.of("Hello", 43, false, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple8) > 0);
        assertTrue(quintuple8.compareTo(quintuple1) < 0);
        // third comparison has priority over fourth and fifth comparison
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple9 = ComparableQuintuple.of("Hello", 42, false, 'b', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple9) > 0);
        assertTrue(quintuple9.compareTo(quintuple1) < 0);
        // fourth comparison has priority over fifth comparison
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple10 = ComparableQuintuple.of("Hello", 42, true, 'b', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple10) < 0);
        assertTrue(quintuple10.compareTo(quintuple1) > 0);
    }

    /***
     * This test shows how to use a custom comparator for the first element of the quintuple.
     * NOTE: both quintuples should have the same comparators!
     */
    @Test
    public void customThirdComparison() {
        // third comparator is custom
        final Comparator<String> stringComparator = String::compareTo; // normal comparison
        final Comparator<Integer> integerComparator = Integer::compareTo; // normal comparison
        final Comparator<Boolean> booleanComparator = (a, b) -> Boolean.compare(b, a); // custom comparison, reverse
        final Comparator<Character> characterComparator = Character::compare; // normal comparison
        final Comparator<Long> longComparator = Long::compare; // normal comparison

        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple1 = ComparableQuintuple.of("Hello", 42, true, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        // first comparison works
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple2 = ComparableQuintuple.of("hello", 42, true, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple2) < 0);
        assertTrue(quintuple2.compareTo(quintuple1) > 0);
        // second comparison works
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple3 = ComparableQuintuple.of("Hello", 43, true, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple3) < 0);
        assertTrue(quintuple3.compareTo(quintuple1) > 0);
        // third comparison works
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple4 = ComparableQuintuple.of("Hello", 42, false, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple4) < 0);
        assertTrue(quintuple4.compareTo(quintuple1) > 0);
        // fourth comparison works
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple5 = ComparableQuintuple.of("Hello", 42, true, 'b', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple5) < 0);
        assertTrue(quintuple5.compareTo(quintuple1) > 0);
        // fifth comparison works
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple6 = ComparableQuintuple.of("Hello", 42, true, 'a', 124L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple6) < 0);
        assertTrue(quintuple6.compareTo(quintuple1) > 0);
        // first comparison has priority over second, third, fourth and fifth comparisons
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple7 = ComparableQuintuple.of("hello world", 30, false, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple7) < 0);
        assertTrue(quintuple7.compareTo(quintuple1) > 0);
        // second comparison has priority over third, fourth and fifth comparison
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple8 = ComparableQuintuple.of("Hello", 43, false, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple8) < 0);
        assertTrue(quintuple8.compareTo(quintuple1) > 0);
        // third comparison has priority over fourth and fifth comparison
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple9 = ComparableQuintuple.of("Hello", 42, false, 'b', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple9) < 0);
        assertTrue(quintuple9.compareTo(quintuple1) > 0);
        // fourth comparison has priority over fifth comparison
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple10 = ComparableQuintuple.of("Hello", 42, true, 'b', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple10) < 0);
        assertTrue(quintuple10.compareTo(quintuple1) > 0);
    }

    /***
     * This test shows how to use a custom comparator for the first element of the quintuple.
     * NOTE: both quintuples should have the same comparators!
     */
    @Test
    public void customFourthComparison() {
        // fourth comparator is custom
        final Comparator<String> stringComparator = String::compareTo; // normal comparison
        final Comparator<Integer> integerComparator = Integer::compareTo; // normal comparison
        final Comparator<Boolean> booleanComparator = Boolean::compare; //  normal comparison
        final Comparator<Character> characterComparator = (a, b) -> Character.compare(b, a); // custom comparison, reverse
        final Comparator<Long> longComparator = Long::compare; // normal comparison

        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple1 = ComparableQuintuple.of("Hello", 42, true, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        // first comparison works
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple2 = ComparableQuintuple.of("hello", 42, true, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple2) < 0);
        assertTrue(quintuple2.compareTo(quintuple1) > 0);
        // second comparison works
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple3 = ComparableQuintuple.of("Hello", 43, true, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple3) < 0);
        assertTrue(quintuple3.compareTo(quintuple1) > 0);
        // third comparison works
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple4 = ComparableQuintuple.of("Hello", 42, false, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple4) > 0);
        assertTrue(quintuple4.compareTo(quintuple1) < 0);
        // fourth comparison works
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple5 = ComparableQuintuple.of("Hello", 42, true, 'b', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple5) > 0);
        assertTrue(quintuple5.compareTo(quintuple1) < 0);
        // fifth comparison works
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple6 = ComparableQuintuple.of("Hello", 42, true, 'a', 124L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple6) < 0);
        assertTrue(quintuple6.compareTo(quintuple1) > 0);
        // first comparison has priority over second, third, fourth and fifth comparison
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple7 = ComparableQuintuple.of("hello world", 30, false, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple7) < 0);
        assertTrue(quintuple7.compareTo(quintuple1) > 0);
        // second comparison has priority over third, fourth and fifth comparison
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple8 = ComparableQuintuple.of("Hello", 43, false, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple8) < 0);
        assertTrue(quintuple8.compareTo(quintuple1) > 0);
        // third comparison has priority over fourth and fifth comparison
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple9 = ComparableQuintuple.of("Hello", 42, false, 'b', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple9) > 0);
        assertTrue(quintuple9.compareTo(quintuple1) < 0);
        // fourth comparison has priority over fifth comparison
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple10 = ComparableQuintuple.of("Hello", 42, true, 'b', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple10) > 0);
        assertTrue(quintuple10.compareTo(quintuple1) < 0);
    }


    /***
     * This test shows how to use a custom comparator for the first element of the quintuple.
     * NOTE: both quintuples should have the same comparators!
     */
    @Test
    public void customFifthComparison()
    {
        // fifth comparator is custom
        final Comparator<String> stringComparator = String::compareTo; // normal comparison
        final Comparator<Integer> integerComparator = Integer::compareTo; // normal comparison
        final Comparator<Boolean> booleanComparator = Boolean::compare; //  normal comparison
        final Comparator<Character> characterComparator = Character::compare; // normal comparison
        final Comparator<Long> longComparator = (a,b) -> Long.compare(b,a); // custom comparison, reverse

        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple1 = ComparableQuintuple.of("Hello", 42, true, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        // first comparison works
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple2 = ComparableQuintuple.of("hello", 42, true, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple2) < 0);
        assertTrue(quintuple2.compareTo(quintuple1) > 0);
        // second comparison works
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple3 = ComparableQuintuple.of("Hello", 43, true, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple3) < 0);
        assertTrue(quintuple3.compareTo(quintuple1) > 0);
        // third comparison works
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple4 = ComparableQuintuple.of("Hello", 42, false, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple4) > 0);
        assertTrue(quintuple4.compareTo(quintuple1) < 0);
        // fourth comparison works
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple5 = ComparableQuintuple.of("Hello", 42, true, 'b', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple5) < 0);
        assertTrue(quintuple5.compareTo(quintuple1) > 0);
        // fifth comparison works
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple6 = ComparableQuintuple.of("Hello", 42, true, 'a', 124L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple6) > 0);
        assertTrue(quintuple6.compareTo(quintuple1) < 0);
        // first comparison has priority over second, third, fourth and fifth comparison
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple7 = ComparableQuintuple.of("hello world", 30, false, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple7) < 0);
        assertTrue(quintuple7.compareTo(quintuple1) > 0);
        // second comparison has priority over third, fourth and fifth comparison
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple8 = ComparableQuintuple.of("Hello", 43, false, 'a', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple8) < 0);
        assertTrue(quintuple8.compareTo(quintuple1) > 0);
        // third comparison has priority over fourth and fifth comparison
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple9 = ComparableQuintuple.of("Hello", 42, false, 'b', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple9) > 0);
        assertTrue(quintuple9.compareTo(quintuple1) < 0);
        // fourth comparison has priority over fifth comparison
        final ComparableQuintuple<String, Integer, Boolean, Character, Long> quintuple10 = ComparableQuintuple.of("Hello", 42, true, 'b', 123L, stringComparator, integerComparator, booleanComparator, characterComparator, longComparator);
        assertTrue(quintuple1.compareTo(quintuple10) < 0);
        assertTrue(quintuple10.compareTo(quintuple1) > 0);
    }
}
