from dataclasses import dataclass


@dataclass(frozen=True)
class GameResult:
    """The outcome of a single GuessGame play-through."""

    secret_number: int
    attempts: int
    success: bool

    def __post_init__(self) -> None:
        if self.attempts <= 0:
            raise ValueError(f"attempts must be positive, was {self.attempts}")

    @staticmethod
    def won(secret_number: int, attempts: int) -> "GameResult":
        return GameResult(secret_number, attempts, True)

    @staticmethod
    def lost(secret_number: int, attempts: int) -> "GameResult":
        return GameResult(secret_number, attempts, False)
