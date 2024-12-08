# Advent of code - 2024 - Petcu Dragos

Solution explanation

1. We sort both lists of measurements. 
2. Then we parse the first measurement list, starting from index 0.
3. Get the value of this index from first measurement.
4. Find number of occurrences in first list for this value
5. Find number of occurrences in second list for this value
6. Add to the similarityScore the product of [value * result from step 5)] * result from step 4) 
7. Increase the index with the number of occurrences from first list to skip those values.
8. if index < list.size() go to step 3) ,  else go to step 9)
9. Show the similarityScore.