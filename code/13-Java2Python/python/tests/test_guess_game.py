import pytest

from domain.guess_game import GuessGame
from domain.number_guesser import NumberGuesser
from valueobjects.interval import Interval
from tests.testsupport.fake_secret_number_generator import FakeSecretNumberGenerator
from tests.testsupport.recording_number_guesser import RecordingNumberGuesser
from tests.testsupport.stubborn_number_guesser import StubbornNumberGuesser

FULL_RANGE = Interval(1, 100)
MAX_ATTEMPTS_FOR_FULL_RANGE = 7


def test_produces_correct_game_result_on_success():
    generator = FakeSecretNumberGenerator(42)
    guesser = NumberGuesser()
    game = GuessGame(FULL_RANGE, generator, guesser)

    result = game.play()

    assert result.secret_number == 42
    assert result.success is True
    assert 1 <= result.attempts <= MAX_ATTEMPTS_FOR_FULL_RANGE


def test_every_secret_between_1_and_100_is_found_within_seven_attempts():
    for secret in range(1, 101):
        generator = FakeSecretNumberGenerator(secret)
        guesser = NumberGuesser()
        game = GuessGame(FULL_RANGE, generator, guesser)

        result = game.play()

        assert result.success, f"Secret {secret} was not found"
        assert result.secret_number == secret
        assert result.attempts <= MAX_ATTEMPTS_FOR_FULL_RANGE, (
            f"Secret {secret} took {result.attempts} attempts, "
            f"expected <= {MAX_ATTEMPTS_FOR_FULL_RANGE}"
        )


def test_finds_lower_boundary_secret_within_seven_attempts():
    generator = FakeSecretNumberGenerator(1)
    guesser = NumberGuesser()
    game = GuessGame(FULL_RANGE, generator, guesser)

    result = game.play()

    assert result.success is True
    assert result.secret_number == 1
    assert result.attempts <= MAX_ATTEMPTS_FOR_FULL_RANGE


def test_finds_upper_boundary_secret_within_seven_attempts():
    generator = FakeSecretNumberGenerator(100)
    guesser = NumberGuesser()
    game = GuessGame(FULL_RANGE, generator, guesser)

    result = game.play()

    assert result.success is True
    assert result.secret_number == 100
    assert result.attempts <= MAX_ATTEMPTS_FOR_FULL_RANGE


def test_single_value_interval_succeeds_in_one_attempt():
    single_value = Interval(5, 5)
    generator = FakeSecretNumberGenerator(5)
    guesser = NumberGuesser()
    game = GuessGame(single_value, generator, guesser)

    result = game.play()

    assert result.success is True
    assert result.secret_number == 5
    assert result.attempts == 1


def test_interval_narrows_as_play_progresses():
    generator = FakeSecretNumberGenerator(73)
    recording_guesser = RecordingNumberGuesser()
    game = GuessGame(FULL_RANGE, generator, recording_guesser)

    result = game.play()

    recorded = recording_guesser.recorded_intervals

    assert result.success is True
    assert len(recorded) == result.attempts
    assert recorded[0] == FULL_RANGE

    for interval in recorded:
        assert interval.lower <= 73 <= interval.upper, (
            f"Secret must always remain within the current interval {interval}"
        )

    for i in range(1, len(recorded)):
        previous_width = recorded[i - 1].upper - recorded[i - 1].lower
        current_width = recorded[i].upper - recorded[i].lower
        assert current_width < previous_width, (
            f"Interval did not narrow between attempt {i} and {i + 1}"
        )


def test_gives_up_with_failure_result_when_guesser_never_converges():
    generator = FakeSecretNumberGenerator(50)
    stubborn_guesser = StubbornNumberGuesser(1)
    game = GuessGame(FULL_RANGE, generator, stubborn_guesser)

    result = game.play()

    assert result.success is False
    assert result.secret_number == 50
    assert result.attempts == 100


def test_rejects_none_start_interval():
    generator = FakeSecretNumberGenerator(1)
    guesser = NumberGuesser()

    with pytest.raises(TypeError):
        GuessGame(None, generator, guesser)


def test_rejects_none_generator():
    guesser = NumberGuesser()

    with pytest.raises(TypeError):
        GuessGame(FULL_RANGE, None, guesser)


def test_rejects_none_guesser():
    generator = FakeSecretNumberGenerator(1)

    with pytest.raises(TypeError):
        GuessGame(FULL_RANGE, generator, None)
