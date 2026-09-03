package dev.mukesh.flightBooking.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Seat extends BaseEntity {

    @Id
    @SequenceGenerator(
            name = "seat_sequence",
            sequenceName = "seat_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seat_sequence")
    private Integer seatId;


    @ManyToOne
    @JoinColumn(name = "aircraft_id", nullable = false, referencedColumnName = "aircraftId")
    private Aircraft aircraft;

    @Column(nullable = false)
    private String seatNumber;

    @Column(nullable = false)
    private SeatClass seatClass;

    @Column(nullable = false)
    private SeatLocation seatLocation;


    @OneToMany(mappedBy = "seat")
    private List<Ticket> tickets;

}
