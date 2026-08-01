package com.example;

import com.example.valueobjects.GameResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the {@code GameResult} value object.
 */
class GameResultTest {

    @Test
    void storesSecretNumberAttemptsAndSuccess() {
        var result = new GameResult(42, 5, true);

        assertEquals(42, result.secretNumber());
        assertEquals(5, result.attempts());
        assertTrue(result.success());
    }

    @Test
    void canRepresentAFailedGame() {
        var result = new GameResult(7, 10, false);

        assertEquals(7, result.secretNumber());
        assertEquals(10, result.attempts());
        assertFalse(result.success());
    }

    @Test
    void boundarySingleAttemptSuccess() {
        var result = new GameResult(1, 1, true);

        assertEquals(1, result.attempts());
        assertTrue(result.success());
    }

    @Test
    void rejectsNegativeAttempts() {
        assertThrows(IllegalArgumentException.class, () -> new GameResult(10, -1, true));
    }

    @Test
    void rejectsZeroAttempts() {
        assertThrows(IllegalArgumentException.class, () -> new GameResult(10, 0, true));
    }
}
