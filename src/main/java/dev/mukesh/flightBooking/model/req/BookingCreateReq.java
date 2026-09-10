package dev.mukesh.flightBooking.model.req;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class BookingCreateReq {


    @NotNull(message = "Flight ID cannot be null")
    private Integer flightId;

    @NotNull(message = "Passengers list cannot be null")
    private List<PassengerSeatReq> passengerSeatMap;

    @Data
    public static class PassengerSeatReq {

        @NotNull(message = "Passenger ID cannot be null")
        private Integer passengerId;

        private Integer seatId;
    }
}
