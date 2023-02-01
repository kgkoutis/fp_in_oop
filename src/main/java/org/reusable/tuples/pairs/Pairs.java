package org.reusable.tuples.pairs;

import java.util.Comparator;

public final class Pairs {
    public static <T, V> SimplePair<T, V> of(final T first,
                                             final V second) {
        return SimplePair.of(first, second);
    }

    public static <T extends Comparable<T>, V extends Comparable<V>> ComparablePair<T, V> of(final T first,
                                                                                             final V second) {
        return ComparablePair.of(first, second);
    }

    public static <T extends Comparable<T>, V extends Comparable<V>> ComparablePair<T, V> of(final T first,
                                                                                             final V second,
                                                                                             final Comparator<T> firstComparator,
                                                                                             final Comparator<V> secondComparator) {
        return ComparablePair.of(first, second, firstComparator, secondComparator);
    }

    public static <T extends Comparable<T>, V extends Comparable<V>> ComparablePair<T, V> of(final Pair<T, V> pair) {
        return ComparablePair.of(pair);
    }

    public static <T extends Comparable<T>, V extends Comparable<V>> ComparablePair<T, V> of(final Pair<T, V> pair,
                                                                                             final Comparator<T> firstComparator,
                                                                                             final Comparator<V> secondComparator) {
        return ComparablePair.of(pair, firstComparator, secondComparator);
    }
}
