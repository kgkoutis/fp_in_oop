package org.course.composability.dependencyinjection.functionalfactories.after;

import java.util.function.Supplier;

public class Program {
    private final Options options;

    public Program(Supplier<Options> loggerOptionsFactory) {
        options = loggerOptionsFactory.get();
    }

    public void run() {
        System.out.println("Program running with options = " + options);
        // do something with options
        //....
    }
}
