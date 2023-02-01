package org.reusable.tuples.triples;

import java.util.Comparator;
import java.util.Objects;

public final class ComparableTriple<T extends Comparable<T>, U extends Comparable<U>, V extends Comparable<V>> implements Triple<T, U, V>, Comparable<ComparableTriple<T, U, V>> {
    private final Comparator<T> firstComparator;
    private final Comparator<U> secondComparator;
    private final Comparator<V> thirdComparator;
    private final SimpleTriple<T, U, V> origin;

    private ComparableTriple(final T first,
                             final U second,
                             final V third,
                             final Comparator<T> firstComparator,
                             final Comparator<U> secondComparator,
                             final Comparator<V> thirdComparator) {
        this.origin = SimpleTriple.of(first, second, third);
        this.firstComparator = firstComparator;
        this.secondComparator = secondComparator;
        this.thirdComparator = thirdComparator;
    }

    private ComparableTriple(final Triple<T, U, V> triple,
                             final Comparator<T> firstComparator,
                             final Comparator<U> secondComparator,
                             final Comparator<V> thirdComparator) {
        this.origin = SimpleTriple.of(triple.first(), triple.second(), triple.third());
        this.firstComparator = firstComparator;
        this.secondComparator = secondComparator;
        this.thirdComparator = thirdComparator;
    }

    public static <T extends Comparable<T>, U extends Comparable<U>, V extends Comparable<V>> ComparableTriple<T, U, V> of(final T first,
                                                                                                                           final U second,
                                                                                                                           final V third) {
        return new ComparableTriple<>(first, second, third, Comparator.naturalOrder(), Comparator.naturalOrder(), Comparator.naturalOrder());
    }

    public static <T extends Comparable<T>, U extends Comparable<U>, V extends Comparable<V>> ComparableTriple<T, U, V> of(final T first,
                                                                                                                           final U second,
                                                                                                                           final V third,
                                                                                                                           final Comparator<T> firstComparator,
                                                                                                                           final Comparator<U> secondComparator,
                                                                                                                           final Comparator<V> thirdComparator) {
        return new ComparableTriple<>(first, second, third, firstComparator, secondComparator, thirdComparator);
    }

    public static <T extends Comparable<T>, U extends Comparable<U>, V extends Comparable<V>> ComparableTriple<T, U, V> of(final Triple<T, U, V> triple) {
        return new ComparableTriple<>(triple, Comparator.naturalOrder(), Comparator.naturalOrder(), Comparator.naturalOrder());
    }

    public static <T extends Comparable<T>, U extends Comparable<U>, V extends Comparable<V>> ComparableTriple<T, U, V> of(final Triple<T, U, V> triple,
                                                                                                                           final Comparator<T> firstComparator,
                                                                                                                           final Comparator<U> secondComparator,
                                                                                                                           final Comparator<V> thirdComparator) {
        return new ComparableTriple<>(triple, firstComparator, secondComparator, thirdComparator);
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
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ComparableTriple<?, ?, ?> that = (ComparableTriple<?, ?, ?>) o;
        return Objects.equals(firstComparator, that.firstComparator) &&
                Objects.equals(secondComparator, that.secondComparator) &&
                Objects.equals(thirdComparator, that.thirdComparator) &&
                Objects.equals(origin, that.origin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstComparator, secondComparator, thirdComparator, origin);
    }

    @Override
    public String toString() {
        return "ComparableTriple{" +
                "firstComparator=" + firstComparator +
                ", secondComparator=" + secondComparator +
                ", thirdComparator=" + thirdComparator +
                ", origin=" + origin +
                '}';
    }


    @Override
    public int compareTo(final ComparableTriple<T, U, V> o) {
        final int firstCompare = firstComparator.compare(first(), o.first());
        if (firstCompare != 0) {
            return firstCompare;
        }
        final int secondCompare = secondComparator.compare(second(), o.second());
        if (secondCompare != 0) {
            return secondCompare;
        }
        return thirdComparator.compare(third(), o.third());
    }


    public Comparator<T> getFirstComparator() { return firstComparator ;}
    public Comparator<U> getSecondComparator() { return secondComparator ;}
    public Comparator<V> getThirdComparator() { return thirdComparator ;}
}
