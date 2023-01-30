package org.reusable.tuples.triples;

final public class Triples {
    public static <U, V, R> Triple<R,U,V> of(R apply, U second, V third) {
        return SimpleTriple.of(apply, second, third);
    }

    public static <T extends Comparable<T>, U extends Comparable<U>, V extends Comparable<V>> ComparableTriple<T, U, V> of(T first, U second, V third) {
        return ComparableTriple.of(first, second, third);
    }
}
