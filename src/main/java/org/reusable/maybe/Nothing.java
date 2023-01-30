package org.reusable.maybe;

import org.reusable.maybe.visitors.MaybeVisitor;

public final class Nothing<T> extends Maybe<T> {
    @Override
    public <R> R accept(MaybeVisitor<T, R> visitor) {
        return visitor.visit(this);
    }
}
