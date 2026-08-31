package dev.mukesh.flightBooking.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Airline {

    @Id
    private String airlineCode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String countryCode;

}
