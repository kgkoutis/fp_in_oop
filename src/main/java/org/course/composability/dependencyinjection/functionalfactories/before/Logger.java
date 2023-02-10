package org.course.composability.dependencyinjection.functionalfactories.before;

public final class Logger {
    private final Options options;

    public Logger(final LoggerOptionsFactory loggerOptionsFactory) // what should we put here instead of a factory?
    {
        options = loggerOptionsFactory.create();
    }

    public void log() {
        System.out.println("Logger logs with options = " + options);
        // do something with options
        //....
    }
}
