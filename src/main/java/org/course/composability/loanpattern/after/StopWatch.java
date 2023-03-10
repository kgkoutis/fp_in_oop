package org.course.composability.loanpattern.after;

import static org.reusable.Threads.delay;

public final class StopWatch implements ResourceHolder {

    private final org.apache.commons.lang3.time.StopWatch stopWatch;

    public StopWatch() {
        System.out.println("ResourceHolder created");
        System.out.println("Acquiring resource");
        stopWatch = new org.apache.commons.lang3.time.StopWatch(); // simulate resource acquisition
        stopWatch.reset();
        stopWatch.start();
    }

    public String delayMs(final long waitTimeInMs) {
        System.out.println("ResourceHolder is doing something");
        delay(waitTimeInMs);
        return "ResourceHolder is done";
    }

    @Override
    public void close() {
        System.out.println("ResourceHolder is closed");
        stopWatch.stop();
        final long g = stopWatch.getTime();
        System.out.println("ResourceHolder was used for " + g + "ms");
        System.out.println("Releasing resource");
        stopWatch.reset(); // simulate resource release
    }
}