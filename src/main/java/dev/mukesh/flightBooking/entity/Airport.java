package dev.mukesh.flightBooking.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Airport extends BaseEntity {
    @Id
    private String airportCode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;


    @OneToMany(mappedBy = "source_airport")
    private List<Flight> sourceFlights;

    @OneToMany(mappedBy = "dest_airport")
    private List<Flight> destinationFlights;

}
