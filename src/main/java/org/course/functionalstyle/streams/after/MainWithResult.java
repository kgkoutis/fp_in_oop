package org.course.functionalstyle.streams.after;

import org.reusable.Result;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.stream.Collectors.groupingBy;

/**
 * This is a simple example of how to use the Result class. The Result class is a simple wrapper marking either the presence of a value
 * (success scenario) or an exception (failure scenario). The Result class offers a number of methods to handle the success and failure
 * scenarios separately. It is definitely the way to go here. To use it we just need to wrap the code that might throw an exception as we do
 * here in the getResult method.
 *
 * It is obvious that using such code we don't interfere with the philosophy of the language as we retain the information about the checked exception
 * and defer its handling for later to some upstream caller as we return.
 *
 * NOTE: The Result type as a concept came from functional programming and it is a special case of the Either type which we will see later.
 * NOTE2: Internally in our implementation the Result type uses the sneakyThrows method that we talked before.
 *
 */
public class MainWithResult {
    public static void main(String[] args) {

        Result<List<String>, Throwable> animals2 = getResult(List.of("Dog", "Cat", "Bird", "Fish", "Snake", "Lizard", "Turtle", "Rabbit", "Horse", "Cow"));

        // first way to handle result
        Optional<Map<Integer, List<String>>> ss = animals2
                .mapSuccess(
                        success -> success
                                .stream()
                                .map(String::toUpperCase)
                                .collect(groupingBy(String::length)));

        if (ss.isPresent()) {
            ss.get().forEach((length, strings) -> System.out.println(length + " -> " + strings));
        } else {
            System.out.println("Error reading file: " + animals2.throwable().getMessage());
        }

        // or second way to handle the result
        animals2.handle(
                success -> success
                        .stream()
                        .map(String::toUpperCase)
                        .collect(groupingBy(String::length))
                        .forEach((length, strings) -> System.out.println(length + " -> " + strings))
                ,
                e -> System.out.println("Error reading file: " + e.getMessage())
        );
    }

    private static Result<List<String>, Throwable> getResult(List<String> animals) {
        try {
            final List<String> result = MainWithResult.sometimesThrowsIOException(animals);

            return Result.success(result);
        } catch (IOException e) {
            return Result.failure(e);
        }
    }

    private static <T> T sometimesThrowsIOException(T t) throws IOException {
        int min = 0;
        int max = 10;
        int rnd = ThreadLocalRandom.current().nextInt(min, max + 1);
        if (rnd < 1) {
            throw new IOException("Random number goes boom");
        }

        return t;
    }
}


