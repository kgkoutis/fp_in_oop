package org.course.composability.dependencyinjection.partialapplication.before;

public final class Adder {

    public FixedAdder addFirst(final int i) {
        // validation.... other stuff..... etc
        return new FixedAdder(i);
    }
}

