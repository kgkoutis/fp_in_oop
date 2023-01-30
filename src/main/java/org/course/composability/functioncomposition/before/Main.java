package org.course.composability.functioncomposition.before;

/**
 * The code below calculates splitStringInWords's result and passes it to countArray.
 * <p>
 * Function composition is the process of combining two or more functions to produce a new function.
 * Basically in the case of composing 2 functions, the result of the first function is passed to the second function.
 * <p>
 * You might remember from math at school (or university), the composition of two functions f and g is defined as:
 * <p>
 * h(x) = f(g(x))
 * <p>
 * where h is a new function, the composition of f and g.
 * <p>
 * Note that g above is the 1st function (since it receives the input x first) and has to feed its result to f (the 2nd function).
 * Note also that in general the types of f and g should be compatible.
 * <p>
 * How would we express this in code?
 */
public class Main {
    public static void main(final String[] args) {
        final String initialInput = "Hello World!";

        final String[] result1 = splitStringInWords(initialInput);
        final int result2 = countArray(result1);

        System.out.println(result2);
    }

    private static String[] splitStringInWords(final String str) {
        final String[] parts = str.split("\\s+"); // split by one or more spaces

        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].replaceAll("[.!?,]", ""); // remove dots, exclamation marks, commas and question marks
        }

        return parts;
    }

    private static int countArray(final String[] parts) {
        return parts.length;
    }
}