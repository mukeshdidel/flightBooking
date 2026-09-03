package dev.mukesh.flightBooking.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Ticket extends BaseEntity {

    @Id
    @SequenceGenerator(
            name = "ticket_sequence",
            sequenceName = "ticket_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_sequence")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false, referencedColumnName = "booking_id")
    private Booking booking;


    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false, referencedColumnName = "passenger_id")
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false, referencedColumnName = "flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false, referencedColumnName = "seat_id")
    private Seat seat;


    private String seatNumber;


    @Column(nullable = false)
    private TicketStatus status;




}
