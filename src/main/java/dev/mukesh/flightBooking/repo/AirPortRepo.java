package dev.mukesh.flightBooking.repo;


import dev.mukesh.flightBooking.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirPortRepo extends JpaRepository<Airport, String> {


}
