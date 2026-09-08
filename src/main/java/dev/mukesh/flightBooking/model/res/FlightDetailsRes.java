package dev.mukesh.flightBooking.model.res;

import dev.mukesh.flightBooking.enums.SeatLocation;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class FlightDetailsRes {
    private Integer flightId;
    private String flightNum;
    private String status;
    private Double price;
    private Integer availableSeats;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    private AirlineRes airline;
    private AirportRes sourceAirport;
    private AirportRes destAirport;
    private AircraftRes aircraft;
    private List<SeatRes> seats;

    @Data
    @Builder
    public static class AirlineRes {
        private String airlineCode;
        private String name;
    }

    @Data
    @Builder
    public static class AirportRes {
        private String airportCode;
        private String name;
        private String city;
    }

    @Data
    @Builder
    public static class AircraftRes {
        private Integer aircraftId;
        private String model;
        private String manufacturer;
    }

    @Data
    @Builder
    public static class SeatRes {
        private Integer seatId;
        private String seatNumber;
        private SeatLocation location;
        private Boolean isAvailable;
    }
}
