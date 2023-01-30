package org.course.composability.dependencyinjection.functionalfactories.before;

public class Program {
    private final Options options;

    public Program(LoggerOptionsFactory loggerOptionsFactory) // what should we put here instead of a factory?
    {
        options = loggerOptionsFactory.create();
    }

    public void run() {
        System.out.println("Program running with options = " + options);
        // do something with options
        //....
    }
}
