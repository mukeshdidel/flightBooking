package dev.mukesh.flightBooking.service;


import dev.mukesh.flightBooking.entity.Passenger;
import dev.mukesh.flightBooking.entity.User;
import dev.mukesh.flightBooking.model.req.PassengerCreateReq;
import dev.mukesh.flightBooking.model.res.PassengerDeleteRes;
import dev.mukesh.flightBooking.model.res.PassengerRes;
import dev.mukesh.flightBooking.repo.PassengerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;
    private final UserService userService;

    public List<PassengerRes> createPassenger(List<PassengerCreateReq> passengerCreateReqBody, Integer userId) {

        User user = userService.getUser(userId);

        List<Passenger> newPassengers = passengerCreateReqBody.stream().map( passenger -> Passenger.builder()
                .user(user)
                .gender(passenger.getGender())
                .firstName(passenger.getFirstName())
                .lastName(passenger.getLastName())
                .email(passenger.getEmail())
                .phoneNumber(passenger.getPhoneNumber())
                .passportNumber(passenger.getPassportNumber())
                .build()
        ).toList();

        List<Passenger> savedPassenger = passengerRepository.saveAll(newPassengers);

        return savedPassenger.stream().map( passenger -> PassengerRes.builder()

                .passengerId(passenger.getPassengerId())
                .gender(passenger.getGender())
                .firstName(passenger.getFirstName())
                .lastName(passenger.getLastName())
                .email(passenger.getEmail())
                .phoneNumber(passenger.getPhoneNumber())
                .passportNumber(passenger.getPassportNumber())
                .build()
        ).toList();
    }

    public List<PassengerRes> getPassenger(Integer userId) {

        User user = userService.getUser(userId);

        List<Passenger> passengers = passengerRepository.findByUserId(user.getUserId());

        return passengers.stream().map(passenger -> PassengerRes.builder()
                        .passengerId(passenger.getPassengerId())
                        .gender(passenger.getGender())
                        .firstName(passenger.getFirstName())
                        .lastName(passenger.getLastName())
                        .email(passenger.getEmail())
                        .phoneNumber(passenger.getPhoneNumber())
                        .passportNumber(passenger.getPassportNumber())
                        .build()
                ).toList();

    }




    @Transactional
    public PassengerDeleteRes deletePassenger(Integer passengerId, Integer userId) {

        passengerRepository.deleteByPassengerId(passengerId, userId);;

        return PassengerDeleteRes.builder().message("Passenger deleted successfully").build();

    }
}
