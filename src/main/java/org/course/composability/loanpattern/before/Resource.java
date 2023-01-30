package org.course.composability.loanpattern.before;

public interface Resource extends AutoCloseable {
    String doSomething(long waitTimeInMs) throws InterruptedException;

    String doSomethingElse(long waitTimeInMs) throws InterruptedException;
}
