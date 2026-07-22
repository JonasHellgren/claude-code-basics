
The Bike Classifier determines whether a motorcycle belongs to licence class `A1`, `A2`, or `A`.  
The main agent accepts the commands `start` and `test`.  
With `start`, it reads one motorcycle from `files/bike.md` and applies the rules in `files/rules.md`.  
The classification is based on engine volume, engine power, weight, and power-to-weight ratio.  
The result is saved in `results/classification.md`.  
With `test`, the system reads several motorcycles from `files/eval-bikes.md`.  
Each predicted class is compared with the expected class.  
The test summary is saved in `results/test-results.md`.

## Agents

|Agent|Description|
|---|---|
|Main agent|Receives `start` or `test` and coordinates the selected workflow.|
|`bike-classifier`|Reads one motorcycle, calculates the power-to-weight ratio, and assigns class `A1`, `A2`, or `A`.|
|`classifier-tester`|Classifies all evaluation motorcycles and compares the predictions with their expected classes.|
## Concepts introduced

[[Prompt argument]]
[[Rule evaluation]]

## Execution logic

