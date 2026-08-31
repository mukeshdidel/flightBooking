package dev.mukesh.flightBooking.repo;


import dev.mukesh.flightBooking.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepo extends JpaRepository<Airline, String> {
}
