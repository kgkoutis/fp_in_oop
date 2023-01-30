package org.reusable.tuples.quintuples;

import java.util.Comparator;
import java.util.Objects;

public class ComparableQuintuple<T extends Comparable<T>, U extends Comparable<U>, V extends Comparable<V>, W extends Comparable<W>, X extends Comparable<X>> implements Quintuple<T, U, V, W, X>, Comparable<Quintuple<T, U, V, W, X>> {
    private final Comparator<T> firstComparator;
    private final Comparator<U> secondComparator;
    private final Comparator<V> thirdComparator;
    private final Comparator<W> fourthComparator;
    private final Comparator<X> fifthComparator;

    private final SimpleQuintuple<T, U, V, W, X> origin;

    protected ComparableQuintuple(T first, U second, V third, W fourth, X fifth, Comparator<T> firstComparator, Comparator<U> secondComparator, Comparator<V> thirdComparator, Comparator<W> fourthComparator, Comparator<X> fifthComparator) {
        this.origin = SimpleQuintuple.of(first, second, third, fourth, fifth);
        this.firstComparator = firstComparator;
        this.secondComparator = secondComparator;
        this.thirdComparator = thirdComparator;
        this.fourthComparator = fourthComparator;
        this.fifthComparator = fifthComparator;
    }
    public static <T extends Comparable<T>, U extends Comparable<U>, V extends Comparable<V>, W extends Comparable<W>, X extends Comparable<X>> ComparableQuintuple<T, U, V, W, X> of(T first, U second, V third, W fourth, X fifth) {
        return new ComparableQuintuple<>(first, second, third, fourth, fifth, Comparator.naturalOrder(), Comparator.naturalOrder(), Comparator.naturalOrder(), Comparator.naturalOrder(), Comparator.naturalOrder());
    }

    public static <T extends Comparable<T>, U extends Comparable<U>, V extends Comparable<V>, W extends Comparable<W>, X extends Comparable<X>> ComparableQuintuple<T, U, V, W, X> of(T first, U second, V third, W fourth, X fifth, Comparator<T> firstComparator, Comparator<U> secondComparator, Comparator<V> thirdComparator, Comparator<W> fourthComparator, Comparator<X> fifthComparator) {
        return new ComparableQuintuple<>(first, second, third, fourth, fifth, firstComparator, secondComparator, thirdComparator, fourthComparator, fifthComparator);
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
    public X fifth() {return origin.fifth();}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComparableQuintuple<?, ?, ?, ?, ?> that = (ComparableQuintuple<?, ?, ?, ?, ?>) o;
        return Objects.equals(firstComparator, that.firstComparator) && Objects.equals(secondComparator, that.secondComparator) && Objects.equals(thirdComparator, that.thirdComparator) && Objects.equals(fourthComparator, that.fourthComparator) && Objects.equals(fifthComparator, that.fifthComparator) && Objects.equals(origin, that.origin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstComparator, secondComparator, thirdComparator, fourthComparator, fifthComparator, origin);
    }

    @Override
    public String toString() {
        return "ComparableQuintuple{" +
                "firstComparator=" + firstComparator +
                ", secondComparator=" + secondComparator +
                ", thirdComparator=" + thirdComparator +
                ", fourthComparator=" + fourthComparator +
                ", fifthComparator=" + fifthComparator +
                ", origin=" + origin +
                '}';
    }

    @Override
    public int compareTo(Quintuple<T, U, V, W, X> o) {
        int firstCompare = firstComparator.compare(first(), o.first());
        if (firstCompare != 0) {
            return firstCompare;
        }
        int secondCompare = secondComparator.compare(second(), o.second());
        if (secondCompare != 0) {
            return secondCompare;
        }
        int thirdCompare = thirdComparator.compare(third(), o.third());
        if (thirdCompare != 0) {
            return thirdCompare;
        }
        int fourthCompare = fourthComparator.compare(fourth(), o.fourth());
        if (fourthCompare != 0) {
            return fourthCompare;
        }
        return fifthComparator.compare(fifth(), o.fifth());
    }
}
