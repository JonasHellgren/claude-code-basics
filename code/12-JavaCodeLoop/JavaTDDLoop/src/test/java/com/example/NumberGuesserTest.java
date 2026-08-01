package com.example;

import com.example.domain.NumberGuesser;
import com.example.valueobjects.Interval;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@code NumberGuesser}.
 *
 * <p>The architecture requires the final strategy to be a binary-search midpoint:
 * {@code lower + (upper - lower) / 2}. These tests encode that required behaviour,
 * plus the general invariant that every guess stays within the given interval.</p>
 */
class NumberGuesserTest {

    @ParameterizedTest
    @CsvSource({
            "1, 100, 50",
            "1, 1, 1",
            "5, 10, 7",
            "0, 0, 0",
            "-10, 10, 0",
            "1, 2, 1"
    })
    void guessesTheMidpointOfTheInterval(int lower, int upper, int expectedGuess) {
        var guesser = new NumberGuesser();
        var interval = new Interval(lower, upper);

        assertEquals(expectedGuess, guesser.nextGuess(interval));
    }

    @ParameterizedTest
    @CsvSource({
            "1, 100",
            "-50, 50",
            "1, 1",
            "17, 42"
    })
    void guessStaysWithinInterval(int lower, int upper) {
        var guesser = new NumberGuesser();
        var interval = new Interval(lower, upper);

        int guess = guesser.nextGuess(interval);

        assertTrue(guess >= lower && guess <= upper,
                "Guess " + guess + " was outside [" + lower + ", " + upper + "]");
    }
}
