package org.reusable;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final public class Unit {
    private static Unit instance;
    private static final Lock lock = new ReentrantLock();

    private Unit() {}

    public static Unit get() {
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    //noinspection InstantiationOfUtilityClass
                    instance = new Unit();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }
}