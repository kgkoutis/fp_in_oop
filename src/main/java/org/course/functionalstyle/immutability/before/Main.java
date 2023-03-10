package org.course.functionalstyle.immutability.before;

/**
 * Point here is not thread-safe (why?). Check the test below. How can we improve it?
 */
public class Main {
    public static void main(final String[] args) {
        final Point p1 = new Point(1, 2);
        System.out.println(p1);
        p1.move(3, 4);
        System.out.println(p1);

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

