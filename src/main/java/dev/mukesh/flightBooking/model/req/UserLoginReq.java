package dev.mukesh.flightBooking.model.req;


import lombok.Data;

@Data
public class UserLoginReq {

    private String email;
    private String password;

}
