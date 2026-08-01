package com.example.testsupport;

import com.example.valueobjects.Interval;
import com.example.domain.NumberGuesser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Test spy for {@code NumberGuesser}.
 *
 * <p>Records every interval it is asked to guess against, then delegates to the real
 * {@code NumberGuesser} strategy so behaviour stays realistic. Used to observe how the
 * interval narrows across a {@code GuessGame} play-through.</p>
 */
public class RecordingNumberGuesser extends NumberGuesser {

    private final List<Interval> recordedIntervals = new ArrayList<>();

    @Override
    public int nextGuess(Interval interval) {
        recordedIntervals.add(interval);
        return super.nextGuess(interval);
    }

    public List<Interval> recordedIntervals() {
        return Collections.unmodifiableList(recordedIntervals);
    }
}
