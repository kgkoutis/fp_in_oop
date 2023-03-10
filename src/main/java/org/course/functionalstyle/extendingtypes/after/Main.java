package org.course.functionalstyle.extendingtypes.after;

/**
 * Functional approach: separate data from behavior. Which usually means: use a static method to "extend" the type.
 * Pros: flexibility, you can use any static method you want as long as you have access to the desired state (:getBrand). The logic is not "baked in" to a type.
 * Cons: the method is static and external to the type,
 *              a) so you have to remember which is the helper class that can extend your type, although, what you most of the time remember is the method name,
 *              b) subtypes may not know that they can use this method even if they know that they hail from the parent class,
 *              c) The method leaseVehicle no longer accepts a ready-made type (i.e: LeasableVehicle) so the calling code (i.e. :main) cannot make assumptions in advance
 *                 what kind of process will be performed on the vehicle.
 * <p>
 * How can we extend the type in a more OOP way, with and without using inheritance?
 */

public class Main {
    public static void main(final String[] args) {
        final Car car = new Car("Kia");
        int days = 10;
        leaseVehicle(car, days);

        final Motorcycle motorcycle = new Motorcycle("Suzuki");
        days = 20;
        leaseVehicle(motorcycle, days);

        final Boat boat = new Boat("Love Boat");
        days = 30;
        leaseVehicle(boat, days);
    }

    private static void leaseVehicle(final Vehicle vehicle,
                                     final int days) {
        final String message = VehicleUtils.lease(vehicle, days); // don't forget this!
        System.out.println(message);
    }
}