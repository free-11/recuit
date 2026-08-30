package org.example.recruit;

import org.example.recruit.utils.PasswordUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordUtilsTest {

    @Test
    void encryptedPasswordCanBeVerified() {
        String encrypted = PasswordUtils.encryptPassword("correct-password");

        assertTrue(PasswordUtils.matches("correct-password", encrypted));
        assertFalse(PasswordUtils.matches("wrong-password", encrypted));
    }
}
