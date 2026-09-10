package dev.mukesh.flightBooking.service;


import dev.mukesh.flightBooking.entity.*;
import dev.mukesh.flightBooking.enums.BookingStatus;
import dev.mukesh.flightBooking.enums.TicketStatus;
import dev.mukesh.flightBooking.exception.ConflictException;
import dev.mukesh.flightBooking.exception.ResourceNotFoundException;
import dev.mukesh.flightBooking.model.req.BookingCreateReq;
import dev.mukesh.flightBooking.model.res.BookingCreateRes;
import dev.mukesh.flightBooking.model.res.BookingGetRes;
import dev.mukesh.flightBooking.repo.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {


    private final FlightRepository flightRepository;
    private final TicketRepository ticketRepository;

    private final UserService userService;

    private final PassengerRepository passengerRepository;
    private final SeatRepository seatRepository;

    private final SeatService seatService;
    private final BookingRepository bookingRepository;

    private final FlightSeatMapRepository flightSeatMapRepository;

    @Transactional
    public BookingCreateRes createBooking(BookingCreateReq bookingCreateReq, Integer userId) {


        Flight flight = flightRepository.findById(bookingCreateReq.getFlightId())
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found"));

        int requestedSeatsCount = bookingCreateReq.getPassengerSeatMap().size();

        if (flight.getAvailableSeats() < requestedSeatsCount) {
            throw new ConflictException("Not enough available seats on this flight.");
        }

        Set<Integer> passengerIds = bookingCreateReq.getPassengerSeatMap().stream()
                .map(BookingCreateReq.PassengerSeatReq::getPassengerId).collect(Collectors.toSet());

        Set<Integer> seatIds = bookingCreateReq.getPassengerSeatMap().stream()
                .map(BookingCreateReq.PassengerSeatReq::getSeatId).collect(Collectors.toSet());

        if (passengerIds.size() != requestedSeatsCount || seatIds.size() != requestedSeatsCount) {
            throw new IllegalArgumentException("Duplicate passengers or seats in request.");
        }



        User user = userService.getUser(userId);
        List<Passenger> passengers = passengerRepository.findAllById(passengerIds);

        if (passengers.size() != requestedSeatsCount) {
            throw new ResourceNotFoundException("One or more passengers not found.");
        }

        List<FlightSeatMap> flightSeatMaps = flightSeatMapRepository.findByFlightIdAndSeatId(flight.getFlightId(), seatIds);

        if (flightSeatMaps.size() != requestedSeatsCount) {
            throw new ConflictException("One or more seats are invalid for this flight.");
        }


        Booking booking = new Booking();
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setPNR(UUID.randomUUID().toString());
        booking.setStatus(BookingStatus.PENDING);
        booking.setBookingDate(LocalDateTime.now());
        booking.setTotalFare(flight.getPrice() * requestedSeatsCount);


        List<Ticket> tickets = bookingCreateReq.getPassengerSeatMap().stream().map(ps -> {



            Passenger passenger = passengers.stream()
                    .filter(p -> p.getPassengerId().equals(ps.getPassengerId()))
                    .findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Passenger with ID " + ps.getPassengerId() + " not found."));

            if (passenger.getUser() == null || !passenger.getUser().getUserId().equals(userId)) {
                throw new ConflictException("Passenger does not belong to the requesting user.");
            }

            FlightSeatMap flightSeatMap = flightSeatMaps.stream()
                    .filter(fsm -> fsm.getSeat().getSeatId().equals(ps.getSeatId()))
                    .findFirst()
                    .orElseThrow(() -> new ConflictException("Seat ID " + ps.getSeatId() + " is invalid for this flight."));

            if (!flightSeatMap.getIsAvailable()) {
                throw new ConflictException("Seat ID " + ps.getSeatId() + " is already booked.");
            }

            flightSeatMap.setIsAvailable(false);


            Ticket ticket = new Ticket();
            ticket.setBooking(booking);
            ticket.setFlight(flight);
            ticket.setPassenger(passenger);
            ticket.setFlightSeatMap(flightSeatMap);
            ticket.setStatus(TicketStatus.PENDING);

            return ticket;


        }).collect(Collectors.toList());


        booking.setTickets(tickets);

        flight.setAvailableSeats(flight.getAvailableSeats() - requestedSeatsCount);

        flightRepository.save(flight);
        Booking savedBooking = bookingRepository.save(booking);

        ticketRepository.saveAll(tickets);

        return BookingCreateRes.builder()
                .bookingId(savedBooking.getBookingId())
                .PNR(savedBooking.getPNR())
                .status(savedBooking.getStatus())
                .totalFare(savedBooking.getTotalFare())
                .bookingDate(savedBooking.getBookingDate())
                .build();

    }

    public List<BookingGetRes> getUserBookings(Integer userId) {

        User user = userService.getUser(userId);

        List<Booking> bookings = bookingRepository.findByUser(user);

        return bookings.stream().map(booking -> BookingGetRes.builder()
                .bookingId(booking.getBookingId())
                .pnr(booking.getPNR())
                .bookingDate(booking.getBookingDate())
                .totalFare(booking.getTotalFare())
                .status(booking.getStatus().name())
                .flight(BookingGetRes.FlightSummary.builder()
                        .flightNumber(booking.getFlight().getFlightNum())
                        .sourceAirportCode(booking.getFlight().getSourceAirport().getAirportCode())
                        .destAirportCode(booking.getFlight().getDestAirport().getAirportCode())
                        .departureTime(booking.getFlight().getDepartureTime())
                        .arrivalTime(booking.getFlight().getArrivalTime())
                        .build())
                .tickets(booking.getTickets().stream().map(ticket -> BookingGetRes.TicketDetails.builder()
                        .ticketId(ticket.getId())
                        .passengerName(ticket.getPassenger().getFirstName() + " " + ticket.getPassenger().getLastName())
                        .seatNumber(ticket.getFlightSeatMap().getSeat().getSeatNumber())
                        .status(ticket.getStatus().name())
                        .build()).toList())
                .build()).toList();

    }
}
