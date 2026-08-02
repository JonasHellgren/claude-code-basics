from domain.guess_game import GuessGame
from domain.number_guesser import NumberGuesser
from domain.secret_number_generator import SecretNumberGenerator
from valueobjects.interval import Interval


def main() -> None:
    """Wires up and plays a single GuessGame."""
    start_interval = Interval.of(1, 100)
    generator = SecretNumberGenerator.create()
    guesser = NumberGuesser.create()
    game = GuessGame.of(start_interval, generator, guesser)
    result = game.play()
    print(result)


if __name__ == "__main__":
    main()
