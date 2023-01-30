package org.course.functionalstyle.streams.after;

import org.reusable.throwing.ThrowingFunction;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.stream.Collectors.groupingBy;

/**
 * This is a simple example of how to use the ThrowingFunction interface.
 * <p>
 * This interface is a SAM and turns the checked exception of the original method (or lambda) into a RuntimeException.
 * One might say that it introduces the "proxy pattern" applied for a functional interface.
 * <p>
 * What kind of problems does this cause in terms of:
 * a) philosophy of the language?
 * b) exception handling?
 * c) multiple exceptions?
 * <p>
 * Generally, this is NOT the best way to handle exceptions in a stream.
 * <p>
 * It is also a way which handles checked exceptions, which is only a Java feature, other languages don't encounter.
 * <p>
 * There is also another method called sneakyThrows (shown next), which is basically a compiler hack and can be used to make checked exceptions
 * "invisible" to the compiler. The result would be the same as this example, you would write
 * <p>
 * animals.stream()
 * .map(sneakyThrows(MainWithThrowingFunction::sometimesThrowsIOException))
 * <p>
 * Needless to say, this is also not the best way to handle exceptions in a stream. SneakyThrows might however be useful in internal code
 * as we will show next.
 */
public class MainWithThrowingFunction {
    public static void main(final String[] args) {
        final List<String> animals = List.of("Dog", "Cat", "Bird", "Fish", "Snake", "Lizard", "Turtle", "Rabbit", "Horse", "Cow");

        try {
            animals.stream()
                    .map(ThrowingFunction.uncheck(MainWithThrowingFunction::sometimesThrowsIOException))
                    .map(String::toUpperCase)
                    .collect(groupingBy(String::length))
                    .forEach((length, strings) -> System.out.println(length + " -> " + strings));
        } catch (final RuntimeException e) {
            // handle exception here, you have to know what the exception is
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static <T> T sometimesThrowsIOException(final T t) throws IOException {
        final int min = 0;
        final int max = 10;
        final int rnd = ThreadLocalRandom.current().nextInt(min, max + 1);
        if (rnd < 1) {
            throw new IOException("Random number goes boom");
        }

        return t;
    }
}


