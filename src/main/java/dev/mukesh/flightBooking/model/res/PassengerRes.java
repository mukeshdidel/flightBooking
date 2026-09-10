package dev.mukesh.flightBooking.model.res;


import dev.mukesh.flightBooking.enums.Gender;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PassengerRes {

    private Integer passengerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String passportNumber;
    private Gender gender;
}
