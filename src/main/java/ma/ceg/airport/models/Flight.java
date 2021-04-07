package ma.ceg.airport;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import ma.ceg.airport.Passenger;

public class Flight {

    private String name;
    private String type;
    private Set<Passenger> passengers = new HashSet<>();

    public Flight(String type) {
        this.type = type;
    }

    public Flight() { }

    public Flight(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(name, flight.name) &&
                Objects.equals(type, flight.type) &&
                Objects.equals(passengers, flight.passengers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, passengers);
    }
}
