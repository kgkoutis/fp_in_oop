package org.course.functionalstyle.extendingtypes.before;

// Decorator pattern
public final class LeasableVehicle implements Vehicle {
    private final Vehicle vehicle;

    public LeasableVehicle(final Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Vehicle asVehicle() {
        return vehicle;
    }


    @Override
    public String getBrand() {
        return vehicle.getBrand();
    }

    public String lease(final int days) {
        return "Leasing vehicle of brand " + vehicle.getBrand() + " for " + days + " days";
    }
}
