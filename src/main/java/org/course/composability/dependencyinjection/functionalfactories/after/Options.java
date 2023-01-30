package org.course.composability.dependencyinjection.functionalfactories.after;

public final class Options {
    private final boolean isVerbose;
    private final int maxLinesOutput;
    private final String outputFileName;

    public Options(final boolean isVerbose,
                   final int maxLinesOutput,
                   final String outputFileName) {
        this.isVerbose = isVerbose;
        this.maxLinesOutput = maxLinesOutput;
        this.outputFileName = outputFileName;
    }

    @Override
    public String toString() {
        return "Options{" +
                "isVerbose=" + isVerbose +
                ", maxLinesOutput=" + maxLinesOutput +
                ", outputFileName='" + outputFileName + '\'' +
                '}';
    }
}
