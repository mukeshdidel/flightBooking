package dev.mukesh.flightBooking.repo;

import dev.mukesh.flightBooking.entity.Booking;
import dev.mukesh.flightBooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {


    @Query(
            value = "select * from booking where booking_id = :bookingId and user_id = :userId",
            nativeQuery = true
    )
    Optional<Booking> findByBookingIdAndUserId(Integer bookingId, Integer userId);

    List<Booking> findByUser(User user);
}
