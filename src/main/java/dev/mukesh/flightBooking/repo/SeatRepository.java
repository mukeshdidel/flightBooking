package dev.mukesh.flightBooking.repo;


import dev.mukesh.flightBooking.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {



    @Query(
            value = "SELECT * FROM seat WHERE aircraft_id = :aircraftId",
            nativeQuery = true
    )
    List<Seat> findByAircraftId(Integer aircraftId);
}
