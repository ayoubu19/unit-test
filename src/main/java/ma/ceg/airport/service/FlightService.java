package ma.ceg.airport.service;


import ma.ceg.airport.AlreadyExistException;
import ma.ceg.airport.Flight;
import ma.ceg.airport.Passenger;
import ma.ceg.airport.repository.FlightRepository;

import java.util.Optional;

public class FlightService {

    private FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }


    public boolean addPassenger(Flight flight, Passenger passenger) {


        Optional<Passenger> optionalPassenger = flightRepository.getPassengerByNameAndByFlightName(flight.getName(), passenger.getName());

        if (optionalPassenger.isPresent()) {
            throw new AlreadyExistException("Passenger is already created :" + passenger.getName());
        }

        boolean isPassengerAllowed = !"economy".equals(flight.getType()) && !passenger.isVip();

        if (isPassengerAllowed) {
            return false;
        }

        return this.flightRepository.addPassenger(flight, passenger);

    }
}
