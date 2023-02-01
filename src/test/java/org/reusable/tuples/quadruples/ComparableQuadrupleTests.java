package org.reusable.tuples.quadruples;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComparableQuadrupleTests {
    private ComparableQuadruple<String, Integer, Boolean, Character> quadruple;

    @BeforeEach
    void setUp() {
        quadruple = ComparableQuadruple.of("Hello", 42, true, 'a');
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
        final Quadruple<String, Integer, Boolean, Character> mappedQuadruple = quadruple.mapFourth(c -> (char) (c + 1));
        assertEquals("Hello", mappedQuadruple.first());
        assertEquals(42, mappedQuadruple.second());
        assertEquals(true, mappedQuadruple.third());
        assertEquals('b', mappedQuadruple.fourth());
    }

    @Test
    public void map() {
        final Quadruple<String, Integer, Boolean, Character> mappedQuadruple = quadruple.map(s -> s + " World", i -> i +
                1, b -> !b, c -> (char) (c + 1));
        assertEquals("Hello World", mappedQuadruple.first());
        assertEquals(43, mappedQuadruple.second());
        assertEquals(false, mappedQuadruple.third());
        assertEquals('b', mappedQuadruple.fourth());
    }

    @Test
    public void bindFirst() {
        final Quadruple<String, Integer, Boolean, Character> boundQuadruple =
                quadruple.bindFirst(s -> Quadruples.of(s + " World", 42, true, 'a'));
        assertEquals("Hello World", boundQuadruple.first());
        assertEquals(42, boundQuadruple.second());
        assertEquals(true, boundQuadruple.third());
    }

    @Test
    public void bindSecond() {
        final Quadruple<String, Integer, Boolean, Character> boundQuadruple =
                quadruple.bindSecond(i -> Quadruples.of("Hello", i + 1, true, 'a'));
        assertEquals("Hello", boundQuadruple.first());
        assertEquals(43, boundQuadruple.second());
        assertEquals(true, boundQuadruple.third());
    }

    @Test
    public void bindThird() {
        final Quadruple<String, Integer, Boolean, Character> boundQuadruple =
                quadruple.bindThird(b -> Quadruples.of("Hello", 42, !b, 'a'));
        assertEquals("Hello", boundQuadruple.first());
        assertEquals(42, boundQuadruple.second());
        assertEquals(false, boundQuadruple.third());
    }

    @Test
    public void bindFourth() {
        final Quadruple<String, Integer, Boolean, Character> boundQuadruple =
                quadruple.bindFourth(c -> Quadruples.of("Hello", 42, true, (char) (c + 1)));
        assertEquals("Hello", boundQuadruple.first());
        assertEquals(42, boundQuadruple.second());
        assertEquals(true, boundQuadruple.third());
        assertEquals('b', boundQuadruple.fourth());
    }

    @Test
    public void compare() {
        // equal ordering
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple2 = ComparableQuadruple.of("Hello", 42, true, 'a');
        assertEquals(0, quadruple.compareTo(quadruple2));
        assertEquals(0, quadruple2.compareTo(quadruple));
        // first comparator check
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple3 = ComparableQuadruple.of("Hello World", 42, true, 'a');
        assertTrue(quadruple.compareTo(quadruple3) < 0);
        assertTrue(quadruple3.compareTo(quadruple) > 0);
        // second comparator check
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple4 = ComparableQuadruple.of("Hello", 43, true, 'a');
        assertTrue(quadruple.compareTo(quadruple4) < 0);
        assertTrue(quadruple4.compareTo(quadruple) > 0);
        // third comparator check
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple5 = ComparableQuadruple.of("Hello", 42, false, 'a');
        assertTrue(quadruple.compareTo(quadruple5) > 0);
        assertTrue(quadruple5.compareTo(quadruple) < 0);
        // fourth comparator check
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple6 = ComparableQuadruple.of("Hello", 42, true, 'b');
        assertTrue(quadruple.compareTo(quadruple6) < 0);
        assertTrue(quadruple6.compareTo(quadruple) > 0);
        // first comparator has priority over second, third and fourth
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple7 = ComparableQuadruple.of("Hello World", 43, false, 'a');
        assertTrue(quadruple.compareTo(quadruple7) < 0);
        assertTrue(quadruple7.compareTo(quadruple) > 0);
        // second comparator has priority over third and fourth
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple8 = ComparableQuadruple.of("Hello", 43, false, 'a');
        assertTrue(quadruple.compareTo(quadruple8) < 0);
        assertTrue(quadruple8.compareTo(quadruple) > 0);
        // third comparator has priority over fourth
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple9 = ComparableQuadruple.of("Hello", 42, false, 'b');
        assertTrue(quadruple.compareTo(quadruple9) > 0);
        assertTrue(quadruple9.compareTo(quadruple) < 0);
    }

    /***
     * This test shows how to use a custom comparator for the first element of the quadruple.
     * NOTE: both quadruples should have the same comparators!
     */
    @Test
    public void customFirstComparison() {
        // first comparator is custom
        final Comparator<String> stringComparator = String.CASE_INSENSITIVE_ORDER; // custom comparison
        final Comparator<Integer> integerComparator = Integer::compare; // normal comparison
        final Comparator<Boolean> booleanComparator = Boolean::compare; // normal comparison
        final Comparator<Character> characterComparator = Character::compare; // normal comparison

        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple1 = ComparableQuadruple.of("Hello", 42, true, 'a', stringComparator, integerComparator, booleanComparator,characterComparator);

        // first comparison works
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple2 = ComparableQuadruple.of("hello", 42, true, 'a', stringComparator, integerComparator, booleanComparator,characterComparator);
        assertEquals(0, quadruple1.compareTo(quadruple2));
        assertEquals(0, quadruple2.compareTo(quadruple1));
        // second comparison works
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple3 = ComparableQuadruple.of("Hello", 43, true, 'a', stringComparator, integerComparator, booleanComparator,characterComparator);
        assertTrue(quadruple1.compareTo(quadruple3) < 0);
        assertTrue(quadruple3.compareTo(quadruple1) > 0);
        // third comparison works
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple4 = ComparableQuadruple.of("Hello", 42, false, 'a', stringComparator, integerComparator, booleanComparator,characterComparator);
        assertTrue(quadruple1.compareTo(quadruple4) > 0);
        assertTrue(quadruple4.compareTo(quadruple1) < 0);
        // fourth comparison works
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple5 = ComparableQuadruple.of("Hello", 42, true, 'b', stringComparator, integerComparator, booleanComparator, characterComparator);
        assertTrue(quadruple1.compareTo(quadruple5) < 0);
        assertTrue(quadruple5.compareTo(quadruple1) > 0);
        // first comparison has priority over second, third and fourth comparisons
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple6 = ComparableQuadruple.of("hello world", 30, false, 'a', stringComparator, integerComparator, booleanComparator,characterComparator);
        assertTrue(quadruple1.compareTo(quadruple6) < 0);
        assertTrue(quadruple6.compareTo(quadruple1) > 0);
        // second comparison has priority over third and fourth comparison
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple7 = ComparableQuadruple.of("Hello", 43, false, 'a', stringComparator, integerComparator, booleanComparator,characterComparator);
        assertTrue(quadruple1.compareTo(quadruple7) < 0);
        assertTrue(quadruple7.compareTo(quadruple1) > 0);
        // third comparison has priority over fourth comparison
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple8 = ComparableQuadruple.of("Hello", 42, false, 'b', stringComparator, integerComparator, booleanComparator,characterComparator);
        assertTrue(quadruple1.compareTo(quadruple8) > 0);
        assertTrue(quadruple8.compareTo(quadruple1) < 0);
    }

    /***
     * This test shows how to use a custom comparator for the first element of the quadruple.
     * NOTE: both quadruples should have the same comparators!
     */
    @Test
    public void customSecondComparison() {
        // second comparator is custom
        final Comparator<String> stringComparator = String::compareTo; // normal comparison
        final Comparator<Integer> integerComparator = (i, j) -> Integer.compare(j, i); // custom comparison, reverse
        final Comparator<Boolean> booleanComparator = Boolean::compare; // normal comparison
        final Comparator<Character> characterComparator = Character::compare; // normal comparison

        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple1 = ComparableQuadruple.of("Hello", 42, true, 'a', stringComparator, integerComparator, booleanComparator, characterComparator);

        // first comparison works
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple2 = ComparableQuadruple.of("hello", 42, true, 'a', stringComparator, integerComparator, booleanComparator, characterComparator);
        assertTrue(quadruple1.compareTo(quadruple2) < 0);
        assertTrue(quadruple2.compareTo(quadruple1) > 0);
        // second comparison works
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple3 = ComparableQuadruple.of("Hello", 43, true, 'a', stringComparator, integerComparator, booleanComparator, characterComparator);
        assertTrue(quadruple1.compareTo(quadruple3) > 0);
        assertTrue(quadruple3.compareTo(quadruple1) < 0);
        // third comparison works
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple4 = ComparableQuadruple.of("Hello", 42, false, 'a', stringComparator, integerComparator, booleanComparator, characterComparator);
        assertTrue(quadruple1.compareTo(quadruple4) > 0);
        assertTrue(quadruple4.compareTo(quadruple1) < 0);
        // fourth comparison works
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple5 = ComparableQuadruple.of("Hello", 42, true, 'b', stringComparator, integerComparator, booleanComparator, characterComparator);
        assertTrue(quadruple1.compareTo(quadruple5) < 0);
        assertTrue(quadruple5.compareTo(quadruple1) > 0);
        // first comparison has priority over second, third and fourth comparison
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple6 = ComparableQuadruple.of("hello world", 30, false, 'a', stringComparator, integerComparator, booleanComparator, characterComparator);
        assertTrue(quadruple1.compareTo(quadruple6) < 0);
        assertTrue(quadruple6.compareTo(quadruple1) > 0);
        // second comparison has priority over third and fourth comparison
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple7 = ComparableQuadruple.of("Hello", 43, false, 'a', stringComparator, integerComparator, booleanComparator, characterComparator);
        assertTrue(quadruple1.compareTo(quadruple7) > 0);
        assertTrue(quadruple7.compareTo(quadruple1) < 0);
        // third comparison has priority over fourth comparison
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple8 = ComparableQuadruple.of("Hello", 42, false, 'b', stringComparator, integerComparator, booleanComparator, characterComparator);
        assertTrue(quadruple1.compareTo(quadruple8) > 0);
        assertTrue(quadruple8.compareTo(quadruple1) < 0);
    }

    /***
     * This test shows how to use a custom comparator for the first element of the quadruple.
     * NOTE: both quadruples should have the same comparators!
     */
    @Test
    public void customThirdComparison() {
        // third comparator is custom
        final Comparator<String> stringComparator = String::compareTo; // normal comparison
        final Comparator<Integer> integerComparator = Integer::compareTo; // normal comparison
        final Comparator<Boolean> booleanComparator = (a, b) -> Boolean.compare(b, a); // custom comparison, reverse
        final Comparator<Character> characterComparator = Character::compare; // normal comparison

        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple1 = ComparableQuadruple.of("Hello", 42, true, 'a', stringComparator, integerComparator, booleanComparator, characterComparator);
        // first comparison works
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple2 = ComparableQuadruple.of("hello", 42, true, 'a', stringComparator, integerComparator, booleanComparator, characterComparator);
        assertTrue(quadruple1.compareTo(quadruple2) < 0);
        assertTrue(quadruple2.compareTo(quadruple1) > 0);
        // second comparison works
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple3 = ComparableQuadruple.of("Hello", 43, true, 'a', stringComparator, integerComparator, booleanComparator, characterComparator);
        assertTrue(quadruple1.compareTo(quadruple3) < 0);
        assertTrue(quadruple3.compareTo(quadruple1) > 0);
        // third comparison works
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple4 = ComparableQuadruple.of("Hello", 42, false, 'a', stringComparator, integerComparator, booleanComparator, characterComparator);
        assertTrue(quadruple1.compareTo(quadruple4) < 0);
        assertTrue(quadruple4.compareTo(quadruple1) > 0);
        // fourth comparison works
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple5 = ComparableQuadruple.of("Hello", 42, true, 'b', stringComparator, integerComparator, booleanComparator, characterComparator);
        assertTrue(quadruple1.compareTo(quadruple5) < 0);
        assertTrue(quadruple5.compareTo(quadruple1) > 0);
        // first comparison has priority over second and third comparison
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple6 = ComparableQuadruple.of("hello world", 30, false, 'a', stringComparator, integerComparator, booleanComparator, characterComparator);
        assertTrue(quadruple1.compareTo(quadruple6) < 0);
        assertTrue(quadruple6.compareTo(quadruple1) > 0);
        // second comparison has priority over third comparison
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple7 = ComparableQuadruple.of("Hello", 43, false, 'a', stringComparator, integerComparator, booleanComparator, characterComparator);
        assertTrue(quadruple1.compareTo(quadruple7) < 0);
        assertTrue(quadruple7.compareTo(quadruple1) > 0);
        // third comparison has priority over fourth comparison
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple8 = ComparableQuadruple.of("Hello", 42, false, 'b', stringComparator, integerComparator, booleanComparator, characterComparator);
        assertTrue(quadruple1.compareTo(quadruple8) < 0);
        assertTrue(quadruple8.compareTo(quadruple1) > 0);
    }

    /***
     * This test shows how to use a custom comparator for the first element of the quadruple.
     * NOTE: both quadruples should have the same comparators!
     */
    @Test
    public void customFourthComparison()
    {
        // fourth comparator is custom
        final Comparator<String> stringComparator = String::compareTo; // normal comparison
        final Comparator<Integer> integerComparator = Integer::compareTo; // normal comparison
        final Comparator<Boolean> booleanComparator = Boolean::compare; //  normal comparison
        final Comparator<Character> characterComparator = (a, b) -> Character.compare(b,a); // custom comparison, reverse

        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple1 = ComparableQuadruple.of("Hello", 42, true, 'a', stringComparator, integerComparator, booleanComparator, characterComparator);
        // first comparison works
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple2 = ComparableQuadruple.of("hello", 42, true, 'a', stringComparator, integerComparator, booleanComparator, characterComparator);
        assertTrue(quadruple1.compareTo(quadruple2) < 0);
        assertTrue(quadruple2.compareTo(quadruple1) > 0);
        // second comparison works
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple3 = ComparableQuadruple.of("Hello", 43, true, 'a', stringComparator, integerComparator, booleanComparator, characterComparator);
        assertTrue(quadruple1.compareTo(quadruple3) < 0);
        assertTrue(quadruple3.compareTo(quadruple1) > 0);
        // third comparison works
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple4 = ComparableQuadruple.of("Hello", 42, false, 'a', stringComparator, integerComparator, booleanComparator, characterComparator);
        assertTrue(quadruple1.compareTo(quadruple4) > 0);
        assertTrue(quadruple4.compareTo(quadruple1) < 0);
        // fourth comparison works
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple5 = ComparableQuadruple.of("Hello", 42, true, 'b', stringComparator, integerComparator, booleanComparator, characterComparator);
        assertTrue(quadruple1.compareTo(quadruple5) > 0);
        assertTrue(quadruple5.compareTo(quadruple1) < 0);
        // first comparison has priority over second and third comparison
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple6 = ComparableQuadruple.of("hello world", 30, false, 'a', stringComparator, integerComparator, booleanComparator, characterComparator);
        assertTrue(quadruple1.compareTo(quadruple6) < 0);
        assertTrue(quadruple6.compareTo(quadruple1) > 0);
        // second comparison has priority over third comparison
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple7 = ComparableQuadruple.of("Hello", 43, false, 'a', stringComparator, integerComparator, booleanComparator, characterComparator);
        assertTrue(quadruple1.compareTo(quadruple7) < 0);
        assertTrue(quadruple7.compareTo(quadruple1) > 0);
        // third comparison has priority over fourth comparison
        final ComparableQuadruple<String, Integer, Boolean, Character> quadruple8 = ComparableQuadruple.of("Hello", 42, false, 'b', stringComparator, integerComparator, booleanComparator, characterComparator);
        assertTrue(quadruple1.compareTo(quadruple8) > 0);
        assertTrue(quadruple8.compareTo(quadruple1) < 0);
    }
}
