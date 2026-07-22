# Coordinate Route Optimization

Four Claude subagents collaborate to find a short route through a set of coordinates.

## Subagents

- **Route Creator** – Creates the initial route.
- **Distance Calculator** – Calculates the total route distance.
- **Route Improver** – Generates a random route and keeps it if it is shorter.
- **Reporter** – Creates the final optimization report.

## Workflow

When the user writes:

**Start optimization**

1. Invoke `route-creator`.
2. Invoke `distance-calculator`.
3. Create an empty `history.txt`.

Repeat the following 10 times:

4. Invoke `route-improver`.
5. Invoke `distance-calculator`.
6. Append the current route and distance to `history.txt`.

After the final iteration:

7. Invoke `reporter`.
8. Display `report.txt`.

## File Permissions

The following files may be created or must be overwritten without asking:

- route.txt
- distance.txt
- history.txt
- report.txt

Never modify:

- coordinates.txt