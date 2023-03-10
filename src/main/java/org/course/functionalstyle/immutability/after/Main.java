package org.course.functionalstyle.immutability.after;

/**
 * In FP there are no such things as setters.
 * To "set" a value, you create a new object with the new value (possibly based on the old one).
 * <p>
 * In other words: in FP, setters are copy-getters.
 * <p>
 * Point was made immutable. Now it is thread-safe, because no threads can call a method that changes its internal state.
 * The only thing a thread can do is create a new object, not interfere with an existing one.
 * So two threads always end up working with different objects (even if they share the initial one!).
 * <p>
 * Question: what is a disadvantage of this approach? (think)
 */
public class Main {
    public static void main(final String[] args) {
        final Point p1 = new Point(1, 2);
        final Point p2 = p1.move(3, 4);

        // extra
        final Point p3 = p1.withX(5); // substitution of X
        System.out.println(p3);
        final Point p4 = p1.withY(6); // substitution of Y
        System.out.println(p4);

        testThreadSafety();
        testThreadSafetyBetter();
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

    public static void testThreadSafetyBetter() {
        System.out.println("Testing thread safety better");
        final Point p = new Point(1, 2);

        final Point[] pt1 = {p};
        final Point[] pt2 = {p};

        final Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                final Point temp = pt1[0].move(1, 1);
                pt1[0] = temp;
            }
        });

        final Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                final Point temp = pt2[0].move(1, 1);
                pt2[0] = temp;
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

        System.out.println(pt1[0]);
        System.out.println(pt2[0]);
    }
}

