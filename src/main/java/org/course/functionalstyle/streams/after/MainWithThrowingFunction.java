package org.course.functionalstyle.streams.after;

import org.reusable.throwing.ThrowingFunction;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;
import static org.reusable.Lists.listOf;

/**
 * This is a simple example of how to use the ThrowingFunction interface.
 * <p>
 * This interface is a SAM interface and turns the checked exception of the original method into a RuntimeException.
 * <p>
 * Generally, this is a little against the philosophy of Java, which doesn't shy away from checked exceptions.
 * Of course the idea of checked exceptions has been questioned many times the past years.
 * Checked exceptions are only a Java feature, other languages don't have them.
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
        final List<String> animals = listOf("Dog", "Cat", "Bird", "Fish", "Snake", "Lizard", "Turtle", "Rabbit", "Horse","Cow");

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
        final int max = 1000;
        final SecureRandom random = new SecureRandom();
        final int rnd = random.nextInt(max - min + 1) + min;
        if (rnd < 3) {
            throw new IOException("Random number goes boom it was: " + rnd);
        }

        return t;
    }
}


