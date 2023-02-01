package org.reusable.maybe;

import org.reusable.maybe.visitors.MaybeVisitor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class Nothing<T> extends Maybe<T> {
    @Override
    public <R> R accept(final MaybeVisitor<T, R> visitor) {
        return visitor.visit(this);
    }

    private Nothing() {
    }

    public static Nothing<?> instance = new Nothing<>();
    private static final Lock lock = new ReentrantLock();
    @SuppressWarnings("unchecked")
    public static <T> Nothing<T> get() {
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new Nothing<T>();
                }
            } finally {
                lock.unlock();
            }
        }
        return (Nothing<T>) instance;
    }

    @Override
    public boolean equals(final Object o) {
        return this == o || o != null && getClass() == o.getClass();
    }
}
