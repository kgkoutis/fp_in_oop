package org.course.composability.loanpattern.before;

public interface ResourceHolder extends AutoCloseable {
    void useResource();
}
