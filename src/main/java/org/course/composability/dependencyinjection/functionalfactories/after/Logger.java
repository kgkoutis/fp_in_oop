package org.course.composability.dependencyinjection.functionalfactories.after;

import java.util.function.Supplier;

public final class Logger {
    private final Options options;

    public Logger(final Supplier<Options> loggerOptionsFactory) {
        options = loggerOptionsFactory.get();
    }

    public void log() {
        System.out.println("Logger logs with options = " + options);
        // do something with options
        //....
    }
}
