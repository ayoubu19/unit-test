package ma.ceg.airport;

import java.util.Objects;

public class Passenger {

    private String name;
    private boolean isVip;

    public Passenger(boolean isVip) {
        this.isVip = isVip;
    }

    public Passenger(String name, boolean isVip) {
        this.name = name;
        this.isVip = isVip;
    }

    public boolean isVip() {
        return isVip;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return isVip == passenger.isVip &&
                Objects.equals(name, passenger.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isVip);
    }
}
