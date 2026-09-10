package dev.mukesh.flightBooking.repo;


import dev.mukesh.flightBooking.entity.FlightSeatMap;
import dev.mukesh.flightBooking.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface FlightSeatMapRepository extends JpaRepository<FlightSeatMap, Integer> {

    @Query(
            value = "SELECT s.* FROM seat s JOIN flight_seat_map fsm ON s.seat_id = fsm.seat_id WHERE fsm.flight_id = :flightId AND fsm.is_available = true",
            nativeQuery = true
    )
    List<Seat> findAvailableSeatsByFlightId(@Param("flightId") Integer flightId);


    @Modifying
    @Query(
            value = "UPDATE flight_seat_map SET is_available = false WHERE seat_id = :seatId AND flight_id = :flightId",
            nativeQuery = true
    )
    void markAsTakenBySeatIdAndFlightId(@Param("seatId") Integer seatId, @Param("flightId") Integer flightId);



    @Query(
            value = "SELECT * FROM flight_seat_map WHERE flight_id = :flightId",
            nativeQuery = true
    )
    List<FlightSeatMap> findByFlightId(Integer flightId);


    @Query(
            value = "SELECT * FROM flight_seat_map WHERE flight_id = :flightId AND seat_id IN (:seatIds)",
            nativeQuery = true
    )
    List<FlightSeatMap> findByFlightIdAndSeatId(Integer flightId, Set<Integer> seatIds);
}