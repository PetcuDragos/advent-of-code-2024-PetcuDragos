# Advent of code - 2024 - Petcu Dragos

Solution:

I have parsed the words as a matrix and I have computed for each
position 
1) The word formed by concatenating the letter in his top left, with the letter from
the current position, with the letter from bottom right. (main diagonal)
2) The word formed by concatenating the letter in his bottom left, with the letter from
the current position, with the letter from top right. (secondary diagonal)

I have verified that these two words are either MAS or SAM, and if true, I increased the counter.
