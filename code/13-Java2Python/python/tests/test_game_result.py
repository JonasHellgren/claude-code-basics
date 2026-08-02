import pytest

from valueobjects.game_result import GameResult


def test_stores_secret_number_attempts_and_success():
    result = GameResult(42, 5, True)

    assert result.secret_number == 42
    assert result.attempts == 5
    assert result.success is True


def test_can_represent_a_failed_game():
    result = GameResult(7, 10, False)

    assert result.secret_number == 7
    assert result.attempts == 10
    assert result.success is False


def test_boundary_single_attempt_success():
    result = GameResult(1, 1, True)

    assert result.attempts == 1
    assert result.success is True


def test_rejects_negative_attempts():
    with pytest.raises(ValueError):
        GameResult(10, -1, True)


def test_rejects_zero_attempts():
    with pytest.raises(ValueError):
        GameResult(10, 0, True)
