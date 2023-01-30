package org.course.functionalstyle.immutability.before;

/**
 * Point here is not thread-safe (why?). How can we improve it?
 */
public class Main {
    public static void main(final String[] args) {
        final Point p1 = new Point(1, 2);
        System.out.println(p1);
        p1.move(3, 4);
        System.out.println(p1);
    }
}

