package ma.ceg.airport.repository;

import ma.ceg.airport.Flight;
import ma.ceg.airport.Passenger;

import java.util.Optional;
import java.util.Set;

public interface FlightRepository {

    boolean addPassenger(Flight flight, Passenger passenger);
    Optional<Passenger> getPassengerByNameAndByFlightName(String flightName, String passengerName);

}
