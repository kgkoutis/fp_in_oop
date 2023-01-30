package org.course.functionalstyle.immutability.after;

/**
 * This could be a record in later Java versions.
 */
final public class Point {
    public final Integer x, y;

    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Point move(Integer x, Integer y) {
        if (x == null) x = 0;
        if (y == null) y = 0;
        return new Point(this.x + x, this.y + y);
    }

    private Point(Point other) {
        this.x = other.x;
        this.y = other.y;
    }

    public Point copy() {
        return new Point(this);
    }

    public Point with(Integer x, Integer y) {
        x = x == null ? this.x : x;
        y = y == null ? this.y : y;
        return new Point(x, y);
    }

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }
}
