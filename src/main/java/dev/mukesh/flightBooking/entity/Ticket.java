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
    @JoinColumn(name = "booking_id", nullable = false, referencedColumnName = "bookingId")
    private Booking booking;


    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false, referencedColumnName = "passengerId")
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false, referencedColumnName = "flightId")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false, referencedColumnName = "seatId")
    private Seat seat;


    private String seatNumber;


    @Column(nullable = false)
    private TicketStatus status;




}
