package com.example.vcsservice.service;

import com.example.vcsservice.common.exceptions.InvalidRequestDataException;
import com.example.vcsservice.common.exceptions.RecordNotFoundException;
import com.example.vcsservice.common.utils.SessionIdUtil;
import com.example.vcsservice.data.entity.User;
import com.example.vcsservice.data.repository.UserRepository;
import com.example.vcsservice.data.service.RedisTokenService;
import com.example.vcsservice.domain.dto.LoginRequest;
import com.example.vcsservice.domain.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.*;
import com.nimbusds.jwt.*;
import com.nimbusds.jwt.JWTClaimsSet.Builder;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class LoginService extends BaseService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisTokenService redisTokenService;

    private static final String ACCESS_TOKEN_SECRET = "IR3piPE7-0pqMRgSYimQd0NcMHsvT1aF2t72bk9H9TA";
    private static final String ID_TOKEN_SECRET = "IR3piPE7-0pqMRgSYimQd0NcMHsvT1aF2t72bk9H9TA";


    public LoginResponse loginUser(LoginRequest loginRequest) {
        //TODO: validate user details
        //TODO: if user is valid, create session
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isEmpty())
            throw new RecordNotFoundException("User does not exists");
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword()))
            throw new InvalidRequestDataException("Credentials do not match");
        //TODO: create an ID token in redis to store user claims
        final String sessionId = SessionIdUtil.generateSessionId();
        final String idToken = createIDToken(sessionId);
        final String accessToken = createAccessToken(sessionId, user.get());
        redisTokenService.saveToken(sessionId, accessToken);

        //TODO: create a access token to give to user
        //TODO: give user the access token
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(idToken);
        return loginResponse;
    }

    public void logoutUser() {
        //TODO: remove ID/session token from redis
        //TODO: update user is as logged out
        //TODO: remove/invalidate access token from user
        //TODO: remove user session
        //TODO: Logout user
    }

    private String createAccessToken(String sessionId, User user) {
        try {
            JWSSigner signer = new MACSigner(ACCESS_TOKEN_SECRET);

            JWTClaimsSet claimsSet = new Builder()
                    .subject(sessionId)
                    .expirationTime(new Date(System.currentTimeMillis() + 10* 60 * 1000))  // 10 minute expiration
                    .claim("token_type", "access_token")
                    .claim("session_id", sessionId)
                    .claim("email", user.getEmail())
                    .build();

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(signer);

            return signedJWT.serialize();
        } catch (JOSEException e) {
            // Handle exception
            return null;
        }
    }

    private String createIDToken(String sessionId) {
        try {
            JWSSigner signer = new MACSigner(ID_TOKEN_SECRET);

            // Add user-specific claims to the ID token
            JWTClaimsSet claimsSet = new Builder()
                    .subject(sessionId)
                    .expirationTime(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                    .build();

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(signer);

            return signedJWT.serialize();
        } catch (JOSEException e) {
            // Handle exception
            return null;
        }
    }
}
