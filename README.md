# Advent of code - 2024 - Petcu Dragos

Solution:

I just parsed each report/line from the input and checked if it is 
increasing/decreasing for the whole sequence and also checking that the difference between 
the 2 values checked is >= 1 and <= 3.

A better solution would be to create a pool of threads, give lines to the pool and solve
more reports at a time. Maybe I could implement something like this if I have the time. 