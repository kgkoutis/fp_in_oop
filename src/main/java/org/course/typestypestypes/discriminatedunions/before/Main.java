package org.course.typestypestypes.discriminatedunions.before;

import java.util.Optional;

/**
 * What are Discriminated Unions (DUs)? Before we answer that, we can already give an example present in the Java language: the Optional class.
 * Optional is a container object which may or may not contain a non-null value. Like Optional, all DUs have this sort of indeterminate status
 * where you cannot really say what they really are. They are kind of like Schrödinger's cat. Until you open the box,
 * it's both alive and dead. So in the case of Optional, until you "open the box", an Optional is considered both empty and not empty.
 * How do you "open" it then?
 * <p>
 * For Optional (as was designed by Java) either:
 * a)  you call the get() method which "forces the box" and might throw an exception if the box is empty,
 * b)  or you call the isPresent() method, to "gently ask" about the status of the Optional container, and after you know, you can call get() if you want,
 * c)  or you call the ifPresent(cb) method where cb is a Consumer<? super T> callback you provide that will get executed internally by Optional<T> if the value is present.
 * <p>
 * Generally speaking, DUs logically divide the type space into disjoint subsets. In the case of Optional, the type space is logically divided into two subsets: Optional<T>
 * and Optional.empty(). If that is not clear enough let's see some DUs in an FP language like Haskell to get a feel for them.
 * <p>
 * i)      data Maybe a = Just a | Nothing
 * Optional<T> in Haskell corresponds to the Maybe type.
 * ii)     data Bool = False | True
 * iii)    data Weekdays = Monday | Tuesday | Wednesday | Thursday | Friday | Saturday | Sunday
 * iv)     data NonZero = Positive Int | Negative Int              [this type I wrote myself]
 * The semantics are that NonZero is an integer wrapper, whose integer can only be positive or negative. It cannot be 0.
 * <p>
 * Maybe now you can also understand why DUs are also called "sum types".
 * <p>
 * Haskell DU's are something between a C/C++ union struct and an enumeration.
 * They are also different from Typescript unions, because TS unions are not necessarily disjoint.
 * In TS, a union type can be a combination of two or more types like so:
 *      type MyWhateverUnion = char | number | false | "Hi mom!"; // way too broad as a Haskell/FP definition, but possible for TS
 * <p>
 * In FP languages, DUs when used as an argument to a function, they are most likely combined with pattern matching.
 * This is because the compiler cannot know which of the 2 (or 3,4,5...) subsets the DU actually represents.
 * <p>
 * Basically this leads to a design where the compiler forces you to handle all possible cases. Consider the following in Haskell:
 * <p>
 *      map :: Maybe a -> (a -> b) -> Maybe b
 *      map maybe f = case maybe of
 *                          Just a -> Just (f a)   -- handle first case
 *                          _      -> Nothing      -- handle second case, which is the last case.
 * <p>
 * Question? Can we have DUs in Java? Answer: There are a number of ways to emulate DUs in Java.
 * <p>
 * Let's see how we could implement the Maybe monad in Java.
 */
public class Main {
    public static void main(final String[] args) {
        final Optional<String> optional = Optional.of("Hello, world!"); // Optional has a value
        optional.map(String::toUpperCase).ifPresent(System.out::println);
        // Output: HELLO, WORLD!

        final Optional<String> optional2 = Optional.empty(); // Optional is empty
        optional2.map(String::toUpperCase).ifPresent(System.out::println);
        // nothing is printed
    }
}
