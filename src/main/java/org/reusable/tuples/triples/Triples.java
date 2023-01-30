package org.reusable.tuples.triples;

public final class Triples {
    public static <U, V, R> Triple<R, U, V> of(final R apply,
                                               final U second,
                                               final V third) {
        return SimpleTriple.of(apply, second, third);
    }

    public static <T extends Comparable<T>, U extends Comparable<U>, V extends Comparable<V>> ComparableTriple<T, U, V> of(final T first,
                                                                                                                           final U second,
                                                                                                                           final V third) {
        return ComparableTriple.of(first, second, third);
    }
}
