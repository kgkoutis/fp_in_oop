package org.course.composability.dependencyinjection.functionalfactories.before;

public final class Options {
    private final LoggerLevel loggerLevel;
    private final int maxLinesOutput;
    private final String outputFileName;

    public Options(final LoggerLevel loggerLevel,
                   final int maxLinesOutput,
                   final String outputFileName) {
        this.loggerLevel = loggerLevel;
        this.maxLinesOutput = maxLinesOutput;
        this.outputFileName = outputFileName;
    }

    @Override
    public String toString() {
        return "Options{" +
                "loggerLevel=" + loggerLevel +
                ", maxLinesOutput=" + maxLinesOutput +
                ", outputFileName='" + outputFileName + '\'' +
                '}';
    }
}
