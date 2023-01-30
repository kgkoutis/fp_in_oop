package org.reusable.either;

import org.reusable.either.visitors.EitherVisitor;

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
}
