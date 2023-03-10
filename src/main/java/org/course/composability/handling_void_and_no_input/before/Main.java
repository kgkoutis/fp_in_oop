package org.course.composability.handling_void_and_no_input.before;

/**
 * Here we would like to implement the function composition technique we learned (nevermind the reason) but
 * both methods inside class A receive no input and return no output.
 *
 * How can we tackle this problem?
 * */
public class Main {
    public static void main(final String[] args) {
        final A a = new A();
        a.compose(); // composes the two methods inside class A
    }
}