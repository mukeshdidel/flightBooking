package dev.mukesh.flightBooking.controller;


import dev.mukesh.flightBooking.model.res.AirportGetRes;
import dev.mukesh.flightBooking.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(
        origins = {
                "http://localhost:4200",
        }
)
public class AirportController {

    private final AirportService airportService;


    @GetMapping("/airports")
    public ResponseEntity<List<AirportGetRes>> getAllAirports() {
        List<AirportGetRes> airports = airportService.getAllAirports();
        return new ResponseEntity<>(airports, HttpStatus.OK);
    }


}
