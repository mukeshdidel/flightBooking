package dev.mukesh.flightBooking.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Payment extends BaseEntity {

    @Id
    @SequenceGenerator(
            name = "payment_sequence",
            sequenceName = "payment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_sequence")
    private Integer paymentId;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false, referencedColumnName = "booking_id")
    private Booking booking;


    @Column(nullable = false)
    private double amount;


    @Column(nullable = false)
    private PaymentMethod paymentMethod;


    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @Column(nullable = false)
    private TransactionStatus status;


    @Column(nullable = false)
    private String gatewayResp;

}
