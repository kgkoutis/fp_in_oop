package org.course.functionalstyle.extendingtypes.yourturn;

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

    // How do we extend functionality of the Vehicle classes with Decorator?
    private static void leaseVehicle(final Vehicle vehicle,
                                     final int days) {

    }
}
