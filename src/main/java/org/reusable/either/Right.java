package org.reusable.either;

import org.reusable.either.visitors.EitherVisitor;

import java.util.Objects;

public final class Right<L, R> extends Either<L, R> {
    private final R value;

    public Right(final R value) {
        if (value == null) {
            throw new IllegalArgumentException("Right value cannot be null");
        }
        this.value = value;
    }

    public R getValue() {
        return value;
    }

    @Override
    public <T> T accept(final EitherVisitor<L, R, T> visitor) {
        return visitor.visit(this);
    }

    public R getRight() {
        return this.value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Right<?, ?> right = (Right<?, ?>) o;
        return Objects.equals(value, right.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Right{" +
                "value=" + value +
                '}';
    }
}
