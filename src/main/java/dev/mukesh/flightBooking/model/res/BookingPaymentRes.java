package dev.mukesh.flightBooking.model.res;

import dev.mukesh.flightBooking.enums.TransactionStatus;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class BookingPaymentRes {


    private Integer paymentId;
    private Integer bookingId;
    private String pnr;
    private Double amountPaid;
    private TransactionStatus transactionStatus;
    private String gatewayTransactionId;
    private LocalDateTime transactionDate;
    private String message;


}