# Advent of code - 2024 - Petcu Dragos

Day1Problem1

Solution idea. 

Sort each list O(n*logn) then parse the sorted lists simultaneously and compute the 
difference. O(n)

A better solution would be to compute the difference using threads, 
but I do not think that the time complexity in practical solution would be better since 
the creation of threads is expensive. Also, it induces memory complexity.