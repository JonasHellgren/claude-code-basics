from dataclasses import dataclass


@dataclass(frozen=True)
class Interval:
    """An inclusive range of integers between lower and upper."""

    lower: int
    upper: int

    def __post_init__(self) -> None:
        if self.lower > self.upper:
            raise ValueError(
                f"lower ({self.lower}) must not be greater than upper ({self.upper})"
            )

    @staticmethod
    def of(lower: int, upper: int) -> "Interval":
        return Interval(lower, upper)
