import pytest

from valueobjects.interval import Interval


def test_stores_lower_and_upper_bounds():
    interval = Interval(1, 100)

    assert interval.lower == 1
    assert interval.upper == 100


@pytest.mark.parametrize(
    "lower, upper",
    [
        (1, 100),
        (-10, 10),
        (0, 0),
        (5, 5),
        (-5, -1),
    ],
)
def test_accepts_valid_bounds(lower, upper):
    interval = Interval(lower, upper)

    assert interval.lower == lower
    assert interval.upper == upper


def test_single_value_interval_is_valid_boundary_case():
    interval = Interval(42, 42)

    assert interval.lower == 42
    assert interval.upper == 42


@pytest.mark.parametrize(
    "lower, upper",
    [
        (100, 1),
        (0, -1),
        (5, 4),
    ],
)
def test_rejects_lower_greater_than_upper(lower, upper):
    with pytest.raises(ValueError):
        Interval(lower, upper)
