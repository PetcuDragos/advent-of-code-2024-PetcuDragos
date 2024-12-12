# Advent of code - 2024 - Petcu Dragos

Solution:

I have used an OOP oriented solution. ;d

I changed the idea of the problem. Starting from the disk map, for each digit
I construct a diskBlock which stores how many files and free spaces are. At first it is 
obvious that some block only have files and some block only have free spaces. But a block
with only free space can in future have files stored, but also have some free spaces left.

With this idea in mind the algorithm is pretty simple. We start parsing each block. If we
can fit the files from another block (the furthest one) inside this block then we add 
the new files and remove the files from the block where they were taken from. The algorithm 
ends when we have parsed each block.

Then it will result a list of blocks with files and free spaces. This will be exactly the
file system representation. 
