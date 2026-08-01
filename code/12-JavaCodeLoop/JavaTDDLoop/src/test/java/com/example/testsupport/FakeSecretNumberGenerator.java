package com.example.testsupport;

import com.example.valueobjects.Interval;
import com.example.domain.SecretNumberGenerator;

/**
 * Deterministic test double for {@code SecretNumberGenerator}.
 *
 * <p>Always returns the fixed secret number it was created with, regardless of the
 * interval passed in. Used so that {@code GuessGame} behaviour can be tested without
 * relying on real randomness.</p>
 */
public class FakeSecretNumberGenerator extends SecretNumberGenerator {

    private final int fixedSecret;

    public FakeSecretNumberGenerator(int fixedSecret) {
        this.fixedSecret = fixedSecret;
    }

    @Override
    public int generate(Interval interval) {
        return fixedSecret;
    }
}
