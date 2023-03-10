package org.course.composability.loanpattern.before;

/**
 * Main performs an action which is wrapped in a try-with-resources block.
 * It is advisable when you are using resources from the Operating System to use try-with-resources
 * and dispose them as soon as you don't need them anymore, and NOT rely on the garbage collector to do the job.
 * Some of these resources are scarce and you don't want to run out of them. So it is always a good idea to close
 * them as soon as you are done with them.
 * <p>
 * But we may just forget to close them, especially if we are using a lot of them (here we only use one).
 * Or more likely, we don't know that we have to do that because we are not experienced enough (i.e. we are inexperienced).
 * <p>
 * How can we separate the resource management code from the business logic code?
 * Wouldn't it be nice to pass the business logic code as a callback to the resource management code and let it manage
 * the resource for us? How would that work?
 */
public class Main {
    public static void main(final String[] args) {
        // try-with-resources
        try (final ResourceHolder resourceHolder = new CustomFileReader()) {
            resourceHolder.useResource();
            // more business logic code...
            businessLogic1();
            businessLogic2();
        } catch (final Exception e) {
            System.out.println("Exception caught" + e);
        }
    }

    private static void businessLogic2() {
    }

    private static void businessLogic1() {
    }
}