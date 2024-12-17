# Advent of code - 2024 - Petcu Dragos

Solution:

A pretty dummy solution, but I am not sure if I can find a better solution right now.

I consider all combinations of A and B button presses taking into consideration that the maximum number
of a button being pressed is 100. 
I loop with a number starting from 0 to 100. This means the number of A button presses. From this assumption
I calculate the number of button presses for the B button. If the sum is right I consider it as a solution and
I compare it to a global minimum. At the end I am just returning the global minimum.