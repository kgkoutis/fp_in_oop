package org.course.functionalstyle.extendingtypes.before;

/**
 * Functional approach: separate data from behavior. Which usually means: use a static method to "extend" the type.
 * Pros: flexibility, you can use any static method you want as long as you have access to the desired state (:getSpeedInMetersPerSecond)
 * Cons: the method is static so a) it's logic cannot be inherited/reused into subclasses, if any b) you have to remember which is the class that can extend your type
 */

public class Main {
    public static void main(String[] args) {
        Wind w = new Wind(1);
        printSpeed(w);
        printSpeed2(w);
        printSpeed3(w);
    }

    private static void printSpeed(Wind wind) {
        needWind(wind);
        double kmsPerhr = WindHelper.getSpeedInKilometersPerHour(wind); // don't forget this!
        System.out.println("Speed in kilometers Per Hour: " + kmsPerhr);
    }

    private static void printSpeed2(Wind wind) {
        needWind(wind);
        double kmsPerhr = WindHelper.getSpeedInKilometersPerHour(wind); // don't forget this!
        System.out.println("Speed in kilometers Per Hour: " + kmsPerhr);
    }

    private static void printSpeed3(Wind wind) {
        needWind(wind);
        double kmsPerhr = WindHelper.getSpeedInKilometersPerHour(wind); // don't forget this!
        System.out.println("Speed in kilometers Per Hour: " + kmsPerhr);
    }


    private static void needWind(Wind w) {
        //.... logic with wind...//
        /* no operation */
    }
}

