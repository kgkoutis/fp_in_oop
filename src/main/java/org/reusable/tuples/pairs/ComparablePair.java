package org.reusable.tuples.pairs;

import java.util.Comparator;
import java.util.Objects;

public final class ComparablePair<T extends Comparable<T>, V extends Comparable<V>> implements Pair<T, V>, Comparable<ComparablePair<T, V>> {
    private final Comparator<T> firstComparator;
    private final Comparator<V> secondComparator;
    private final SimplePair<T, V> origin;

    private ComparablePair(final T first,
                           final V second,
                           final Comparator<T> firstComparator,
                           final Comparator<V> secondComparator) {
        this.origin = SimplePair.of(first, second);
        this.firstComparator = firstComparator;
        this.secondComparator = secondComparator;
    }

    private ComparablePair(final Pair<T, V> pair,
                           final Comparator<T> firstComparator,
                           final Comparator<V> secondComparator) {
        this.origin = SimplePair.of(pair.first(), pair.second());
        this.firstComparator = firstComparator;
        this.secondComparator = secondComparator;
    }

    public static <T extends Comparable<T>, V extends Comparable<V>> ComparablePair<T, V> of(final T first,
                                                                                             final V second) {
        return new ComparablePair<>(first, second, Comparator.naturalOrder(), Comparator.naturalOrder());
    }

    public static <T extends Comparable<T>, V extends Comparable<V>> ComparablePair<T, V> of(final T first,
                                                                                             final V second,
                                                                                             final Comparator<T> firstComparator,
                                                                                             final Comparator<V> secondComparator) {
        return new ComparablePair<>(first, second, firstComparator, secondComparator);
    }

    public static <T extends Comparable<T>, V extends Comparable<V>> ComparablePair<T, V> of(final Pair<T, V> pair) {
        return new ComparablePair<>(pair, Comparator.naturalOrder(), Comparator.naturalOrder());
    }

    public static <T extends Comparable<T>, V extends Comparable<V>> ComparablePair<T, V> of(final Pair<T, V> pair,
                                                                                             final Comparator<T> firstComparator,
                                                                                             final Comparator<V> secondComparator) {
        return new ComparablePair<>(pair, firstComparator, secondComparator);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ComparablePair<?, ?> that = (ComparablePair<?, ?>) o;
        return Objects.equals(firstComparator, that.firstComparator) &&
                Objects.equals(secondComparator, that.secondComparator) &&
                Objects.equals(origin, that.origin);
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
    public int compareTo(final ComparablePair<T, V> o) {
        final int firstCompare = firstComparator.compare(origin.first(), o.origin.first());
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

    public Comparator<T> getFirstComparator() { return firstComparator ;}
    public Comparator<V> getSecondComparator() { return secondComparator ;}
}
