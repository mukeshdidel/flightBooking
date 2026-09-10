package dev.mukesh.flightBooking.model.req;


import dev.mukesh.flightBooking.enums.Gender;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
public class PassengerCreateReq {


    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    private Gender gender;

    private String email;

    private String phoneNumber;

    private String passportNumber;


}
