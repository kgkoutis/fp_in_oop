package org.course.functionalstyle.immutability.yourturn;
public class Main {
    public static void main(final String[] args) {
        final Point p1 = new Point(1, 2);
        System.out.println(p1);
        p1.move(3, 4); // How do we fix it so that it doesn't mutate the object?
        System.out.println(p1);

        // don't change this method
        // if you get it correct, it should print Point{x=1+a, y=2+a} where a is specific a number
        // (not going to reveal it here) so that x and y will be different but increased by the same amount
        testThreadSafety();
    }

    public static void testThreadSafety() {
        System.out.println("Testing thread safety");
        final Point p1 = new Point(1, 2);

        final Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                p1.move(1, 1);
            }
        });

        final Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                p1.move(1, 1);
            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(p1);
    }
}
