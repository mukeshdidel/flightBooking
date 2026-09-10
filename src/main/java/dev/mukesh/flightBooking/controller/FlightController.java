package dev.mukesh.flightBooking.controller;


import dev.mukesh.flightBooking.model.res.FlightDetailsRes;
import dev.mukesh.flightBooking.model.res.FlightSearchRes;
import dev.mukesh.flightBooking.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(
        origins = {
                "http://localhost:4200",
        }
)
public class FlightController {

    private final FlightService flightService;


    @GetMapping("/flights/search")
    public ResponseEntity<List<FlightSearchRes>> searchFlights(
            @RequestParam String source,
            @RequestParam String destination,
            @RequestParam LocalDate date
    ) {
        List<FlightSearchRes> flights = flightService.searchFlights(source, destination, date);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<FlightDetailsRes> getFlightDetails(@PathVariable("id") Integer id) {
        FlightDetailsRes flight = flightService.getFlightDetails(id);
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }


}
