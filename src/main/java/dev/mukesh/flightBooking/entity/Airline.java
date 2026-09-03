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
public class Airline  extends BaseEntity {

    @Id
    private String airlineCode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String countryCode;

    @OneToMany(mappedBy = "airline")
    private List<Flight> flights;

    @OneToMany(mappedBy = "airline")
    private  List<Aircraft> aircrafts;



}
