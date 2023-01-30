package org.reusable.tuples.pair;

import java.util.Comparator;
import java.util.Objects;

public class ComparablePair<T extends Comparable<T>, V extends Comparable<V>> implements Pair<T,V>, Comparable<ComparablePair<T, V>> {
    private final Comparator<T> firstComparator;
    private final Comparator<V> secondComparator;
    private final SimplePair<T,V> origin;

    private ComparablePair(T first, V second, Comparator<T> firstComparator, Comparator<V> secondComparator) {
        this.origin = SimplePair.of(first, second);
        this.firstComparator = firstComparator;
        this.secondComparator = secondComparator;
    }

    public static <T extends Comparable<T>, V extends Comparable<V>> ComparablePair<T, V> of(T first, V second) {
        return new ComparablePair<>(first, second, Comparator.naturalOrder(), Comparator.naturalOrder());
    }

    public static <T extends Comparable<T>, V extends Comparable<V>> ComparablePair<T, V> of(T first, V second, Comparator<T> firstComparator, Comparator<V> secondComparator) {
        return new ComparablePair<>(first, second, firstComparator, secondComparator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComparablePair<?, ?> that = (ComparablePair<?, ?>) o;
        return Objects.equals(firstComparator, that.firstComparator) && Objects.equals(secondComparator, that.secondComparator) && Objects.equals(origin, that.origin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstComparator, secondComparator, origin);
    }

    @Override
    public String toString() {
        return "ComparablePair{" +
                "firstComparator=" + firstComparator +
                ", secondComparator=" + secondComparator +
                ", first=" + origin.first() +
                ", second=" + origin.second() +
                '}';
    }

    @Override
    public int compareTo(ComparablePair<T, V> o) {
        int firstCompare = firstComparator.compare(origin.first(), o.origin.first());
        if (firstCompare != 0) {
            return firstCompare;
        }
        return secondComparator.compare(origin.second(), o.origin.second());
    }

    public T first() {
        return origin.first();
    }

    public V second() {
        return origin.second();
    }
}
