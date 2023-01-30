package org.reusable.lazy;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Thunk<T> implements Supplier<T> {

    private static class Holder<T> implements Supplier<T> {

        private final T value;

        Holder(T value) {
            this.value = value;
        }

        @Override
        public T get() {
            return this.value;
        }
    }

    private Supplier<T> holder;

    private Thunk(Supplier<T> expression) {
        this.holder = () -> evaluate(expression);
    }

    private synchronized T evaluate(Supplier<T> expression) {
        if (!(this.holder instanceof Thunk.Holder)) {
            var evaluated = expression.get();
            this.holder = new Holder<>(evaluated);
        }
        return this.holder.get();
    }

    @Override
    public T get() {
        return this.holder.get();
    }

    public static <T> Thunk<T> of(T value) {
        return new Thunk<>(() -> value);
    }
    public static <V> Thunk<V> of(Supplier<V> expression) {
        if (expression instanceof Thunk<V>) {
            return (Thunk<V>) expression;
        }
        return new Thunk<>(expression);
    }
    public <R> Thunk<R> map(Function<T, R> mapper) {
        return Thunk.of(() -> mapper.apply(get()));
    }

    public <R> Thunk<R> flatMap(Function<T, Thunk<R>> mapper) {
        return Thunk.of(() -> mapper.apply(get()).get());
    }

    public void accept(Consumer<T> consumer) {
        consumer.accept(get());
    }
}