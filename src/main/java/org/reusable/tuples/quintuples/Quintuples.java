package org.reusable.tuples.quintuples;

import java.util.Comparator;

public final class Quintuples {

    public static <T, U, V, W, X> Quintuple<T, U, V, W, X> of(final T first,
                                                              final U second,
                                                              final V third,
                                                              final W fourth,
                                                              final X fifth) {
        return SimpleQuintuple.of(first, second, third, fourth, fifth);
    }

    public static <T extends Comparable<T>, U extends Comparable<U>, V extends Comparable<V>, W extends Comparable<W>, X extends Comparable<X>> ComparableQuintuple<T, U, V, W, X> of(final T first,
                                                                                                                                                                                      final U second,
                                                                                                                                                                                      final V third,
                                                                                                                                                                                      final W fourth,
                                                                                                                                                                                      final X fifth) {
        return ComparableQuintuple.of(first, second, third, fourth, fifth);
    }

    public static <T extends Comparable<T>, U extends Comparable<U>, V extends Comparable<V>, W extends Comparable<W>, X extends Comparable<X>> ComparableQuintuple<T, U, V, W, X> of(final T first,
                                                                                                                                                                                      final U second,
                                                                                                                                                                                      final V third,
                                                                                                                                                                                      final W fourth,
                                                                                                                                                                                      final X fifth,
                                                                                                                                                                                      final Comparator<T> firstComparator,
                                                                                                                                                                                      final Comparator<U> secondComparator,
                                                                                                                                                                                      final Comparator<V> thirdComparator,
                                                                                                                                                                                      final Comparator<W> fourthComparator,
                                                                                                                                                                                      final Comparator<X> fifthComparator) {
        return ComparableQuintuple.of(first, second, third, fourth, fifth, firstComparator, secondComparator, thirdComparator, fourthComparator, fifthComparator);
    }

    public static <T extends Comparable<T>, U extends Comparable<U>, V extends Comparable<V>, W extends Comparable<W>, X extends Comparable<X>> ComparableQuintuple<T, U, V, W, X> of(final Quintuple<T, U, V, W, X> quintuple) {
        return ComparableQuintuple.of(quintuple);
    }

    public static <T extends Comparable<T>, U extends Comparable<U>, V extends Comparable<V>, W extends Comparable<W>, X extends Comparable<X>> ComparableQuintuple<T, U, V, W, X> of(final Quintuple<T, U, V, W, X> quintuple,
                                                                                                                                                                                      final Comparator<T> firstComparator,
                                                                                                                                                                                      final Comparator<U> secondComparator,
                                                                                                                                                                                      final Comparator<V> thirdComparator,
                                                                                                                                                                                      final Comparator<W> fourthComparator,
                                                                                                                                                                                      final Comparator<X> fifthComparator) {
        return ComparableQuintuple.of(quintuple, firstComparator, secondComparator, thirdComparator, fourthComparator, fifthComparator);
    }

}
