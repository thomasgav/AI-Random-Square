# AI Random Square #
 UCS, IDS and A* search algorithms used to find the optimal route in a random grid.

## Description ##
This program constructs a grid (n x n) consisted of the Junctions and their Connections. Then, it removes randomly a number of those Connections. Finally, it gives each Connection a cost price, which is a random number between 10 and 50.

The user has the input of 2 main parameters during the operation of the program:
* The number n, which is the number of the rows and columns of the grid
* The percentage p, of the Connections which will be taken out of the grid.

(The program also asks the user to give a Node limit for the search trees)

The final grid is created. Two Junctions are randomly picked to be the Start and the Goal Junctions of the route. This program will try to find the optimal route from the Start to the Goal, using 3 search algorithms:
- UCS
- IDS
- A* search (the heuristic function used will be the Manhattan Distance).

At the start, the program prints the Junctions and the Connections that exist in the grid. Then, for each algorithm, it shows the process made to reach the Goal. At the end it prints the final results, which are the final route, the cost of the route and the number of nodes created in the search tree. 
