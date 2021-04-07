package ma.ceg.airport.service;

import ma.ceg.airport.repository.FlightRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import ma.ceg.airport.AlreadyExistException;
import ma.ceg.airport.Flight;
import ma.ceg.airport.Passenger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {

    @InjectMocks
    FlightService flightService;

    @Mock
    FlightRepository flightRepository;

    @Test
    @DisplayName("Should add a usual Passenger to economy flight")
    void shouldAddPassengerToFlight() {
        //Arrange
        Flight flight = new Flight("EE7825", "economy");
        Passenger usualPassenger = new Passenger("ayoub", false);


        //Act
        when(flightRepository.getPassengerByNameAndByFlightName(flight.getName(), usualPassenger.getName())).thenReturn(Optional.ofNullable(null));

        when(flightRepository.addPassenger(flight, usualPassenger)).thenReturn(true);

        boolean isAdded  = flightService.addPassenger(flight, usualPassenger);

        //Assert
        assertThat(isAdded).isTrue();
        verify(flightRepository,times(1)).getPassengerByNameAndByFlightName(flight.getName(), usualPassenger.getName());
        verify(flightRepository,times(1)).addPassenger(flight,usualPassenger);
    }

    @Test
    @DisplayName("Should add vip passenger to  economy flight")
    void should_add_vip_passenger_to_economy_flight() {
        //Arrange
        Flight flight = new Flight("EE7825", "economy");
        Passenger vipPassenger = new Passenger("ayoub", true);

        //ACT
        when(flightRepository.getPassengerByNameAndByFlightName(flight.getName(), vipPassenger.getName())).thenReturn(Optional.ofNullable(null));
        when(flightRepository.addPassenger(flight, vipPassenger)).thenReturn(true);

        boolean isAdded  = flightService.addPassenger(flight, vipPassenger);

        //ASSERT
        assertThat(isAdded).isTrue();
        verify(flightRepository,times(1)).getPassengerByNameAndByFlightName(flight.getName(), vipPassenger.getName());
        verify(flightRepository,times(1)).addPassenger(flight,vipPassenger);
    }

    @Test
    @DisplayName("Should not add  passenger to a non existant flight")
    void should_not_add_usual_passenger_to_non_existant_flight() {
        Flight flight = new Flight("EE7825", "non economy");
        Passenger usualPassenger = new Passenger("ayoub", false);

        when(flightRepository.getPassengerByNameAndByFlightName(flight.getName(), usualPassenger.getName())).thenThrow(RuntimeException.class);

        Assertions.assertThrows(RuntimeException.class,() -> flightService.addPassenger(flight , usualPassenger));
        verify(flightRepository,times(1)).getPassengerByNameAndByFlightName(flight.getName(), usualPassenger.getName());
    }

    @Test
    @DisplayName("Should throw already exist exception")
    void should_throw_already_exixts_exception() {
        //Arrange
        Flight flight = new Flight("EE7825", "economy");
        Passenger usualPassenger = new Passenger("ayoub", false);

        //ACT
        when(flightRepository.getPassengerByNameAndByFlightName(flight.getName(), usualPassenger.getName())).thenReturn(Optional.of(usualPassenger));

        //ASSERT
        Assertions.assertThrows(AlreadyExistException.class,() ->flightService.addPassenger(flight , usualPassenger));
        verify(flightRepository,times(1)).getPassengerByNameAndByFlightName(flight.getName(), usualPassenger.getName());
    }

    @Test
    @DisplayName("Should not add usual passenger to a vip flight")
    void should_not_add_usual_passenger_to_non_economy_flight() {
        Flight flight = new Flight("EE7825", "non economy");
        Passenger usualPassenger = new Passenger("ayoub", false);


        when(flightRepository.getPassengerByNameAndByFlightName(flight.getName(), usualPassenger.getName())).thenReturn(Optional.ofNullable(null));
        boolean isAdded  = flightService.addPassenger(flight, usualPassenger);

        assertThat(isAdded).isFalse();
        verify(flightRepository , times(1) ).getPassengerByNameAndByFlightName(flight.getName(), usualPassenger.getName());

    }

}
