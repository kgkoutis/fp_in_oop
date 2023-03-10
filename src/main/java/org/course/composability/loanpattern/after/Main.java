package org.course.composability.loanpattern.after;

import org.reusable.Unit;

import java.util.function.Function;

/**
 * Notice how the resource management code is handled only in one place: in the executeWithResourceManagement method.
 * Everything else is retrofitted to be a callback that is passed not only its original dependencies (waitTimeInMs)
 * but also the CustomFileReader that is being brought into scope by "somewhere else".
 * <p>
 * This pattern of abstracting away the resource management code into a separate method is called the Loan Pattern.
 * The callbacks are "borrowing" the resource from the resource management code.
 * <p>
 * Composing the resource management code with business logic code leads to the same program behavior as before.
 */

public class Main {
    public static void main(final String[] args) {
        executeWithResourceManagement(Main::readFile);
    }

    // business logic code
    // has nothing to do with try-with-resources
    private static Unit readFile(final CustomFileReader fileReader) {
        fileReader.useResource();
        // more business logic code...
        businessLogic1();
        businessLogic2();
        return Unit.get();
    }

    private static <R> void executeWithResourceManagement(final Function<CustomFileReader, R> callback) {
        // try-with-resources
        try (final CustomFileReader fileReader = new CustomFileReader()) {
            callback.apply(fileReader);
        } catch (final Exception e) {
            System.out.println("Exception caught" + e);
        }
    }

    private static void businessLogic2() {
    }

    private static void businessLogic1() {
    }
}
