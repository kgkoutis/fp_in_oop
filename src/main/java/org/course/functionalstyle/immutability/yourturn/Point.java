package org.course.functionalstyle.immutability.yourturn;

public final class Point {
    public Integer x, y;

    public Point(final Integer x,
                 final Integer y) {
        this.x = x;
        this.y = y;
    }

    public void move(Integer dx,
                     Integer dy) {
        if (dx == null) dx = 0;
        if (dy == null) dy = 0;
        this.x += dx;
        this.y += dy;
    }

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }
}
