package org.course.composability.dependencyinjection.partialapplication.before;

/**
 * Below we have class Adder that has method addFirst that returns FixedAdder.
 * Basically that fixes the first operand of the add method.
 * Afterwards we can call add method on FixedAdder with the second operand.
 * That will give us the result of the addition, which we print out.
 *
 * This is a very simple example of partial application. But how do we write it without classes?
 * */
public class Main {
    public static void main(String[] args) {
        final Adder adder = new Adder();
        final FixedAdder fiveFixed = adder.addFirst(5);
        final int result = fiveFixed.add(10);

        System.out.println(result);
    }
}
