package dev.mukesh.flightBooking.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Booking  extends BaseEntity {

    @Id
    @SequenceGenerator(
            name = "booking_sequence",
            sequenceName = "booking_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_sequence")
    private Integer BookingId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "userId")
    private User user;

    @Column(nullable = false)
    private LocalDateTime bookingDate;

    @Column(nullable = false)
    private double totalFare;

    @Column(nullable = false)
    private BookingStatus status;

    @Column(nullable = false)
    private String PNR;

    @OneToMany(mappedBy = "booking")
    private List<Ticket> tickets;


    @OneToMany(mappedBy = "booking")
    private List<Payment> payments;



}
