package dev.mukesh.flightBooking.service;


import dev.mukesh.flightBooking.entity.User;
import dev.mukesh.flightBooking.exception.ConflictException;
import dev.mukesh.flightBooking.exception.InvalidCredentialsException;
import dev.mukesh.flightBooking.model.req.UserLoginReq;
import dev.mukesh.flightBooking.model.req.UserRegisterReq;
import dev.mukesh.flightBooking.model.res.UserLoginRes;
import dev.mukesh.flightBooking.model.res.UserRegisterRes;
import dev.mukesh.flightBooking.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final UserRepository userRepository;



    private void validateUserDoesNotExist(UserRegisterReq request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new ConflictException("Email already registered");
        }

        if(userRepository.existsByPhoneNumber((request.getPhoneNumber()))) {
            throw  new ConflictException("Phone number already registered");
        }

    }

    public UserRegisterRes register(UserRegisterReq userRegisterReqBody) {

        validateUserDoesNotExist(userRegisterReqBody);

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


    private User authenticateUser(UserLoginReq request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("invalid email or password"));

        if(!user.getPassword().equals(request.getPassword())) {
            throw new InvalidCredentialsException("invalid email or password");
        }

        return   user;
    }

    public UserLoginRes login(UserLoginReq userLoginReqBody) {

        User user = authenticateUser(userLoginReqBody);

        return UserLoginRes.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();

    }


}
