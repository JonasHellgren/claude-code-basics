package com.example;

import com.example.domain.SecretNumberGenerator;
import com.example.valueobjects.Interval;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@code SecretNumberGenerator}.
 *
 * <p>The generator relies on randomness, so these tests assert the deterministic
 * invariant that every generated number stays within the bounds of the given
 * interval, rather than asserting any specific generated value.</p>
 */
class SecretNumberGeneratorTest {

    private static final int SAMPLE_SIZE = 1000;

    @Test
    void generatesNumberWithinInterval() {
        var generator = new SecretNumberGenerator();
        var interval = new Interval(1, 100);

        for (int i = 0; i < SAMPLE_SIZE; i++) {
            int secret = generator.generate(interval);
            assertTrue(secret >= interval.lower() && secret <= interval.upper(),
                    "Generated secret " + secret + " was outside interval " + interval);
        }
    }

    @Test
    void singleValueIntervalAlwaysProducesThatValue() {
        var generator = new SecretNumberGenerator();
        var interval = new Interval(7, 7);

        for (int i = 0; i < SAMPLE_SIZE; i++) {
            assertEquals(7, generator.generate(interval));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "1, 10",
            "-5, 5",
            "50, 55"
    })
    void staysWithinBoundsForVariousIntervals(int lower, int upper) {
        var generator = new SecretNumberGenerator();
        var interval = new Interval(lower, upper);

        for (int i = 0; i < SAMPLE_SIZE; i++) {
            int secret = generator.generate(interval);
            assertTrue(secret >= lower && secret <= upper,
                    "Generated secret " + secret + " was outside [" + lower + ", " + upper + "]");
        }
    }
}
