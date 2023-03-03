package org.reusable.lazy;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class Thunk<T> implements Supplier<T> {

    private static class Holder<T> implements Supplier<T> {

        private final T value;

        Holder(final T value) {
            this.value = value;
        }

        @Override
        public T get() {
            return this.value;
        }
    }

    private Supplier<T> holder;

    private Thunk(final Supplier<T> expression) {
        this.holder = () -> evaluate(expression);
    }

    private synchronized T evaluate(final Supplier<T> expression) {
        if (!(this.holder instanceof Thunk.Holder)) {
            final T evaluated = expression.get();
            this.holder = new Holder<>(evaluated);
        }
        return this.holder.get();
    }

    @Override
    public T get() {
        return this.holder.get();
    }

    public static <T> Thunk<T> of(final T value) {
        return new Thunk<>(() -> value);
    }

    public static <V> Thunk<V> of(final Supplier<V> expression) {
        if (expression instanceof Thunk<?>) {
            return (Thunk<V>) expression;
        }
        return new Thunk<>(expression);
    }

    public <R> Thunk<R> map(final Function<T, R> mapper) {
        return Thunk.of(() -> mapper.apply(get()));
    }

    public <R> Thunk<R> flatMap(final Function<T, Thunk<R>> mapper) {
        return Thunk.of(() -> mapper.apply(get()).get());
    }

    public void accept(final Consumer<T> consumer) {
        consumer.accept(get());
    }
}