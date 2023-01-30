package org.reusable.tuples.pair;

final public class Pairs {
    public static <T, V> SimplePair<T, V> of(T first, V second) {
        return SimplePair.of(first, second);
    }

    public static <T extends Comparable<T>, V extends Comparable<V>> ComparablePair<T, V> of(T first, V second) {
        return ComparablePair.of(first, second);
    }
}
