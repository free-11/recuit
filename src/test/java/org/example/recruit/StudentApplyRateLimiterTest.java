package org.example.recruit;

import org.example.recruit.service.StudentApplyRateLimiter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentApplyRateLimiterTest {
    @Test
    void rejectsRepeatedRequestDuringCooldown() {
        StudentApplyRateLimiter limiter = new StudentApplyRateLimiter(60);

        assertTrue(limiter.tryAcquire(2024000001L));
        assertFalse(limiter.tryAcquire(2024000001L));
        assertTrue(limiter.tryAcquire(2024000002L));
    }

    @Test
    void zeroCooldownDisablesTimeLimit() {
        StudentApplyRateLimiter limiter = new StudentApplyRateLimiter(0);

        assertTrue(limiter.tryAcquire(2024000001L));
        assertTrue(limiter.tryAcquire(2024000001L));
    }

    @Test
    void rejectsNegativeCooldown() {
        assertThrows(IllegalArgumentException.class, () -> new StudentApplyRateLimiter(-1));
    }
}
