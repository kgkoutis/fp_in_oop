package org.course.functionalstyle.extendingtypes.before;

/**
 * OOP approach: extend the type with the Decorator pattern.
 * Pros: a) avoid inheritance and accept flexibly the type to decorate and
 *       b) you don't have to remember a helper class that extends your type, just hit dot and see
 *          what methods are available from intellisense.
 * Cons: the logic is "baked in" the type.
 *
 * Question: What is the FP approach to extend a type? The answer is super simple.
 */
public class Main {
    public static void main(final String[] args) {
        final LeasableVehicle car = new LeasableVehicle(new Car("Kia"));
        int days = 10;
        leaseVehicle(car, days);

        final LeasableVehicle motorCycle = new LeasableVehicle(new Motorcycle("Suzuki"));
        days = 20;
        leaseVehicle(motorCycle, days);

        final LeasableVehicle boat = new LeasableVehicle(new Boat("Love Boat"));
        days = 30;
        leaseVehicle(boat, days);
    }

    private static void leaseVehicle(final LeasableVehicle leasableVehicle,
                                     final int days) {
        final String message = leasableVehicle.lease(days);
        System.out.println(message);
    }
}
