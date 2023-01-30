package org.course.composability.dependencyinjection.functionalfactories.before;

public final class Options {
    private final boolean isVerbose;
    private final int maxLinesOutput;
    private final String outputFileName;

    public Options(boolean isVerbose, int maxLinesOutput, String outputFileName) {
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
