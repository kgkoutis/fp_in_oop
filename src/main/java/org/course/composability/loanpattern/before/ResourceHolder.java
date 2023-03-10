package org.course.composability.loanpattern.before;

public interface ResourceHolder extends AutoCloseable {
    String delayMs(long waitTimeInMs);
}
