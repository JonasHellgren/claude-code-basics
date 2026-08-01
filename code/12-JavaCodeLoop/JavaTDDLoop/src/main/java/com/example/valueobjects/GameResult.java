package com.example.valueobjects;

/**
 * The outcome of a single {@code GuessGame} play-through.
 */
public record GameResult(int secretNumber, int attempts, boolean success) {

    public GameResult {
        if (attempts <= 0) {
            throw new IllegalArgumentException("attempts must be positive, was " + attempts);
        }
    }

    public static GameResult success(int secretNumber, int attempts) {
        return new GameResult(secretNumber, attempts, true);
    }

    public static GameResult failure(int secretNumber, int attempts) {
        return new GameResult(secretNumber, attempts, false);
    }
}
