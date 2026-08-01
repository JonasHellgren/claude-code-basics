package com.example;

import com.example.domain.GuessGame;
import com.example.domain.NumberGuesser;
import com.example.testsupport.FakeSecretNumberGenerator;
import com.example.testsupport.RecordingNumberGuesser;
import com.example.valueobjects.GameResult;
import com.example.valueobjects.Interval;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@code GuessGame}.
 */
class GuessGameTest {

    private static final Interval FULL_RANGE = new Interval(1, 100);
    private static final int MAX_ATTEMPTS_FOR_FULL_RANGE = 7;

    @Test
    void producesCorrectGameResultOnSuccess() {
        var generator = new FakeSecretNumberGenerator(42);
        var guesser = new NumberGuesser();
        var game = new GuessGame(FULL_RANGE, generator, guesser);

        GameResult result = game.play();

        assertEquals(42, result.secretNumber());
        assertTrue(result.success());
        assertTrue(result.attempts() >= 1);
        assertTrue(result.attempts() <= MAX_ATTEMPTS_FOR_FULL_RANGE);
    }

    @Test
    void everySecretBetween1And100IsFoundWithinSevenAttempts() {
        for (int secret = 1; secret <= 100; secret++) {
            var generator = new FakeSecretNumberGenerator(secret);
            var guesser = new NumberGuesser();
            var game = new GuessGame(FULL_RANGE, generator, guesser);

            GameResult result = game.play();

            assertTrue(result.success(), "Secret " + secret + " was not found");
            assertEquals(secret, result.secretNumber());
            assertTrue(result.attempts() <= MAX_ATTEMPTS_FOR_FULL_RANGE,
                    "Secret " + secret + " took " + result.attempts() + " attempts, expected <= "
                            + MAX_ATTEMPTS_FOR_FULL_RANGE);
        }
    }

    @Test
    void findsLowerBoundarySecretWithinSevenAttempts() {
        var generator = new FakeSecretNumberGenerator(1);
        var guesser = new NumberGuesser();
        var game = new GuessGame(FULL_RANGE, generator, guesser);

        GameResult result = game.play();

        assertTrue(result.success());
        assertEquals(1, result.secretNumber());
        assertTrue(result.attempts() <= MAX_ATTEMPTS_FOR_FULL_RANGE);
    }

    @Test
    void findsUpperBoundarySecretWithinSevenAttempts() {
        var generator = new FakeSecretNumberGenerator(100);
        var guesser = new NumberGuesser();
        var game = new GuessGame(FULL_RANGE, generator, guesser);

        GameResult result = game.play();

        assertTrue(result.success());
        assertEquals(100, result.secretNumber());
        assertTrue(result.attempts() <= MAX_ATTEMPTS_FOR_FULL_RANGE);
    }

    @Test
    void singleValueIntervalSucceedsInOneAttempt() {
        var singleValue = new Interval(5, 5);
        var generator = new FakeSecretNumberGenerator(5);
        var guesser = new NumberGuesser();
        var game = new GuessGame(singleValue, generator, guesser);

        GameResult result = game.play();

        assertTrue(result.success());
        assertEquals(5, result.secretNumber());
        assertEquals(1, result.attempts());
    }

    @Test
    void intervalNarrowsAsPlayProgresses() {
        var generator = new FakeSecretNumberGenerator(73);
        var recordingGuesser = new RecordingNumberGuesser();
        var game = new GuessGame(FULL_RANGE, generator, recordingGuesser);

        GameResult result = game.play();

        List<Interval> recorded = recordingGuesser.recordedIntervals();

        assertTrue(result.success());
        assertEquals(recorded.size(), result.attempts());
        assertEquals(FULL_RANGE, recorded.get(0));

        for (Interval interval : recorded) {
            assertTrue(73 >= interval.lower() && 73 <= interval.upper(),
                    "Secret must always remain within the current interval " + interval);
        }

        for (int i = 1; i < recorded.size(); i++) {
            int previousWidth = recorded.get(i - 1).upper() - recorded.get(i - 1).lower();
            int currentWidth = recorded.get(i).upper() - recorded.get(i).lower();
            assertTrue(currentWidth < previousWidth,
                    "Interval did not narrow between attempt " + i + " and " + (i + 1));
        }
    }

    @Test
    void rejectsNullStartInterval() {
        var generator = new FakeSecretNumberGenerator(1);
        var guesser = new NumberGuesser();

        assertThrows(NullPointerException.class, () -> new GuessGame(null, generator, guesser));
    }

    @Test
    void rejectsNullGenerator() {
        var guesser = new NumberGuesser();

        assertThrows(NullPointerException.class, () -> new GuessGame(FULL_RANGE, null, guesser));
    }

    @Test
    void rejectsNullGuesser() {
        var generator = new FakeSecretNumberGenerator(1);

        assertThrows(NullPointerException.class, () -> new GuessGame(FULL_RANGE, generator, null));
    }
}
