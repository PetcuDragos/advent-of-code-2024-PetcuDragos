# Advent of code - 2024 - Petcu Dragos

Solution:

I have used regex to find the sequences of text that I need. After that I just 
fetched the values and multiplied them. Added the result to a global sum and returned it.

Maybe regex shouldn't have been involved? But it was the easiest solution. Another solution
could be by parsing the string and trying to construct the result. It would be theta(n) where
n is the number of characters. Maybe if I have the time I will look into it.