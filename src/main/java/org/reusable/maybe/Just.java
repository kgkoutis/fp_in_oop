package org.reusable.maybe;

import org.reusable.maybe.visitors.MaybeVisitor;

import java.util.Objects;

public final class Just<T> extends Maybe<T> {
    private final T value;

    public Just(final T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public <R> R accept(final MaybeVisitor<T, R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Just<?> just = (Just<?>) o;
        return Objects.equals(value, just.value);
    }

    @Override
    public String toString() {
        return "Just{" +
                "value=" + value +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
