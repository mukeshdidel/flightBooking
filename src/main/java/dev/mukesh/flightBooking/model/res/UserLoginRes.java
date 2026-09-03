package dev.mukesh.flightBooking.model.res;


import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
public class UserLoginRes {

    private String token;

    // maybe a refresh token and user related fields in future

}
