# Architecture Requirements

## Value Objects

### Interval

Fields:

- lower
- upper

---

### GameResult

Fields:

- secretNumber
- attempts
- success

---

## Services

### SecretNumberGenerator

Method:

```java
int generate(Interval interval)
```

---

### NumberGuesser

Method:

```java
int nextGuess(Interval interval)
```

Initial strategy:

```java
return interval.lower();
```

Final strategy:

```java
return interval.lower()
    + (interval.upper() - interval.lower()) / 2;
```

---

### GuessGame

Constructor:

```java
GuessGame(
    Interval startInterval,
    SecretNumberGenerator generator,
    NumberGuesser guesser)
```

Method:

```java
GameResult play()
```

---

### RunnerGuessGame

Method:

```java
public static void main(String[] args)
```

---

## Required Production Files

```text
Interval.java
GameResult.java
SecretNumberGenerator.java
NumberGuesser.java
GuessGame.java
RunnerGuessGame.java
```