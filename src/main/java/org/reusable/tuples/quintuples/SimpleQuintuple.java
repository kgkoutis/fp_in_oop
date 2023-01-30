package org.reusable.tuples.quintuples;

import java.util.Objects;

public class SimpleQuintuple<T, U, V, W, X> implements Quintuple<T, U, V, W, X> {
    private final T first;
    private final U second;
    private final V third;
    private final W fourth;
    private final X fifth;

    private SimpleQuintuple(T first, U second, V third, W fourth, X fifth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
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

    public static <T, U, V, W, X> SimpleQuintuple<T, U, V, W, X> of(T first, U second, V third, W fourth, X fifth) {
        return new SimpleQuintuple<>(first, second, third, fourth, fifth);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleQuintuple<?, ?, ?, ?, ?> that = (SimpleQuintuple<?, ?, ?, ?, ?>) o;
        return Objects.equals(first, that.first) && Objects.equals(second, that.second) && Objects.equals(third, that.third) && Objects.equals(fourth, that.fourth) && Objects.equals(fifth, that.fifth);
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
