from domain.number_guesser import NumberGuesser
from valueobjects.interval import Interval


class RecordingNumberGuesser(NumberGuesser):
    """Test spy for NumberGuesser.

    Records every interval it is asked to guess against, then delegates to
    the real NumberGuesser strategy so behaviour stays realistic. Used to
    observe how the interval narrows across a GuessGame play-through.
    """

    def __init__(self) -> None:
        self._recorded_intervals: list[Interval] = []

    def next_guess(self, interval: Interval) -> int:
        self._recorded_intervals.append(interval)
        return super().next_guess(interval)

    @property
    def recorded_intervals(self) -> list[Interval]:
        return list(self._recorded_intervals)
