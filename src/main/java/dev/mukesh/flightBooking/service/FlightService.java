package dev.mukesh.flightBooking.service;


import dev.mukesh.flightBooking.entity.Flight;
import dev.mukesh.flightBooking.entity.Seat;
import dev.mukesh.flightBooking.exception.ResourceNotFoundException;
import dev.mukesh.flightBooking.model.res.FlightDetailsRes;
import dev.mukesh.flightBooking.model.res.FlightSearchRes;
import dev.mukesh.flightBooking.repo.FlightRepository;
import dev.mukesh.flightBooking.repo.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FlightService {


    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;

    public List<FlightSearchRes> searchFlights(String source, String destination, String date) {

        List<Flight> flights = flightRepository.searchFlight(source, destination, date);

        return flights.stream().map(flight -> FlightSearchRes.builder()
                .flightId(flight.getFlightId())
                .flightNum(flight.getFlightNum())
                .airlineCode(flight.getAirline().getAirlineCode())
                .airlineName(flight.getAirline().getName())
                .sourceAirport(FlightSearchRes.AirportRes.builder()
                        .airportCode(flight.getSourceAirport().getAirportCode())
                        .name(flight.getSourceAirport().getName())
                        .city(flight.getSourceAirport().getCity())
                        .country(flight.getSourceAirport().getCountry())
                        .build())
                .destAirport(FlightSearchRes.AirportRes.builder()
                        .airportCode(flight.getDestAirport().getAirportCode())
                        .name(flight.getDestAirport().getName())
                        .city(flight.getDestAirport().getCity())
                        .country(flight.getDestAirport().getCountry())
                        .build())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .durationMinutes(java.time.Duration.between(flight.getDepartureTime(), flight.getArrivalTime()).toMinutes())
                .price(flight.getPrice())
                .build()).toList();

    }

    public FlightDetailsRes getFlightDetails(Integer id) {

        Flight flight = flightRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Flight not found"));

        List<Seat> aircraftSeats = seatRepository.findByAircraftId(flight.getAircraft().getAircraftId());

        // todo: mark as available or not available in seats

        List<FlightDetailsRes.SeatRes> seatModels = aircraftSeats.stream().map(seat ->
                FlightDetailsRes.SeatRes.builder()
                        .seatId(seat.getSeatId())
                        .seatNumber(seat.getSeatNumber())
                        .location(seat.getSeatLocation())
                        .isAvailable(true) // todo: hardcoded!,  check if seat is booked or not
                        .build()
        ).toList();

        return FlightDetailsRes.builder()
                .flightId(flight.getFlightId())
                .flightNum(flight.getFlightNum())
                .status(flight.getStatus().name())
                .price(flight.getPrice())
                .availableSeats(flight.getAvailableSeats())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .airline(FlightDetailsRes.AirlineRes.builder()
                        .airlineCode(flight.getAirline().getAirlineCode())
                        .name(flight.getAirline().getName())
                        .build())
                .sourceAirport(FlightDetailsRes.AirportRes.builder()
                        .airportCode(flight.getSourceAirport().getAirportCode())
                        .name(flight.getSourceAirport().getName())
                        .city(flight.getSourceAirport().getCity())
                        .build())
                .destAirport(FlightDetailsRes.AirportRes.builder()
                        .airportCode(flight.getDestAirport().getAirportCode())
                        .name(flight.getDestAirport().getName())
                        .city(flight.getDestAirport().getCity())
                        .build())
                .aircraft(FlightDetailsRes.AircraftRes.builder()
                        .aircraftId(flight.getAircraft().getAircraftId())
                        .model(flight.getAircraft().getModel())
                        .manufacturer(flight.getAircraft().getManufacturer())
                        .build())
                .seats(seatModels)
                .build();

    }
}
