package org.course.functionalstyle.extendingtypes.after;

/**
 * OOP approach: extend the type with the Decorator/Adapter/Proxy pattern.
 * Pros: a) the method can be inherited for inherited subclasses and b) you don't have to remember a class that extends your type, just hit dot and see
 * what methods are available from intellisense.
 * Cons: the logic is "baked in" the type.
 */
public class Main {
    public static void main(final String[] args) {
        final WindExtender w = new WindExtender(new Wind(1));
        printSpeed(w);
        printSpeed2(w);
        printSpeed3(w);
    }

    private static void printSpeed(final WindExtender w) {
        needWind(w);
        final double kmsPerhr = w.getSpeedInKilometersPerHour(); // don't forget this!
        System.out.println("Speed in kilometers Per Hour: " + kmsPerhr);
    }

    private static void printSpeed2(final WindExtender w) {
        needWind(w);
        final double kmsPerhr = w.getSpeedInKilometersPerHour(); // don't forget this!
        System.out.println("Speed in kilometers Per Hour: " + kmsPerhr);
    }

    private static void printSpeed3(final WindExtender w) {
        needWind(w);
        final double kmsPerhr = w.getSpeedInKilometersPerHour(); // don't forget this!
        System.out.println("Speed in kilometers Per Hour: " + kmsPerhr);
    }

    private static void needWind(final Wind w) {
        //.... logic with wind...//
        /* no operation */
    }
}
