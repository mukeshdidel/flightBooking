package dev.mukesh.flightBooking.repo;

import dev.mukesh.flightBooking.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {



    @Query(
            value = "select * from flight where source_airport = :src and dest_airport = :dest and DATE(departure_time) = :searchDate and available_seats > 0",
            nativeQuery = true
    )
    List<Flight> searchFlight(@Param("src") String source, @Param("dest") String destination, @Param("searchDate") LocalDate date);
}
