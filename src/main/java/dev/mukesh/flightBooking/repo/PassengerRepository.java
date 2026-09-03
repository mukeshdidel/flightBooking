package dev.mukesh.flightBooking.repo;

import dev.mukesh.flightBooking.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
}
