package org.reusable.either;

import org.reusable.either.visitors.EitherVisitor;

public class Right<L, R> extends Either<L, R> {
    private final R value;

    public Right(R value) {
        if (value == null) {
            throw new IllegalArgumentException("Right value cannot be null");
        }
        this.value = value;
    }

    public R getValue() {
        return value;
    }

    @Override
    public <T> T accept(EitherVisitor<L, R, T> visitor) {
        return visitor.visit(this);
    }

    public R getRight() {
        return this.value;
    }
}
