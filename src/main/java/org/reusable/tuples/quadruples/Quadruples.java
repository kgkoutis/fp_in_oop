package org.reusable.tuples.quadruples;

import java.util.Comparator;

public final class Quadruples {
    public static <T, V, U, W> SimpleQuadruple<T, V, U, W> of(final T first,
                                                              final V second,
                                                              final U third,
                                                              final W fourth) {
        return SimpleQuadruple.of(first, second, third, fourth);
    }

    public static <T extends Comparable<T>, V extends Comparable<V>, U extends Comparable<U>, W extends Comparable<W>> ComparableQuadruple<T, V, U, W> of(final T first,
                                                                                                                                                          final V second,
                                                                                                                                                          final U third,
                                                                                                                                                          final W fourth) {
        return ComparableQuadruple.of(first, second, third, fourth);
    }

    public static <T extends Comparable<T>, V extends Comparable<V>, U extends Comparable<U>, W extends Comparable<W>> ComparableQuadruple<T, V, U, W> of(final T first,
                                                                                                                                                          final V second,
                                                                                                                                                          final U third,
                                                                                                                                                          final W fourth,
                                                                                                                                                          final Comparator<T> firstComparator,
                                                                                                                                                          final Comparator<V> secondComparator,
                                                                                                                                                          final Comparator<U> thirdComparator,
                                                                                                                                                          final Comparator<W> fourthComparator) {
        return ComparableQuadruple.of(first, second, third, fourth, firstComparator, secondComparator, thirdComparator, fourthComparator);
    }

    public static <T extends Comparable<T>, V extends Comparable<V>, U extends Comparable<U>, W extends Comparable<W>> Quadruple<T, V, U, W> of(final Quadruple<T, V, U, W> quadruple) {
        return ComparableQuadruple.of(quadruple);
    }

    public static <T extends Comparable<T>, V extends Comparable<V>, U extends Comparable<U>, W extends Comparable<W>> Quadruple<T, V, U, W> of(final ComparableQuadruple<T, V, U, W> quadruple,
                                                                                                                                                final Comparator<T> firstComparator,
                                                                                                                                                final Comparator<V> secondComparator,
                                                                                                                                                final Comparator<U> thirdComparator,
                                                                                                                                                final Comparator<W> fourthComparator) {
        return ComparableQuadruple.of(quadruple, firstComparator, secondComparator, thirdComparator, fourthComparator);
    }
}
