package dev.mukesh.flightBooking.service;


import dev.mukesh.flightBooking.entity.Airport;
import dev.mukesh.flightBooking.model.res.AirportGetRes;
import dev.mukesh.flightBooking.repo.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;


    public List<AirportGetRes> getAllAirports() {

        List<Airport> airports = airportRepository.findAll();

        return airports.stream()
                .map(airport -> AirportGetRes.builder()
                        .name(airport.getName())
                        .city(airport.getCity())
                        .country(airport.getCountry())
                        .airportCode(airport.getAirportCode())
                        .build())
                .toList();

    }
}
