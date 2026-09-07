package dev.mukesh.flightBooking.model.res;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegisterRes {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

}
