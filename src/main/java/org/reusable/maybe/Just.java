package org.reusable.maybe;

import org.reusable.maybe.visitors.MaybeVisitor;

public final class Just<T> extends Maybe<T> {
    private final T value;

    public Just(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public <R> R accept(MaybeVisitor<T, R> visitor) {
        return visitor.visit(this);
    }
}