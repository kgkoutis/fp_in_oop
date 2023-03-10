package org.course.typestypestypes.discriminatedunions.after;

import org.reusable.Unit;
import org.reusable.maybe.Maybe;

/**
 * A discriminated union can be emulated in Java using 2 things: a (preferably) closed class hierarchy and a visitor interface.
 * The visitor interface's mission is to provide a method for each class in the hierarchy.
 * Every subclass of visitor will know how to pattern match on the class hierarchy because it will have already a method for each class.
 */
public class Main {
    public static void main(final String[] args) {
        final Maybe<String> maybe = Maybe.of("Hello, world!"); // Maybe has a value
        final Unit _1 = maybe.map(String::toUpperCase).ifJust(System.out::println);
        // Output: HELLO, WORLD!

        final Maybe<String> maybe2 = Maybe.of(null); // Maybe has no value
        final Unit _2 = maybe2.map(String::toUpperCase).ifJust(System.out::println);
        // nothing is printed
    }
}
