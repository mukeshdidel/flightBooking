package dev.mukesh.flightBooking.entity;


import dev.mukesh.flightBooking.enums.TicketStatus;
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

    @OneToOne
    @JoinColumn(name = "flight_seat_id", nullable = false, referencedColumnName = "flight_seat_id")
    private FlightSeatMap flightSeatMap;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status;




}
