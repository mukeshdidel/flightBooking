package dev.mukesh.flightBooking.model.res;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class BookingGetRes {

    private Integer bookingId;
    private String pnr;
    private LocalDateTime bookingDate;
    private Double totalFare;
    private String status;

    private FlightSummary flight;

    private List<TicketDetails> tickets;

    @Data
    @Builder
    public static class FlightSummary {
        private String flightNumber;
        private String sourceAirportCode;
        private String destAirportCode;
        private LocalDateTime departureTime;
        private LocalDateTime arrivalTime;
    }

    @Data
    @Builder
    public static class TicketDetails {
        private Integer ticketId;
        private String passengerName;
        private String seatNumber;
        private String status;
    }
}