package org.course.composability.functioncomposition.after;

import java.util.function.Function;

/**
 * Here countWords is the composition of splitStringInWords and countArray as expressed by the following equation:
 * <p>
 * countWords(x) = countArray(splitStringInWords(x))
 * <p>
 * The fact that we have it in a lambda expression is just a matter of preference. Lambda expressions play a big role in functional programming.
 * Hopefully you can see that the code behaves exactly the same as before.
 * <p>
 * The benefit of using function composition is that split our code into smaller pieces and we can compose them in bigger functions
 * without having to worry about the implementation details of the smaller functions. Kind of like Lego blocks.
 * */
public class Main {
    public static void main(String[] args) {
        final String initialInput = "Hello World!";

        final Function<String, Integer> countWords = x -> countArray(splitStringInWords(x));
        final int result = countWords.apply(initialInput);

        System.out.println(result);

        // Another way to write the same thing is below where the order we write the functions is reversed. The semantics however do stay the same.

        // Function<String, String[]> first = Main::splitStringInWords;
        // Function<String[], Integer> second = Main::countArray;
        // final Function<String, Integer> countWords2 = first.andThen(second);
    }

    private static String[] splitStringInWords(String str) {
        final String[] parts = str.split("\\s+"); // split by one or more spaces

        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].replaceAll("[.!?,]", ""); // remove dots, exclamation marks, commas and question marks
        }

        return parts;
    }

    private static int countArray(String[] parts) {
        return parts.length;
    }
}