from domain.number_guesser import NumberGuesser
from valueobjects.interval import Interval


class StubbornNumberGuesser(NumberGuesser):
    """Broken test double for NumberGuesser.

    Always returns the same fixed guess, ignoring the interval it is given.
    Used to verify that GuessGame cannot be driven into an infinite loop by
    a misbehaving guesser.
    """

    def __init__(self, fixed_guess: int) -> None:
        self._fixed_guess = fixed_guess

    def next_guess(self, interval: Interval) -> int:
        return self._fixed_guess
