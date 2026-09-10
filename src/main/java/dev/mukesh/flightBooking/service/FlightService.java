package dev.mukesh.flightBooking.service;


import dev.mukesh.flightBooking.entity.Flight;
import dev.mukesh.flightBooking.entity.FlightSeatMap;
import dev.mukesh.flightBooking.exception.ResourceNotFoundException;
import dev.mukesh.flightBooking.model.res.FlightDetailsRes;
import dev.mukesh.flightBooking.model.res.FlightSearchRes;
import dev.mukesh.flightBooking.repo.FlightRepository;
import dev.mukesh.flightBooking.repo.FlightSeatMapRepository;
import dev.mukesh.flightBooking.repo.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {


    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;
    private final FlightSeatMapRepository flightSeatMapRepository;

    public List<FlightSearchRes> searchFlights(String source, String destination, LocalDate date) {

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
        List<FlightSeatMap> flightSeatMaps = flightSeatMapRepository.findByFlightId(flight.getFlightId());
        List<FlightDetailsRes.SeatRes> seatModels = flightSeatMaps.stream().map(map ->
                FlightDetailsRes.SeatRes.builder()
                        .seatId(map.getFlightSeatId())
                        .seatNumber(map.getSeat().getSeatNumber())
                        .location(map.getSeat().getSeatLocation())
                        .isAvailable(map.getIsAvailable())
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
