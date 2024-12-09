# Advent of code - 2024 - Petcu Dragos


Solution:

This is indeed a very dummy solution. 
The idea is that whenever I am finding a mismatch on an index, I am verifying 3 things:

The whole sequence without currentValue.
The whole sequence without previousValue.
The whole sequence without nextValue.


There is room for improvement, but technically worst case would be that I parse a report 
3 times to find out if it is safe or not. If I have the time I would try to think of a better 
solution. Probably I could strip the values that I check and not check the whole sequence 3 times.
Also threads can be used to parse the reports separately in parallel.
