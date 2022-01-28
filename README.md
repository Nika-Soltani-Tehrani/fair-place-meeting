In this project we want to implement a way to find the best solution for the problem described below:
Imagine we model a city with a graph. Each building in the city is a node of the graph and each
street is as an edge in the graph. Consider that the length of each street is considered as the weight
related to each edge. 
If we have some people in some buildings of the city, we want to find the best place which is fair for
everybody to reach for a meeting.
To solve the problem, we first use DFS (Depth First Search) to find the best solution for traversing
the graph and then use dijkstra algorithm to find the best path. At last by calculating the fair score
for each place we can choose the fairest place for the meeting. 