package org.reusable.tuples.quintuples;

import java.util.Objects;

public final class SimpleQuintuple<T, U, V, W, X> implements Quintuple<T, U, V, W, X> {
    private final T first;
    private final U second;
    private final V third;
    private final W fourth;
    private final X fifth;

    private SimpleQuintuple(final T first,
                            final U second,
                            final V third,
                            final W fourth,
                            final X fifth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
    }

    private SimpleQuintuple(final Quintuple<T, U, V, W, X> quintuple) {
        this(quintuple.first(), quintuple.second(), quintuple.third(), quintuple.fourth(), quintuple.fifth());
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

    public X fifth() {
        return fifth;
    }

    public static <T, U, V, W, X> SimpleQuintuple<T, U, V, W, X> of(final T first,
                                                                    final U second,
                                                                    final V third,
                                                                    final W fourth,
                                                                    final X fifth) {
        return new SimpleQuintuple<>(first, second, third, fourth, fifth);
    }

    public static <T, U, V, W, X> SimpleQuintuple<T, U, V, W, X> of(final Quintuple<T, U, V, W, X> quintuple) {
        return new SimpleQuintuple<>(quintuple);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SimpleQuintuple<?, ?, ?, ?, ?> that = (SimpleQuintuple<?, ?, ?, ?, ?>) o;
        return Objects.equals(first, that.first) &&
                Objects.equals(second, that.second) &&
                Objects.equals(third, that.third) &&
                Objects.equals(fourth, that.fourth) &&
                Objects.equals(fifth, that.fifth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, third, fourth, fifth);
    }

    @Override
    public String toString() {
        return "SimpleQuintuple{" +
                "first=" + first +
                ", second=" + second +
                ", third=" + third +
                ", fourth=" + fourth +
                ", fifth=" + fifth +
                '}';
    }
}
