package dev.mukesh.flightBooking.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Entity
@Table(name = "flight_seat_map",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"flight_id", "seat_id"})
    })
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightSeatMap {
    @Id
    @SequenceGenerator(
            name = "flight_seat_map_sequence",
            sequenceName = "flight_seat_map_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_seat_map_sequence")
    @Column(name = "flight_seat_id")
    private Integer flightSeatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

    @OneToOne(mappedBy = "flightSeatMap")
    private Ticket ticket;

}