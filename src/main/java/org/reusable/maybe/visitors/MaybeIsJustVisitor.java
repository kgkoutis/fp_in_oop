package org.reusable.maybe.visitors;

import org.reusable.maybe.Just;
import org.reusable.maybe.Nothing;

public final class MaybeIsJustVisitor<T> implements MaybeVisitor<T, Boolean> {
    @Override
    public Boolean visit(final Just<T> just) {
        return true;
    }

    @Override
    public Boolean visit(final Nothing<T> nothing) {
        return false;
    }
}
