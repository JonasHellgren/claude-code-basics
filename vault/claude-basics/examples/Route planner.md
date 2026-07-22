
A traveler wants to visit five cities, each represented by an (x, y) coordinate. The goal is to find a short route that visits every city exactly once. This simplified Traveling Salesman Problem is solved by three Claude subagents that create, evaluate, and improve the route.

### Agents

| Agent                   | Responsibility                                          |
| ----------------------- | ------------------------------------------------------- |
| **Main Agent**          | Coordinates the workflow and invokes the subagents.     |
| **Route Creator**       | Creates the initial route through the cities.           |
| **Distance Calculator** | Calculates the total route distance.                    |
| **Route Improver**      | Generates a random route and keeps it if it is shorter. |
| **Reporter**            | Creates the final optimization report.                  |
## Concepts introduced

[[Input file]], [[State file]]


## Execution logic

![[route-planner-flow.png]]
