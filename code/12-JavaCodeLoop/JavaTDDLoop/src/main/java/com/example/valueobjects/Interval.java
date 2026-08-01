package com.example.valueobjects;

/**
 * An inclusive range of integers between {@code lower} and {@code upper}.
 */
public record Interval(int lower, int upper) {

    public static Interval of(int lower, int upper) {
        return new Interval(lower, upper);
    }

    public Interval {
        if (lower > upper) {
            throw new IllegalArgumentException(
                    "lower (" + lower + ") must not be greater than upper (" + upper + ")");
        }
    }
}
