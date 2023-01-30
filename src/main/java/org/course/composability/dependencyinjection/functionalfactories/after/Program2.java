package org.course.composability.dependencyinjection.functionalfactories.after;

// Why would that class be not testable? Think.
public final class Program2 {
    private final Options options;

    public Program2() {
        this.options = new Options(true, 10, "output.txt");
    }

    public void run() {
        System.out.println("Program running with options = " + options);
        // do something with options
        //....
    }
}
