package org.example.recruit;

import org.example.recruit.utils.JwtUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JwtUtilsTest {

    private static final String SECRET =
            "test-secret-must-be-at-least-sixty-four-characters-long-for-hs512-signing";

    @Test
    void tokenRemainsValidForAnotherInstanceUsingTheSameConfiguredSecret() {
        JwtUtils issuer = new JwtUtils(SECRET);
        String token = issuer.generateToken("admin");

        JwtUtils verifier = new JwtUtils(SECRET);

        assertTrue(verifier.validateToken(token));
        assertEquals("admin", verifier.getUsernameFromToken(token));
    }
}
