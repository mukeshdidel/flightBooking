package dev.mukesh.flightBooking.controller;


import dev.mukesh.flightBooking.model.req.UserLoginReq;
import dev.mukesh.flightBooking.model.req.UserRegisterReq;
import dev.mukesh.flightBooking.model.res.UserLoginRes;
import dev.mukesh.flightBooking.model.res.UserRegisterRes;
import dev.mukesh.flightBooking.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/public/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterRes> register(@Valid @RequestBody UserRegisterReq userRegisterReqBody) {
        UserRegisterRes res = authService.register(userRegisterReqBody);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
//
//    @PostMapping("/login")
//    public ResponseEntity<UserLoginRes> login(@RequestBody UserLoginReq userLoginReqBody) {
//
//    }










}
