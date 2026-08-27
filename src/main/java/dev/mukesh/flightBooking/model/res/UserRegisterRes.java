package dev.mukesh.flightBooking.model.res;

import lombok.Data;

@Data
public class UserRegisterRes {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

}
