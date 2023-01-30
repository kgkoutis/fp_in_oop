package org.course.functionalstyle.immutability.after;

/**
 * In FP there are no such things as setters.
 * To "set" a value, you create a new object with the new value (possibly based on the old one).
 *
 * In other words: in FP, setters are copy-getters.
 *
 * Point was made immutable. Now it is thread-safe, because no threads can call a method that changes its internal state.
 * The only thing a thread can do is create a new object, not interfere with an existing one.
 * So two threads always end up working with different objects (even if they share the initial one!).
 *
 * Question: what is a disadvantage of this approach? (think)
 *
 * */
public class Main {
    public static void main(String[] args) {
        final Point p1 = new Point(1, 2);
        final Point p2 = p1.move(3, 4);
        System.out.println(p1);
        System.out.println(p2);
    }
}

