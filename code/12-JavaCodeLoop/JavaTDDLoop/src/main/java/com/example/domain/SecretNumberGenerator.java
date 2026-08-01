package com.example.domain;

import com.example.valueobjects.Interval;

import java.util.random.RandomGenerator;

/**
 * Generates a random secret number within a given {@link Interval}.
 */
public class SecretNumberGenerator {

    private final RandomGenerator random;

    public SecretNumberGenerator() {
        this(RandomGenerator.getDefault());
    }

    protected SecretNumberGenerator(RandomGenerator random) {
        this.random = random;
    }

    public int generate(Interval interval) {
        int bound = interval.upper() - interval.lower() + 1;
        return interval.lower() + random.nextInt(bound);
    }

    public static SecretNumberGenerator create() {
        return new SecretNumberGenerator();
    }
}
