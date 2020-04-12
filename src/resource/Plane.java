package resource;

import java.util.Objects;

/**
 * immutable object
 * a plane containing number, type, seats, age
 */
public class Plane implements Resource {
    private final String number;
    private final String strType;
    private final int intSeats;
    private final double age;

    public Plane(String number, String strType, int intSeats, double age) {
        this.number = number;
        this.strType = strType;
        this.intSeats = intSeats;
        this.age = age;
    }

    public String getNumber() {
        return this.number;
    }

    public String getStrType() {
        return this.strType;
    }

    public int getIntSeats() {
        return this.intSeats;
    }

    public double getAge() {
        return this.age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Plane)) {
            return false;
        }
        Plane plane = (Plane) o;
        return Objects.equals(number, plane.number) && Objects.equals(strType, plane.strType)
                && intSeats == plane.intSeats && age == plane.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, strType, intSeats, age);
    }

    @Override
    public String toString() {
        return "{" + " number='" + getNumber() + "'" + ", strType='" + getStrType() + "'" + ", intSeats='"
                + getIntSeats() + "'" + ", age='" + getAge() + "'" + "}";
    }

}