package org.reusable.tuples.quadruple;

import java.util.Objects;

public class SimpleQuadruple<T, U, V, W> implements Quadruple<T, U, V, W> {
    private final T first;
    private final U second;
    private final V third;
    private final W fourth;

    private SimpleQuadruple(T first, U second, V third, W fourth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
    }

    public T first() {
        return first;
    }

    public U second() {
        return second;
    }

    public V third() {
        return third;
    }

    public W fourth() {
        return fourth;
    }

    public static <T, U, V, W> SimpleQuadruple<T, U, V, W> of(T first, U second, V third, W fourth) {
        return new SimpleQuadruple<>(first, second, third, fourth);
    }

    @Override
    public String toString() {
        return "Quadruple [first=" + first + ", second=" + second + ", third=" + third + ", fourth=" + fourth + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        SimpleQuadruple<?, ?, ?, ?> other = (SimpleQuadruple<?, ?, ?, ?>) obj;
        if (first == null) {
            if (other.first != null) return false;
        } else if (!first.equals(other.first)) return false;
        if (second == null) {
            if (other.second != null) return false;
        } else if (!second.equals(other.second)) return false;
        if (third == null) {
            if (other.third != null) return false;
        } else if (!third.equals(other.third)) return false;
        if (fourth == null) {
            if (other.fourth != null) return false;
        } else if (!fourth.equals(other.fourth)) return false;
        return true;
    }

    @Override
    public int hashCode() {
       return Objects.hash(first, second, third, fourth);
    }
}

