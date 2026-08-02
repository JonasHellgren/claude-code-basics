package com.example.runner;

import com.example.valueobjects.GameResult;
import com.example.domain.GuessGame;
import com.example.domain.NumberGuesser;
import com.example.domain.SecretNumberGenerator;
import com.example.valueobjects.Interval;

/**
 * Entry point that wires up and plays a single {@link GuessGame}.
 */
public final class RunnerGuessGame {

    private RunnerGuessGame() {
    }

    public static void main(String[] args) {
        var startInterval = Interval.of(1, 100);
        var generator = SecretNumberGenerator.create();
        var guesser = NumberGuesser.create();
        var game = GuessGame.of(startInterval, generator, guesser);
        var result = game.play();
        System.out.println(result);
    }
}
