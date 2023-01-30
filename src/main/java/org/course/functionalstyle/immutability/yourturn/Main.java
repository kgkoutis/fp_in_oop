package org.course.functionalstyle.immutability.yourturn;
public class Main {
    public static void main(final String[] args) {
        final Point p1 = new Point(1, 2);
        System.out.println(p1);
        p1.move(3, 4); // How do we fix it so that it doesn't mutate the object?
        System.out.println(p1);
    }
}
