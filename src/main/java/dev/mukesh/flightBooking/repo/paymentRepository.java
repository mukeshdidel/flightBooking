package dev.mukesh.flightBooking.repo;


import dev.mukesh.flightBooking.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface paymentRepository extends JpaRepository<Payment, Integer> {
}
