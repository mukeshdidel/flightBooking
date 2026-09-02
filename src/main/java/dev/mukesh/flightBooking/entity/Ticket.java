package dev.mukesh.flightBooking.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Ticket {

    @Id
    @SequenceGenerator(
            name = "ticket_sequence",
            sequenceName = "ticket_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_sequence")
    private Integer id;

    @ManyToOne
    @JoinTable(name = "booking_id")
    private Booking booking;


    @ManyToOne
    @JoinTable(name = "passenger_id")
    private Passenger passenger;

    @ManyToOne
    @JoinTable(name = "flight_id")
    private Flight flight;

    // todo; add seat mapping

    private String seatNumber;

    private TicketStatus status;




}
