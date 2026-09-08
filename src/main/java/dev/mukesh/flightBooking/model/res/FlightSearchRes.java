package dev.mukesh.flightBooking.model.res;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FlightSearchRes {

    private Integer flightId;
    private String flightNum;
    private String airlineCode;
    private String airlineName;
    private AirportRes sourceAirport;
    private AirportRes destAirport;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Long durationMinutes;
    private Double price;


    @Builder
    @Data
    public static class AirportRes {
        private String airportCode;
        private String name;
        private String city;
        private String country;
    }


}
