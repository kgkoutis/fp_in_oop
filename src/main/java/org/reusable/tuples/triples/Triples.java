package org.reusable.tuples.triples;

import java.util.Comparator;

public final class Triples {
    public static <U, V, R> SimpleTriple<R, U, V> of(final R apply,
                                               final U second,
                                               final V third) {
        return SimpleTriple.of(apply, second, third);
    }

    public static <T extends Comparable<T>, U extends Comparable<U>, V extends Comparable<V>> ComparableTriple<T, U, V> of(final T first,
                                                                                                                           final U second,
                                                                                                                           final V third) {
        return ComparableTriple.of(first, second, third);
    }

    public static <T extends Comparable<T>, U extends Comparable<U>, V extends Comparable<V>> ComparableTriple<T, U, V> of(final T first,
                                                                                                                           final U second,
                                                                                                                           final V third,
                                                                                                                           final Comparator<T> firstComparator,
                                                                                                                           final Comparator<U> secondComparator,
                                                                                                                           final Comparator<V> thirdComparator) {
        return ComparableTriple.of(first, second, third, firstComparator, secondComparator, thirdComparator);
    }

    public static <T extends Comparable<T>, U extends Comparable<U>, V extends Comparable<V>> Triple<T, U, V> of(final Triple<T, U, V> triple) {
        return ComparableTriple.of(triple);
    }

    public static <T extends Comparable<T>, U extends Comparable<U>, V extends Comparable<V>> Triple<T, U, V> of(final ComparableTriple<T, U, V> triple,
                                                                                                                 final Comparator<T> firstComparator,
                                                                                                                 final Comparator<U> secondComparator,
                                                                                                                 final Comparator<V> thirdComparator) {
        return ComparableTriple.of(triple, firstComparator, secondComparator, thirdComparator);
    }
}
