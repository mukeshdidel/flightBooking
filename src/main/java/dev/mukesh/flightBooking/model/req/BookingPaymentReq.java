package dev.mukesh.flightBooking.model.req;

import dev.mukesh.flightBooking.enums.PaymentMethod;
import lombok.Data;

@Data
public class BookingPaymentReq {
    private String cardNumber;
    private String cardHolderName;
    private String cvv;

    private PaymentMethod paymentMethod;

}