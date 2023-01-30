package org.reusable.tuples.quadruple;

import java.util.Comparator;
import java.util.Objects;

public final class ComparableQuadruple<T extends Comparable<T>, U extends Comparable<U>, V extends Comparable<V>, W extends Comparable<W>> implements Quadruple<T, U, V, W>, Comparable<ComparableQuadruple<T, U, V, W>> {
    private final Comparator<T> firstComparator;
    private final Comparator<U> secondComparator;
    private final Comparator<V> thirdComparator;
    private final Comparator<W> fourthComparator;

    private final SimpleQuadruple<T, U, V, W> origin;

    private ComparableQuadruple(final T first,
                                final U second,
                                final V third,
                                final W fourth,
                                final Comparator<T> firstComparator,
                                final Comparator<U> secondComparator,
                                final Comparator<V> thirdComparator,
                                final Comparator<W> fourthComparator) {
        this.origin = SimpleQuadruple.of(first, second, third, fourth);
        this.firstComparator = firstComparator;
        this.secondComparator = secondComparator;
        this.thirdComparator = thirdComparator;
        this.fourthComparator = fourthComparator;
    }

    public static <T extends Comparable<T>, U extends Comparable<U>, V extends Comparable<V>, W extends Comparable<W>> ComparableQuadruple<T, U, V, W> of(final T first,
                                                                                                                                                          final U second,
                                                                                                                                                          final V third,
                                                                                                                                                          final W fourth) {
        return new ComparableQuadruple<>(first, second, third, fourth, Comparator.naturalOrder(), Comparator.naturalOrder(), Comparator.naturalOrder(), Comparator.naturalOrder());
    }

    public static <T extends Comparable<T>, U extends Comparable<U>, V extends Comparable<V>, W extends Comparable<W>> ComparableQuadruple<T, U, V, W> of(final T first,
                                                                                                                                                          final U second,
                                                                                                                                                          final V third,
                                                                                                                                                          final W fourth,
                                                                                                                                                          final Comparator<T> firstComparator,
                                                                                                                                                          final Comparator<U> secondComparator,
                                                                                                                                                          final Comparator<V> thirdComparator,
                                                                                                                                                          final Comparator<W> fourthComparator) {
        return new ComparableQuadruple<>(first, second, third, fourth, firstComparator, secondComparator, thirdComparator, fourthComparator);
    }


    @Override
    public int compareTo(final ComparableQuadruple<T, U, V, W> o) {
        int result = firstComparator.compare(first(), o.first());
        if (result != 0) {
            return result;
        }
        result = secondComparator.compare(second(), o.second());
        if (result != 0) {
            return result;
        }
        result = thirdComparator.compare(third(), o.third());
        if (result != 0) {
            return result;
        }
        return fourthComparator.compare(fourth(), o.fourth());
    }

    @Override
    public T first() {
        return origin.first();
    }

    @Override
    public U second() {
        return origin.second();
    }

    @Override
    public V third() {
        return origin.third();
    }

    @Override
    public W fourth() {
        return origin.fourth();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ComparableQuadruple<?, ?, ?, ?> that = (ComparableQuadruple<?, ?, ?, ?>) o;
        return Objects.equals(firstComparator, that.firstComparator) &&
                Objects.equals(secondComparator, that.secondComparator) &&
                Objects.equals(thirdComparator, that.thirdComparator) &&
                Objects.equals(fourthComparator, that.fourthComparator) &&
                Objects.equals(origin, that.origin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstComparator, secondComparator, thirdComparator, fourthComparator, origin);
    }

    @Override
    public String toString() {
        return "ComparableQuadruple{" +
                "firstComparator=" + firstComparator +
                ", secondComparator=" + secondComparator +
                ", thirdComparator=" + thirdComparator +
                ", fourthComparator=" + fourthComparator +
                ", origin=" + origin +
                '}';
    }
}
