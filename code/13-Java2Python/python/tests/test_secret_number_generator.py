import pytest

from domain.secret_number_generator import SecretNumberGenerator
from valueobjects.interval import Interval

SAMPLE_SIZE = 1000


def test_generates_number_within_interval():
    generator = SecretNumberGenerator()
    interval = Interval(1, 100)

    for _ in range(SAMPLE_SIZE):
        secret = generator.generate(interval)
        assert interval.lower <= secret <= interval.upper


def test_single_value_interval_always_produces_that_value():
    generator = SecretNumberGenerator()
    interval = Interval(7, 7)

    for _ in range(SAMPLE_SIZE):
        assert generator.generate(interval) == 7


@pytest.mark.parametrize(
    "lower, upper",
    [
        (1, 10),
        (-5, 5),
        (50, 55),
    ],
)
def test_stays_within_bounds_for_various_intervals(lower, upper):
    generator = SecretNumberGenerator()
    interval = Interval(lower, upper)

    for _ in range(SAMPLE_SIZE):
        secret = generator.generate(interval)
        assert lower <= secret <= upper
