package com.example.domain;

import com.example.valueobjects.GameResult;
import com.example.valueobjects.Interval;

import java.util.Objects;

/**
 * Plays a guessing game: repeatedly narrows an {@link Interval} until the secret
 * number, produced by a {@link SecretNumberGenerator}, is found by a
 * {@link NumberGuesser}.
 *
 * <p>{@link #play()} always terminates: it gives up and returns a failed
 * {@link GameResult} once the number of attempts reaches the number of values in the
 * starting interval, which bounds even a misbehaving {@link NumberGuesser}.</p>
 */
public class GuessGame {

    private final Interval startInterval;
    private final SecretNumberGenerator generator;
    private final NumberGuesser guesser;

    public GuessGame(Interval startInterval, SecretNumberGenerator generator, NumberGuesser guesser) {
        this.startInterval = Objects.requireNonNull(startInterval, "startInterval must not be null");
        this.generator = Objects.requireNonNull(generator, "generator must not be null");
        this.guesser = Objects.requireNonNull(guesser, "guesser must not be null");
    }

    public static GuessGame of(Interval startInterval, SecretNumberGenerator generator, NumberGuesser guesser) {
        return new GuessGame(startInterval, generator, guesser);
    }

    public GameResult play() {
        int secretNumber = generator.generate(startInterval);
        Interval currentInterval = startInterval;
        int attempts = 0;
        int maxAttempts = startInterval.upper() - startInterval.lower() + 1;

        while (attempts < maxAttempts) {
            int guess = guesser.nextGuess(currentInterval);
            attempts++;

            if (guess == secretNumber) {
                return GameResult.success(secretNumber, attempts);
            }
            if (guess < secretNumber) {
                currentInterval = Interval.of(guess + 1, currentInterval.upper());
            } else {
                currentInterval = Interval.of(currentInterval.lower(), guess - 1);
            }
        }
        return GameResult.failure(secretNumber, attempts);
    }
}
