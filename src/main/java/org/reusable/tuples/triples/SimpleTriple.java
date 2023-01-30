package org.reusable.tuples.triples;

public class SimpleTriple<T, U, V> implements Triple<T, U, V> {
    private final T first;
    private final U second;
    private final V third;

    private SimpleTriple(T first, U second, V third) {
        this.first = first;
        this.second = second;
        this.third = third;
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

    public static <T, U, V> SimpleTriple<T, U, V> of(T first, U second, V third) {
        return new SimpleTriple<>(first, second, third);
    }
}
