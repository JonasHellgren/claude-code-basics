
## Agents

|Agent|Roll|
|---|---|
|**Main Agent**|Tolkar kommandona `init` och `propose`, startar rätt subagenter och kontrollerar att resultatfilerna skapas.|
|**recipe-reader**|Läser alla recept i `recipes/`, extraherar namn, tid, portioner och ingredienser och skapar `results/recipes-summary.md`.|
|**ingredient-reader**|Läser `ingredients-at-home.txt`, normaliserar ingrediensnamnen och skapar en enkel lista över vad som finns hemma.|
|**recipe-matcher**|Jämför ingredienserna hemma med recepten i `recipes-summary.md`, rangordnar recepten och skapar `results/recommendations.md`.|

## Concepts introduced

[[hook]]
[[Google drive as external folder]]


## Execution logic
![[recipe.png]]