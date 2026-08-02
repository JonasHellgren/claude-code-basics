from valueobjects.interval import Interval


class NumberGuesser:
    """Produces the next guess for a GuessGame.

    Guesses the midpoint of the interval (binary search).
    """

    @staticmethod
    def create() -> "NumberGuesser":
        return NumberGuesser()

    def next_guess(self, interval: Interval) -> int:
        return interval.lower + (interval.upper - interval.lower) // 2
