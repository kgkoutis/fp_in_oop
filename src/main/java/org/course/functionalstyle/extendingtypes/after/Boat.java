package org.course.functionalstyle.extendingtypes.after;

public final class Boat implements Vehicle {

    private final String brand;

    public Boat(final String brand) {
        this.brand = brand;
    }

    @Override
    public String getBrand() {
        return brand;
    }
}
