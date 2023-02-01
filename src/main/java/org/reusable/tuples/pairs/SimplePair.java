package org.reusable.tuples.pairs;

import java.util.Objects;

public final class SimplePair<T, V> implements Pair<T, V> {
    private final T first;
    private final V second;

    private SimplePair(final T first,
                       final V second) {
        this.first = first;
        this.second = second;
    }

    private SimplePair(final Pair<T, V> pair) {
        this.first = pair.first();
        this.second = pair.second();
    }

    public static <T, V> SimplePair<T, V> of(final T first,
                                             final V second) {
        return new SimplePair<>(first, second);
    }

    public static <T, V> SimplePair<T, V> of(final Pair<T, V> pair) {
        return new SimplePair<>(pair);
    }

    public T first() {
        return first;
    }

    public V second() {
        return second;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SimplePair<?, ?> pair = (SimplePair<?, ?>) o;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}


