package org.reusable.tuples.triples;

public final class SimpleTriple<T, U, V> implements Triple<T, U, V> {
    private final T first;
    private final U second;
    private final V third;

    private SimpleTriple(final T first,
                         final U second,
                         final V third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    private SimpleTriple(final Triple<T, U, V> triple) {
        this.first = triple.first();
        this.second = triple.second();
        this.third = triple.third();
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

    public static <T, U, V> SimpleTriple<T, U, V> of(final T first,
                                                     final U second,
                                                     final V third) {
        return new SimpleTriple<>(first, second, third);
    }

    public static <T, U, V> Triple<T, U, V> of(final SimpleTriple<T, U, V> triple) {
        return new SimpleTriple<>(triple);
    }
}
