package dev.mukesh.flightBooking.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Flight {

    @Id
    @SequenceGenerator(
            name = "flight_sequence",
            sequenceName = "flight_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_sequence")
    private Integer flightID;

    private String flightNum;

    @ManyToOne
    @JoinColumn(name = "airline_code")
    private Airline airline;

    @ManyToOne
    @JoinColumn(name = "source_airport")
    private Airport sourceAirport;

    @ManyToOne
    @JoinColumn(name = "dest_airport")
    private Airport destAirport;

    @ManyToOne
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private FlightStatus status;


    @OneToMany(mappedBy = "flight")
    private List<Ticket> tickets;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
