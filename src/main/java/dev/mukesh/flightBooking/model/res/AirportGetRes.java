package dev.mukesh.flightBooking.model.res;


import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirportGetRes {

    private String airportCode;
    private String name;
    private String city;
    private String country;

}
