package org.example.recruit.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Limits repeated application requests for the same student number.
 * This is intentionally process-local; the database unique key remains the
 * final protection against duplicate records.
 */
@Component
public class StudentApplyRateLimiter {
    private final long cooldownMillis;
    private final Map<Long, Long> lastAttempts = new ConcurrentHashMap<>();

    public StudentApplyRateLimiter(
            @Value("${recruit.apply.cooldown-seconds:60}") long cooldownSeconds) {
        if (cooldownSeconds < 0) {
            throw new IllegalArgumentException("报名冷却时间不能为负数");
        }
        this.cooldownMillis = Duration.ofSeconds(cooldownSeconds).toMillis();
    }

    /**
     * Attempts to acquire the right to submit for a student number.
     * A zero-second cooldown disables this additional time-based limit.
     */
    public synchronized boolean tryAcquire(Long studentNum) {
        long now = System.currentTimeMillis();
        cleanupExpired(now);

        Long previous = lastAttempts.get(studentNum);
        if (cooldownMillis > 0 && previous != null && now - previous < cooldownMillis) {
            return false;
        }
        lastAttempts.put(studentNum, now);
        return true;
    }

    public long getCooldownSeconds() {
        return cooldownMillis / 1000;
    }

    private void cleanupExpired(long now) {
        if (lastAttempts.size() < 1000 || cooldownMillis == 0) {
            return;
        }
        lastAttempts.entrySet().removeIf(entry -> now - entry.getValue() >= cooldownMillis);
    }
}
