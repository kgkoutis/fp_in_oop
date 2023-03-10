package org.course.composability.loanpattern.after;

import org.reusable.Unit;

public interface ResourceHolder extends AutoCloseable {
    Unit useResource();
}