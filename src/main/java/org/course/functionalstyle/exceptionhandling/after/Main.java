package org.course.functionalstyle.exceptionhandling.after;

import org.reusable.either.Either;

import java.net.URI;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Here we introduced a wrapper class for the exception type and message. That way we can pattern match on the type of the exception on the upstream side.
 * The methods with checked exceptions now return Either<ExceptionTypeAndMessage, T> and didn't need to throw exceptions.
 *
 * By manipulating the Either type with map, bind and biBind we can reach the Either type to the final handler for the final processing.
 * This all makes the code more honest and easier to reason about.
 * */
public class Main {
    public static void main(String[] args) {
        URI urlThatMightFail = URI.create("https://doesntmatter.com");

        for (int i = 0; i < 40; i++) {
            Either<ExceptionTypeAndMessage, Double> either = scaryHttpCall(urlThatMightFail);

            either.handle(
                    (ExceptionTypeAndMessage exceptionTypeAndMessage) -> {
                        Class<?> exceptionType = (Class<?>) exceptionTypeAndMessage.getExceptionType();

                        if (exceptionType == DivisionByZeroException.class) {
                            String s = exceptionTypeAndMessage.getMessage();
                            System.out.println("Unhappy path: " + s);
                        }
                        else if (exceptionType == ApiCrashedException.class) {
                            String s = exceptionTypeAndMessage.getMessage();
                            System.out.println("Unhappy path: " + s);
                        }
                        else {
                            System.out.println("Unknown exception type");
                        }
                    },
                    (Double d) -> System.out.println("Happy path: " + d)
            );
        }
    }

    private static Either<ExceptionTypeAndMessage, Double> scaryHttpCall(URI urlThatMightFail) {
        Either<ExceptionTypeAndMessage, Integer> x = getPositiveNumberFromInternet(urlThatMightFail);

        return x.biBind(
                Either::left,
                (Integer r) -> {
                    Double d = Double.valueOf(r);
                    return scaryDivision(d);
                });
    }

    private static Either<ExceptionTypeAndMessage, Double> scaryDivision(Double x) {

        if (x == 0.0)
            return Either.left(ExceptionTypeAndMessage.of(DivisionByZeroException.class, "Division by zero"));
        else
            return Either.right(20 / x);
    }

    private static Either<ExceptionTypeAndMessage, Integer> getPositiveNumberFromInternet(URI url) {
        // call api to get a number...
        int min = 0;
        int max = 10;
        int rnd = ThreadLocalRandom.current().nextInt(min, max + 1);

        // api is broken when it is about to return 4.0
        if (rnd == 4)
            return Either.left(ExceptionTypeAndMessage.of(ApiCrashedException.class, "API crashes at 4"));

        return Either.right(Math.abs(rnd));
    }
}

