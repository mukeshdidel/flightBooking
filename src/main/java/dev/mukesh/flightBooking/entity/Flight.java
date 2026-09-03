package dev.mukesh.flightBooking.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Flight extends BaseEntity {

    @Id
    @SequenceGenerator(
            name = "flight_sequence",
            sequenceName = "flight_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_sequence")
    private Integer flightId;

    private String flightNum;

    @ManyToOne
    @JoinColumn(name = "airline_code", nullable = false, referencedColumnName = "airline_code")
    private Airline airline;

    @ManyToOne
    @JoinColumn(name = "source_airport", nullable = false, referencedColumnName = "airport_code")
    private Airport sourceAirport;

    @ManyToOne
    @JoinColumn(name = "dest_airport", nullable = false, referencedColumnName = "airport_code")
    private Airport destAirport;

    @ManyToOne
    @JoinColumn(name = "aircraft_id", nullable = false, referencedColumnName = "aircraft_id")
    private Aircraft aircraft;


    @Column(nullable = false)
    private LocalDateTime departureTime;

    @Column(nullable = false)
    private LocalDateTime arrivalTime;

    @Column(nullable = false)
    private FlightStatus status;


    @OneToMany(mappedBy = "flight")
    private List<Ticket> tickets;


}
