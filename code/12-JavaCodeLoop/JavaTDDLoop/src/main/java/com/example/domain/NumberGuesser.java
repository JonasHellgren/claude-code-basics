package com.example.domain;

import com.example.valueobjects.Interval;

/**
 * Produces the next guess for a {@code GuessGame}.
 *
 * <p>Guesses the midpoint of the interval (binary search).</p>
 */
public class NumberGuesser {

    public static NumberGuesser create() {
        return new NumberGuesser();
    }

    public int nextGuess(Interval interval) {
        return interval.lower() + (interval.upper() - interval.lower()) / 2;
    }
}
