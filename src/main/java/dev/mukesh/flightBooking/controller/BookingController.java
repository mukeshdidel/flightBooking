package dev.mukesh.flightBooking.controller;


import dev.mukesh.flightBooking.model.req.BookingCreateReq;
import dev.mukesh.flightBooking.model.req.BookingPaymentReq;
import dev.mukesh.flightBooking.model.res.BookingCreateRes;
import dev.mukesh.flightBooking.model.res.BookingGetRes;
import dev.mukesh.flightBooking.model.res.BookingPaymentRes;
import dev.mukesh.flightBooking.service.BookingService;
import dev.mukesh.flightBooking.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(
        origins = {
                "http://localhost:4200",
        }
)
public class BookingController {


    private final BookingService bookingService;
    private final PaymentService paymentService;

    @PostMapping("/bookings")
    public ResponseEntity<BookingCreateRes> createBooking(@Valid @RequestBody BookingCreateReq bookingCreateReq, @RequestHeader("user_id") Integer userId) {
        BookingCreateRes bookingCreateRes = bookingService.createBooking(bookingCreateReq, userId);
        return new ResponseEntity<>(bookingCreateRes, HttpStatus.CREATED);
    }



    @PostMapping("/bookings/payment/{bookingId}")
    public ResponseEntity<BookingPaymentRes> payForBooking(
            @PathVariable  Integer bookingId,
            @RequestBody BookingPaymentReq request,
            @RequestHeader("user_id") Integer userId) {

        BookingPaymentRes response = paymentService.processBookingPayment(bookingId, request, userId);

        return ResponseEntity.ok(response);
    }


    @GetMapping("bookings")
    public ResponseEntity<List<BookingGetRes>> getMyBookings(
            @RequestHeader("user_id") Integer userId) {

        List<BookingGetRes> response = bookingService.getUserBookings(userId);
        return ResponseEntity.ok(response);
    }



}
