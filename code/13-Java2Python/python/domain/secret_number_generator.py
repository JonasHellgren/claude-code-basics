import random

from valueobjects.interval import Interval


class SecretNumberGenerator:
    """Generates a random secret number within a given Interval."""

    def __init__(self, rng: random.Random | None = None) -> None:
        self._rng = rng if rng is not None else random.Random()

    def generate(self, interval: Interval) -> int:
        bound = interval.upper - interval.lower + 1
        return interval.lower + self._rng.randrange(bound)

    @staticmethod
    def create() -> "SecretNumberGenerator":
        return SecretNumberGenerator()
