package dev.mukesh.flightBooking.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Airline extends JpaRepository<Airline, String> {
}
