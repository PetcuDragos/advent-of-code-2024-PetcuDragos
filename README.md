# Advent of code - 2024 - Petcu Dragos

Solution:

I have parsed the map as an agent. Every position was saved inside a set.
Whenever I got into a block I changed the direction.
When I got out the map I return the set of positions. The size of the set says how many
steps it took.