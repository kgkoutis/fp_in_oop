package org.reusable.tuples.quadruple;

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
}
