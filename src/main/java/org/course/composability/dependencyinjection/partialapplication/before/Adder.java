package org.course.composability.dependencyinjection.partialapplication.before;

final public class Adder {

    public FixedAdder addFirst(int i) {
        // validation.... other stuff..... etc
        return new FixedAdder(i);
    }
}

