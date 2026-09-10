package dev.mukesh.flightBooking.service;


import dev.mukesh.flightBooking.entity.Booking;
import dev.mukesh.flightBooking.entity.Payment;
import dev.mukesh.flightBooking.entity.User;
import dev.mukesh.flightBooking.enums.BookingStatus;
import dev.mukesh.flightBooking.enums.TicketStatus;
import dev.mukesh.flightBooking.enums.TransactionStatus;
import dev.mukesh.flightBooking.exception.ConflictException;
import dev.mukesh.flightBooking.exception.ResourceNotFoundException;
import dev.mukesh.flightBooking.model.req.BookingPaymentReq;
import dev.mukesh.flightBooking.model.res.BookingPaymentRes;
import dev.mukesh.flightBooking.repo.BookingRepository;
import dev.mukesh.flightBooking.repo.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {


    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;


    private final UserService userService;


    @Transactional
    public BookingPaymentRes processBookingPayment(Integer bookingId, BookingPaymentReq request, Integer userId) {


        Booking booking = bookingRepository.findByBookingIdAndUserId(bookingId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found."));


        if (booking.getStatus() == BookingStatus.CONFIRMED) {
            throw new ConflictException("This booking has already been paid and confirmed.");
        }
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new ConflictException("This booking was cancelled. Your session may have expired.");
        }

        String transactionId = processPayment(
                request.getCardNumber(),
                request.getCvv(),
                booking.getTotalFare()
        );

        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(booking.getTotalFare());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setTransactionDate(LocalDateTime.now());
        payment.setStatus(TransactionStatus.SUCCESS);
        payment.setGatewayResp(transactionId);

        paymentRepository.save(payment);

        booking.setStatus(BookingStatus.CONFIRMED);
        booking.getTickets().forEach(ticket -> ticket.setStatus(TicketStatus.CONFIRMED));

        bookingRepository.save(booking);

        return BookingPaymentRes.builder()
                .paymentId(payment.getPaymentId())
                .bookingId(booking.getBookingId())
                .pnr(booking.getPNR())
                .amountPaid(payment.getAmount())
                .transactionStatus(payment.getStatus())
                .gatewayTransactionId(payment.getGatewayResp())
                .transactionDate(payment.getTransactionDate())
                .message("Payment successful. Your tickets are confirmed!")
                .build();
    }

    private String processPayment(String cardNumber, String cvv, Double amount) {

        return UUID.randomUUID().toString();
    }

}
