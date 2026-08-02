from domain.number_guesser import NumberGuesser
from domain.secret_number_generator import SecretNumberGenerator
from valueobjects.game_result import GameResult
from valueobjects.interval import Interval


class GuessGame:
    """Plays a guessing game: repeatedly narrows an Interval until the secret
    number, produced by a SecretNumberGenerator, is found by a NumberGuesser.

    play() always terminates: it gives up and returns a failed GameResult
    once the number of attempts reaches the number of values in the starting
    interval, which bounds even a misbehaving NumberGuesser.
    """

    def __init__(
        self,
        start_interval: Interval,
        generator: SecretNumberGenerator,
        guesser: NumberGuesser,
    ) -> None:
        if start_interval is None:
            raise TypeError("start_interval must not be None")
        if generator is None:
            raise TypeError("generator must not be None")
        if guesser is None:
            raise TypeError("guesser must not be None")
        self._start_interval = start_interval
        self._generator = generator
        self._guesser = guesser

    @staticmethod
    def of(
        start_interval: Interval,
        generator: SecretNumberGenerator,
        guesser: NumberGuesser,
    ) -> "GuessGame":
        return GuessGame(start_interval, generator, guesser)

    def play(self) -> GameResult:
        secret_number = self._generator.generate(self._start_interval)
        current_interval = self._start_interval
        attempts = 0
        max_attempts = self._start_interval.upper - self._start_interval.lower + 1

        while attempts < max_attempts:
            guess = self._guesser.next_guess(current_interval)
            attempts += 1

            if guess == secret_number:
                return GameResult.won(secret_number, attempts)
            if guess < secret_number:
                current_interval = Interval.of(guess + 1, current_interval.upper)
            else:
                current_interval = Interval.of(current_interval.lower, guess - 1)

        return GameResult.lost(secret_number, attempts)
