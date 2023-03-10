package org.course.functionalstyle.extendingtypes.before;

public final class Motorcycle implements Vehicle {
    private final String brand;

    public Motorcycle(final String brand) {
        this.brand = brand;
    }

    @Override
    public String getBrand() {
        return brand;
    }
}
