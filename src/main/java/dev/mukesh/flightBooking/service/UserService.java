package dev.mukesh.flightBooking.service;


import dev.mukesh.flightBooking.entity.User;
import dev.mukesh.flightBooking.exception.ResourceNotFoundException;
import dev.mukesh.flightBooking.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private  final UserRepository userRepository;



    public User getUser(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }


}
