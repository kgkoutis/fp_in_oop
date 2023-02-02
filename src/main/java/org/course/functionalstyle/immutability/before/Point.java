package org.course.functionalstyle.immutability.before;

import java.util.Objects;

public final class Point {
    public Integer x, y;

    public Point(final Integer x,
                 final Integer y) {
        Objects.requireNonNull(x);
        Objects.requireNonNull(y);
        this.x = x;
        this.y = y;
    }

    public void move(Integer x,
                     Integer y) {
        if (x == null) x = 0;
        if (y == null) y = 0;
        this.x += x;
        this.y += y;
    }

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }
}
