package com.example.vcsservice.common.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class SessionIdUtil {
    private static final int SESSION_ID_LENGTH = 32;

    public static String generateSessionId() {
        byte[] randomBytes = new byte[SESSION_ID_LENGTH];
        try {
            SecureRandom secureRandom = SecureRandom.getInstanceStrong();
            secureRandom.nextBytes(randomBytes);
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception, e.g., log an error and fallback to another method
            e.printStackTrace();
        }

        // Convert the random bytes to a Base64-encoded string
        return Base64.getUrlEncoder().encodeToString(randomBytes);
    }
}
