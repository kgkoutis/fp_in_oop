package org.reusable;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class Result<V, E extends Throwable> {
    private final V value;
    private final E throwable;
    private final boolean isSuccess;

    public Result(final V value,
                  final E throwable,
                  final boolean isSuccess) {
        this.value = value;
        this.throwable = throwable;
        this.isSuccess = isSuccess;
    }

    public static <V, E extends Throwable> Result<V, E> success(final V value) {
        return new Result<>(value, null, true);
    }

    public static <V, E extends Throwable> Result<V, E> failure(final E throwable) {
        return new Result<>(null, throwable, false);
    }

    public <R> Optional<R> mapSuccess(final Function<V, R> fn) {
        return this.isSuccess ? Optional.ofNullable(this.value).map(fn)
                : Optional.empty();
    }

    public <R> Optional<R> mapFailure(final Function<E, R> fn) {
        return this.isSuccess ? Optional.empty()
                : Optional.ofNullable(this.throwable).map(fn);
    }

    public <R> R map(final Function<V, R> successFn,
                     final Function<E, R> failureFn) {
        return this.isSuccess ? successFn.apply(this.value) //
                : failureFn.apply(this.throwable);
    }

    public void ifSuccess(final Consumer<? super V> action) {
        if (!this.isSuccess) {
            return;
        }

        action.accept(this.value);
    }

    public void ifFailure(final Consumer<? super E> action) {
        if (this.isSuccess) {
            return;
        }

        action.accept(this.throwable);
    }

    public void handle(final Consumer<? super V> succcessAction,
                       final Consumer<? super E> failureAction) {
        if (this.isSuccess) {
            succcessAction.accept(this.value);
            return;
        }

        failureAction.accept(this.throwable);
    }


    public V orElse(final V other) {
        return this.isSuccess ? this.value
                : other;
    }

    public V orElseGet(final Supplier<? extends V> otherSupplier) {
        return this.isSuccess ? this.value
                : otherSupplier.get();
    }

    @SuppressWarnings("unchecked")
    private <T extends Throwable> void sneakyThrow(final Throwable e) throws T {
        throw (T) e;
    }

    public V orElseThrow() {
        if (!this.isSuccess) {
            sneakyThrow(this.throwable);
            return null;
        }

        return this.value;
    }

    public V value() {
        return value;
    }

    public E throwable() {
        return throwable;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        final var that = (Result) obj;
        return Objects.equals(this.value, that.value) &&
                Objects.equals(this.throwable, that.throwable) &&
                this.isSuccess == that.isSuccess;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, throwable, isSuccess);
    }

    @Override
    public String toString() {
        return "Result[" +
                "value=" + value + ", " +
                "throwable=" + throwable + ", " +
                "isSuccess=" + isSuccess + ']';
    }

}
