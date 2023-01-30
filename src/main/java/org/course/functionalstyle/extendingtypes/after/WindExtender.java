package org.course.functionalstyle.extendingtypes.after;

final public class WindExtender extends Wind {
    private final Wind wind;

    public WindExtender(Wind wind) {
        super(wind.getSpeedInMetersPerSecond());
        this.wind = wind;
    }

    public WindExtender(double speedInMetersPerSecond) {
        super(speedInMetersPerSecond);
        this.wind = new Wind(speedInMetersPerSecond);
    }

    public double getSpeedInMetersPerSecond()
    {
        return wind.getSpeedInMetersPerSecond();
    }

    public double getSpeedInKilometersPerHour() {
        return wind.getSpeedInMetersPerSecond() * 3.6;
    }
}
