package dev.mukesh.flightBooking.controller;


import dev.mukesh.flightBooking.model.req.UserLoginReq;
import dev.mukesh.flightBooking.model.req.UserRegisterReq;
import dev.mukesh.flightBooking.model.res.UserLoginRes;
import dev.mukesh.flightBooking.model.res.UserRegisterRes;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/public/auth")
public class AuthController {


    @PostMapping("/register")
    public ResponseEntity<UserRegisterRes> register(@Valid @RequestBody UserRegisterReq body) {
        // todo: add service logic
        return ResponseEntity.ok(new UserRegisterRes());
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginRes> login(@RequestBody UserLoginReq body) {
        // service logic

        return ResponseEntity.ok(new UserLoginRes());
    }










}
