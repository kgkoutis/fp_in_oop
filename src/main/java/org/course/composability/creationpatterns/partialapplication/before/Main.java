package org.course.composability.creationpatterns.partialapplication.before;

/**
 * Below we have class Adder that has method addFirst that returns FixedAdder.
 * Basically that fixes the first operand of the add method.
 * Afterwards we can call add method on FixedAdder with the second operand.
 * That will give us the result of the addition, which we print out.
 * <p>
 * This is a very simple example of partial application. But how do we write it without classes?
 */
public class Main {
    public static void main(final String[] args) {          //             (1st operand)       (operation)     (2nd operand)
        final Adder adder = new Adder();                    //  result = [fill-in-the-blank]       +        [fill-in-the-other-blank]
        final FixedAdder fiveFixed = adder.addFirst(5);  //   result = 5 + [fill-in-the-other-blank]
        final int result = fiveFixed.add(10);               //  result = 5 + 10

        System.out.println(result);
    }
}
