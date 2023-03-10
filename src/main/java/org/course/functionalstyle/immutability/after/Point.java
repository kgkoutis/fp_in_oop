package org.course.functionalstyle.immutability.after;

import java.util.Objects;

/**
 * This could be a record in later Java versions.
 */
public final class Point {
    public final Integer x, y;

    public Point(final Integer x,
                 final Integer y) {
        Objects.requireNonNull(x);
        Objects.requireNonNull(y);
        this.x = x;
        this.y = y;
    }

    public Point move(final Integer dx,
                      final Integer dy) {
        int newX = this.x;
        int newY = this.y;
        if (dx != null) newX += dx;
        if (dy != null) newY += dy;
        return new Point(newX, newY);
    }

    private Point(final Point other) {
        this.x = other.x;
        this.y = other.y;
    }

    public Point copy() {
        return new Point(this);
    }

    public Point withX(final Integer x) {
        return new Point(x, this.y);
    }

    public Point withY(final Integer y) {
        return new Point(this.x, y);
    }

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }
}
