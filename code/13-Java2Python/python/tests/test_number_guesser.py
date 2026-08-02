import pytest

from domain.number_guesser import NumberGuesser
from valueobjects.interval import Interval


@pytest.mark.parametrize(
    "lower, upper, expected_guess",
    [
        (1, 100, 50),
        (1, 1, 1),
        (5, 10, 7),
        (0, 0, 0),
        (-10, 10, 0),
        (1, 2, 1),
    ],
)
def test_guesses_the_midpoint_of_the_interval(lower, upper, expected_guess):
    guesser = NumberGuesser()
    interval = Interval(lower, upper)

    assert guesser.next_guess(interval) == expected_guess


@pytest.mark.parametrize(
    "lower, upper",
    [
        (1, 100),
        (-50, 50),
        (1, 1),
        (17, 42),
    ],
)
def test_guess_stays_within_interval(lower, upper):
    guesser = NumberGuesser()
    interval = Interval(lower, upper)

    guess = guesser.next_guess(interval)

    assert lower <= guess <= upper
