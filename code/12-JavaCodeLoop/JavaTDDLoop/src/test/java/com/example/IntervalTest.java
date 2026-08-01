package com.example;

import com.example.valueobjects.Interval;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for the {@code Interval} value object.
 */
class IntervalTest {

    @Test
    void storesLowerAndUpperBounds() {
        var interval = new Interval(1, 100);

        assertEquals(1, interval.lower());
        assertEquals(100, interval.upper());
    }

    @ParameterizedTest
    @CsvSource({
            "1, 100",
            "-10, 10",
            "0, 0",
            "5, 5",
            "-5, -1"
    })
    void acceptsValidBounds(int lower, int upper) {
        var interval = new Interval(lower, upper);

        assertEquals(lower, interval.lower());
        assertEquals(upper, interval.upper());
    }

    @Test
    void singleValueIntervalIsValidBoundaryCase() {
        var interval = new Interval(42, 42);

        assertEquals(42, interval.lower());
        assertEquals(42, interval.upper());
    }

    @ParameterizedTest
    @CsvSource({
            "100, 1",
            "0, -1",
            "5, 4"
    })
    void rejectsLowerGreaterThanUpper(int lower, int upper) {
        assertThrows(IllegalArgumentException.class, () -> new Interval(lower, upper));
    }
}
