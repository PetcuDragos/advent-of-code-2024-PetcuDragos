# Advent of code - 2024 - Petcu Dragos

Solution:

The Map is formed of Peak objects. The Position object represents the hiker. 

We take every trailhead (position that has 0 as value) and we try to construct possible paths
to the highest peaks (position that has 9 value). A little optimization is that I have also added
is that for each Peak we have visitors, which stores the index of every trailhead that 
has passed that peak. In this way we skip recreating some loops in the passing. 

The construction of paths looks like a Mars algorithm. We have a queue where we put every position
that needs to be processed. So from one point in the map we look in all directions and add
its neighbouring positions if those are reachable (diff = 1 in height and nextPosition inside
the map) and not visited to the queue. Then at the next iteration we pop the queue and process again.
The process stops when there are no more positions saved in the queue.

So in the end all 9 peaks should have a list of visitors. What I am doing to get the final
result is that I am parsing again the map, see which is a 9 peak and sum the number of visitors.
 