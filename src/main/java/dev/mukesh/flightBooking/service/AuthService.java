package dev.mukesh.flightBooking.service;


import dev.mukesh.flightBooking.entity.User;
import dev.mukesh.flightBooking.exception.ConflictException;
import dev.mukesh.flightBooking.exception.InvalidCredentialsException;
import dev.mukesh.flightBooking.model.req.UserLoginReq;
import dev.mukesh.flightBooking.model.req.UserRegisterReq;
import dev.mukesh.flightBooking.model.res.UserLoginRes;
import dev.mukesh.flightBooking.model.res.UserRegisterRes;
import dev.mukesh.flightBooking.repo.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {


    // repo
    private final UserRepository userRepository;



    public UserRegisterRes register(UserRegisterReq userRegisterReqBody) {

        if(userRepository.existsByEmail(userRegisterReqBody.getEmail())) {
            throw new ConflictException("Email already registered");
        }

        if(userRepository.existsByPhoneNumber((userRegisterReqBody.getPhoneNumber()))) {
            throw  new ConflictException("Phone number already registered");
        }

        User newUser = User.builder()
                .email(userRegisterReqBody.getEmail())
                // todo: hash password
                .password(userRegisterReqBody.getPassword())
                .firstName(userRegisterReqBody.getFirstName())
                .lastName(userRegisterReqBody.getLastName())
                .phoneNumber(userRegisterReqBody.getPhoneNumber())
                .build();

        User savedUser = userRepository.save(newUser);

        return UserRegisterRes.builder()
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .phoneNumber(savedUser.getPhoneNumber())
                .build();

    }


    public UserLoginRes login(UserLoginReq userLoginReqBody) {

        User user = userRepository.findByEmail(userLoginReqBody.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("invalid email or password"));

        if(!user.getPassword().equals(userLoginReqBody.getPassword())) {
            throw new InvalidCredentialsException("invalid email or password");
        }

        return UserLoginRes.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();

    }


}
