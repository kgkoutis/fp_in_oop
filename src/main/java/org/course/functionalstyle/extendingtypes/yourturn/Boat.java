package org.course.functionalstyle.extendingtypes.yourturn;

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
