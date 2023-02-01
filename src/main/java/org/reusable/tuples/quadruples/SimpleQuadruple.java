package org.reusable.tuples.quadruples;

import java.util.Objects;

public final class SimpleQuadruple<T, U, V, W> implements Quadruple<T, U, V, W> {
    private final T first;
    private final U second;
    private final V third;
    private final W fourth;

    private SimpleQuadruple(final T first,
                            final U second,
                            final V third,
                            final W fourth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
    }

    private SimpleQuadruple(final Quadruple<T, U, V, W> other) {
        this.first = other.first();
        this.second = other.second();
        this.third = other.third();
        this.fourth = other.fourth();
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

    public static <T, U, V, W> SimpleQuadruple<T, U, V, W> of(final T first,
                                                              final U second,
                                                              final V third,
                                                              final W fourth) {
        return new SimpleQuadruple<>(first, second, third, fourth);
    }

    public static <T, U, V, W> Quadruple<T, U, V, W> of(final SimpleQuadruple<T, U, V, W> other) {
        return new SimpleQuadruple<>(other);
    }

    @Override
    public String toString() {
        return "Quadruple [first=" + first + ", second=" + second + ", third=" + third + ", fourth=" + fourth + "]";
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final SimpleQuadruple<?, ?, ?, ?> other = (SimpleQuadruple<?, ?, ?, ?>) obj;
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
            return other.fourth == null;
        } else return fourth.equals(other.fourth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, third, fourth);
    }
}

