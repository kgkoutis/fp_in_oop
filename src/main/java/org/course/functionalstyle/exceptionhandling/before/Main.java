package org.course.functionalstyle.exceptionhandling.before;

import java.net.URI;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Here we have a method that makes a call to (:scaryHttpCall) that can throw two different exceptions.
 * This means that the upstream code has to handle both of them.
 *
 * Before we saw the Result class that can be used to return a value or an exception, and we said that Result is just a
 * special case of the Either DU.
 *
 * but Either looks like this:
 *      data Either a b = Left a | Right b
 *
 * As you can see is either (Left a) or (Right b) value, where a and b can be of any type. That is different from Result which demands one of them to represent an exception.
 * so we can have:
 *      let normalProduct = Right 10 :: Either String Int
 *      let giftItem = Left "no price" :: Either String Int
 *
 * How does Either look in Java?
 *
 * (NOTE) Traditionally, the Left value is used to hold an "error" or "exception" value and the Right value is used to hold a correct value.
 * (NOTE2) In this exercise we will use Either to express an Exception for the Left value but what we said above about its generality are still valid.
 * */
public class Main {
    public static void main(String[] args) {
        URI urlThatMightFail = URI.create("http://doesntmatter.com");

        for (int i = 0; i < 40; i++) {
            double res;
            try {
                res = scaryHttpCall(urlThatMightFail);
                System.out.println("Happy path: " + res);
            } catch (DivisionByZeroException | ApiCrashedException e) {
                System.out.println("Unhappy path: " + e.getMessage());
            }
        }
    }

    private static double scaryHttpCall(URI urlThatMightFail) throws DivisionByZeroException, ApiCrashedException {
        double x = getPositiveNumberFromInternet(urlThatMightFail);
        return scaryDivision(x);
    }

    private static double scaryDivision(double x) throws DivisionByZeroException {
        if (x == 0.0)
            throw new DivisionByZeroException("Division by zero");
        else
            return 20 / x;
    }

    private static double getPositiveNumberFromInternet(URI url) throws ApiCrashedException {
        // call api to get a number...
        int min = 0;
        int max = 10;
        int rnd = ThreadLocalRandom.current().nextInt(min, max + 1);

        // api is broken when it is about to return 4
        if (rnd == 4)
            throw new ApiCrashedException("API crashes at 4");

        return Math.abs(rnd);
    }
}