package org.course.composability.loanpattern.after;

public interface ResourceHolder extends AutoCloseable {
    String delayMs(long waitTimeInMs);
}
