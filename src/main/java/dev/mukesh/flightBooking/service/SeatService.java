package dev.mukesh.flightBooking.service;


import dev.mukesh.flightBooking.entity.Seat;
import dev.mukesh.flightBooking.repo.FlightSeatMapRepository;
import dev.mukesh.flightBooking.repo.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;
    private final FlightSeatMapRepository flightSeatMapRepository;


    public List<Seat> getAvailableSeatsByFlightId(Integer flightId) {
        return flightSeatMapRepository.findAvailableSeatsByFlightId(flightId);
    }

    public void lockSeat(Integer seatId, Integer flightId) {
        flightSeatMapRepository.markAsTakenBySeatIdAndFlightId(seatId, flightId);
    }
}
