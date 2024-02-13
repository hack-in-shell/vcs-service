package com.example.vcsservice.service;

import com.example.vcsservice.data.repository.UserRepository;
import com.example.vcsservice.domain.dto.CreateUserRequest;
import com.example.vcsservice.domain.dto.CreateUserResponse;
import com.example.vcsservice.data.entity.User;
import com.example.vcsservice.domain.model.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationService extends BaseService {
    private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;


    public CreateUserResponse registerUser(CreateUserRequest createRequest) {
        // TODO: Validate user details
        validateUserDetails(new UserDetails(createRequest.getFirstName(), createRequest.getLastName(), createRequest.getEmail()));

        // TODO: Check if user already exists
        if (userExists(createRequest.getEmail()))
            throw new RuntimeException("User already exists");

        // TODO: Create user account
        User user = createUserAccount(createRequest);

        // TODO: Send verification email
//        sendVerificationEmail(createRequest.getEmail());

        // TODO: Save request in temp and send response
        return new CreateUserResponse(user.getFirstName(), user.getLastName(), user.getEmail());
    }

    private User createUserAccount(CreateUserRequest createRequest) {
        User newUser = new User();
        newUser.setFirstName(createRequest.getFirstName());
        newUser.setLastName(createRequest.getLastName());
        newUser.setEmail(createRequest.getEmail());

        String encodedPassword = passwordEncoder.encode(createRequest.getPassword());
        newUser.setPassword(encodedPassword);

        User savedUser = userRepository.save(newUser);
        return savedUser;
    }

    public void verifyEmail(String token) {
        //TODO: check if mail is valid

        return;
    }

    public boolean userExists(String email) {
        //TODO: check with db by user email/userId if user exists
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            return true;
        }
        return false;
    }

}
