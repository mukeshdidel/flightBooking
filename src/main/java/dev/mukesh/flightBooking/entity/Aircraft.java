package dev.mukesh.flightBooking.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Aircraft {

    @Id
    @SequenceGenerator(
            name = "aircraft_sequence",
            sequenceName = "aircraft_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aircraft_sequence")
    private Integer id;

    private String model;
    private String manufacturer;
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "airline_code", nullable = false)
    private Airline airline;



    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
