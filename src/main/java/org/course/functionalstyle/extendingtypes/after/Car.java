package org.course.functionalstyle.extendingtypes.after;

public final class Car implements Vehicle {
    private final String brand;

    public Car(final String brand) {
        this.brand = brand;
    }

    @Override
    public String getBrand() {
        return brand;
    }
}

