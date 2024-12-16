# Advent of code - 2024 - Petcu Dragos

Solution:

Firstly I get all the regions. A region is formed of a list of Positions. A position has rowIndex,
columnIndex, plantType, a list of direction which shows in what direction fences are needed and
a boolean which shows if it was processed as part of a region. 

To build the regions we start with an unprocessed position from the map. We set this position
as processed and then we verify its 
neighbouring positions. If a neighbour is from a different region (or outside the map) it means 
that we need to add a new direction for a fence in the direction of the neighbour for the current 
position. If a neighbour is from the same region it 
means that we need to add this neighbour to the same region object and parse this one as well. 
We insert it into a queue and it will wait for its turn to be processed. A region is fully built 
when the queue is empty. To build all the regions we must parse every unprocessed position from the
map.

The area of a region is the number of positions from a region. 

To calculate the perimeter we take every position of the region and verify if its neighbours from
direction UP and LEFT. If these share the same plantType as current position. (are in the same 
region) then we check if they have fences in the same perpendicular direction and if yes 
we decrease the number of fences for the current position.
The final perimeter of a region is the sum of the remaining fences that are needed.