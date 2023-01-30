package org.course.composability.dependencyinjection.partialapplication.after;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Partial application is a technique where you fix some of the arguments to a function, and get a new function back.
 * This is what we see with the adder function below. It takes an Integer and returns a Function<Integer, Integer> back.
 * Here we create a function (fiveFixed) that adds 5 to any number as a first operand.
 *
 * We would say that 5 is "baked in" or more formally partially applied to the function fiveFixed.
 * Afterwards we just apply the second operand to retrieve the result.
 *
 * NOTE: A function that takes a function as an input argument OR gives back a function as an output OR does both is called a higher order function (HOF).
 * Meaning, it handles functions like any other "normal" values (e.g. booleans, ints etc.) as first class citizens.
 * Most languages nowadays support HOFs. Before Java 8, Java didn't support HOFs.
 *
 * NOTE2: The type Function<Integer, Function<Integer, Integer>> is not the same as BiFunction<Integer, Integer, Integer>.
 * BiFunction is a functional interface that takes two arguments and returns a (simple) result.
 * Function<Integer, Function<Integer, Integer>> is a functional interface that takes one argument and returns a function which takes one argument and returns a result.
 *
 * Basically it promotes a technique where you take in "one argument at a time".
 * This is called currying. It is a technique that is used widely in functional programming.
 *
 */
public class Main {
    public static void main(String[] args) {
        final Function<Integer, Function<Integer, Integer>> adder = firstOperand -> secondOperand -> firstOperand + secondOperand;
        final Function<Integer, Integer> fiveFixed = adder.apply(5); // partial application, first operand is "baked in"
        final int result = fiveFixed.apply(10);
        System.out.println(result);

        BiFunction<Integer, Integer, Integer> otherAdder = (a, b) -> a + b;
    }
}