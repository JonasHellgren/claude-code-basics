package com.example.testsupport;

import com.example.domain.NumberGuesser;
import com.example.valueobjects.Interval;

/**
 * Broken test double for {@code NumberGuesser}.
 *
 * <p>Always returns the same fixed guess, ignoring the interval it is given. Used to
 * verify that {@code GuessGame} cannot be driven into an infinite loop by a
 * misbehaving guesser.</p>
 */
public class StubbornNumberGuesser extends NumberGuesser {

    private final int fixedGuess;

    public StubbornNumberGuesser(int fixedGuess) {
        this.fixedGuess = fixedGuess;
    }

    @Override
    public int nextGuess(Interval interval) {
        return fixedGuess;
    }
}
