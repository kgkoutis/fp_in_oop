package org.course.composability.loanpattern.after;

public class StopWatch implements Resource {

    private final org.apache.commons.lang3.time.StopWatch stopWatch = new org.apache.commons.lang3.time.StopWatch();
    public StopWatch() {
        System.out.println("Resource created");
        stopWatch.start();
    }

    public String doSomething(long waitTimeInMs) throws InterruptedException {
        System.out.println("Resource is doing something");
        Thread.sleep(waitTimeInMs);
        return "Resource is done";
    }

    public String doSomethingElse(long waitTimeInMs) throws InterruptedException {
        System.out.println("Resource is doing something else");
        Thread.sleep(waitTimeInMs);
        return "Resource is done";
    }

    @Override
    public void close() throws Exception {
        System.out.println("Resource is closed");
        stopWatch.stop();
        long g = stopWatch.getTime();
        System.out.println("Resource was used for " + g + "ms");
    }
}