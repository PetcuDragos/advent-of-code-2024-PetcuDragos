# Advent of code - 2024 - Petcu Dragos

Solution:

I came with an uglier solution. 
It involves weights and child-parent relationships.

The idea is to keep weights for each number to know in what order the numbers can come.
A child should have a lower weight than the parent. 

e.g. X|Y

The complexity comes when processing the rules in this case. We parse the rules that we 
are interested in. We see for each rule who is the parent (X) and who is the child (Y) and
we see if we need to change the weights of these pages. If page X has weight wX than page Y
must have at most wX-1. 
1) If Y has more than wX-1 it means that all of its children must change
their weight accordingly following the same principle. (child has wX-1)
2) If Y has less or equal than wX-1 it means that the rule will not have effect on Y or its children.