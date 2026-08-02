from domain.secret_number_generator import SecretNumberGenerator
from valueobjects.interval import Interval


class FakeSecretNumberGenerator(SecretNumberGenerator):
    """Deterministic test double for SecretNumberGenerator.

    Always returns the fixed secret number it was created with, regardless
    of the interval passed in. Used so that GuessGame behaviour can be
    tested without relying on real randomness.
    """

    def __init__(self, fixed_secret: int) -> None:
        super().__init__()
        self._fixed_secret = fixed_secret

    def generate(self, interval: Interval) -> int:
        return self._fixed_secret
