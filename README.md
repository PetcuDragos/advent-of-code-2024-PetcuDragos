# Advent of code - 2024 - Petcu Dragos

Solution:

The solution here is to process directly on the disk map.

We know that there is always a memory block followed by a free space block.
The idea of the algorithm is to construct the compact file system directly from this map.

The compact file system is built from the disk map following these rules:
1) If current position in disk map in fully processed (is 0) then we go to next position.
2) If current position in disk map is a memory block we just add its fileSystemId to the
compact file system. After this we decrement the number of files from the memory block.
3) If current position in disk map is a free space block we find the furthest memory block
that is not empty. Then we add the fileSystemId of this file. We decrement the number of 
files from the memory block and we also decrement the number of free spaces from the free
space block. In other words, we compacted a free space. 

We should eventually get to all memory spaces being fully processed. Then we just
compute the checksum with the help of the file system. We multiply the index with the value
from the file system. 


Note:

I have created un ugly version and a nice version. I think improvements can be made to the
nice version in case of performance, but it will come with a cost of readability/understandability.