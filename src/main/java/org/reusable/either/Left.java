package org.reusable.either;

import org.reusable.either.visitors.EitherVisitor;

import java.util.Objects;

public final class Left<L, R> extends Either<L, R> {
    private final L value;

    public Left(final L value) {
        if (value == null) {
            throw new IllegalArgumentException("Left value cannot be null");
        }
        this.value = value;
    }

    public L getValue() {
        return value;
    }

    @Override
    public <T> T accept(final EitherVisitor<L, R, T> visitor) {
        return visitor.visit(this);
    }

    public L getLeft() {
        return this.value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Left<?, ?> left = (Left<?, ?>) o;
        return Objects.equals(value, left.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Left{" +
                "value=" + value +
                '}';
    }
}
