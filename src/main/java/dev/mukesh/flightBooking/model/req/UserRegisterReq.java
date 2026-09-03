package dev.mukesh.flightBooking.model.req;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterReq {

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;


    @NotBlank(message = "Password is required")
    private String password;

    private String firstName;

    private String lastName;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

}
