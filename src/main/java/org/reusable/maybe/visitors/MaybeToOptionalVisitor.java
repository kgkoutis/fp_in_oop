package org.reusable.maybe.visitors;

import org.reusable.maybe.Just;
import org.reusable.maybe.Maybe;
import org.reusable.maybe.Nothing;
import java.util.Optional;

public final class MaybeToOptionalVisitor<T> implements MaybeVisitor<T, Optional<T>> {
    @Override
    public Optional<T> visit(final Just<T> just) {
        return Optional.of(just.getValue());
    }

    @Override
    public Optional<T> visit(final Nothing<T> nothing) {
        return Optional.empty();
    }
}
