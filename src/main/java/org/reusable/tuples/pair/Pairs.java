package org.reusable.tuples.pair;

public final class Pairs {
    public static <T, V> SimplePair<T, V> of(final T first,
                                             final V second) {
        return SimplePair.of(first, second);
    }

    public static <T extends Comparable<T>, V extends Comparable<V>> ComparablePair<T, V> of(final T first,
                                                                                             final V second) {
        return ComparablePair.of(first, second);
    }
}
