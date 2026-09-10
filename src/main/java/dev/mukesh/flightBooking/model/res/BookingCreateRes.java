package dev.mukesh.flightBooking.model.res;


import dev.mukesh.flightBooking.enums.BookingStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BookingCreateRes {

    private Integer bookingId;
    private LocalDateTime bookingDate;
    private BookingStatus status;
    private String PNR;
    private Double totalFare;

}
