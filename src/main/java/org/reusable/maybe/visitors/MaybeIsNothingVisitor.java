package org.reusable.maybe.visitors;

import org.reusable.maybe.Just;
import org.reusable.maybe.Nothing;

public final class MaybeIsNothingVisitor<T> implements MaybeVisitor<T, Boolean> {
    @Override
    public Boolean visit(final Just<T> just) {
        return false;
    }

    @Override
    public Boolean visit(final Nothing<T> nothing) {
        return true;
    }
}