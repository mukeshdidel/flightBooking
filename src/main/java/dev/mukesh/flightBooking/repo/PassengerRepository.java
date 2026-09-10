package dev.mukesh.flightBooking.repo;

import dev.mukesh.flightBooking.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {



    @Query(
            value = "select * from passenger p where p.user_id = :userId",
            nativeQuery = true
    )
    List<Passenger> findByUserId(Integer userId);



    @Modifying
    @Query(
            value = "delete from passenger p where p.passenger_id = :passengerId and p.user_id = :userId",
            nativeQuery = true
    )
    void deleteByPassengerId(Integer passengerId, Integer userId);
}
