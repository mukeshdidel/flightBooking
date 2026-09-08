package dev.mukesh.flightBooking.repo;

import dev.mukesh.flightBooking.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {



    @Query(
            value = "SELECT * FROM Flight WHERE source_airport = :src AND dest_airport = :dest AND DATE(departure_time) = CAST(:searchDate AS DATE)",
            nativeQuery = true
    )
    List<Flight> searchFlight(@Param("src") String source, @Param("dest") String destination, @Param("searchDate") String date);
}
