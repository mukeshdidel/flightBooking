package dev.mukesh.flightBooking.controller;


import dev.mukesh.flightBooking.model.req.PassengerCreateReq;
import dev.mukesh.flightBooking.model.res.PassengerDeleteRes;
import dev.mukesh.flightBooking.model.res.PassengerRes;
import dev.mukesh.flightBooking.service.PassengerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(
        origins = {
                "http://localhost:4200",
        }
)
public class PassengerController {

    private final PassengerService passengerService;

    @PostMapping("/passengers")
    public ResponseEntity<List<PassengerRes>> createPassenger(@RequestBody List<PassengerCreateReq> passengerCreateReqBody, @RequestHeader("user_id") Integer userId) {

        List<PassengerRes> passenger = passengerService.createPassenger(passengerCreateReqBody, userId);
        return new ResponseEntity<>(passenger, HttpStatus.CREATED);
    }

    @GetMapping("/passengers")
    public ResponseEntity<List<PassengerRes>> getPassenger(@RequestHeader("user_id") Integer userId) {
        List<PassengerRes> passengers = passengerService.getPassenger(userId);
        return new ResponseEntity<>(passengers, HttpStatus.OK);
    }

    @DeleteMapping("/passengers/{passengerId}")
    public ResponseEntity<PassengerDeleteRes> deletePassenger(@PathVariable Integer passengerId, @RequestHeader("user_id") Integer userId) {
        PassengerDeleteRes res = passengerService.deletePassenger(passengerId, userId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
