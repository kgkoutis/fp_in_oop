package org.reusable.tuples.pair;
import java.util.Objects;

public class SimplePair<T, V> implements Pair<T,V> {
    protected final T first;
    protected final V second;

    private SimplePair(T first, V second) {
        this.first = first;
        this.second = second;
    }

    public static <T, V> SimplePair<T, V> of(T first, V second) {
        return new SimplePair<>(first, second);
    }

    public T first() {
        return first;
    }

    public V second() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimplePair<?, ?> pair = (SimplePair<?, ?>) o;
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


