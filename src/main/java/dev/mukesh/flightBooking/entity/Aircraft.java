package dev.mukesh.flightBooking.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class Aircraft extends BaseEntity {

    @Id
    @SequenceGenerator(
            name = "aircraft_sequence",
            sequenceName = "aircraft_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aircraft_sequence")
    private Integer AircraftId;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "airline_code", nullable = false, referencedColumnName = "airlineCode")
    private Airline airline;

    @OneToMany(mappedBy = "aircraft")
    private List<Flight> flights;

    @OneToMany(mappedBy = "aircraft")
    private  List<Seat> seats;


}
