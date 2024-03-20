# Project of Audies Party
#### Author: Jheremy Kevin Tancara Zambrana

***

Deadline: March 20, 2024

## Problem Information:
### Summary:
* She organizes a party, where he invites his neighbors, some villagers get along better with some than with others, for that reason he will have to decide who to invite to make the party a success. 
* Audie has a txt with the names of the villagers, followed by a list of two names and a numerical value representing how well they get along (the higher the value the better they get along).
* Help audie to create his guest list based on how well they get along, the program should generate a list where all the villagers on the list are those who get along with a value greater than x with any other villager on the list.

### Clarifications and Restrictions:
* Audie wants to invite as many guests as possible, so the list should be as large as possible.
* If several answers are found, print the list whose sum of the friendship ratio among all the invitees is the highest.
* After you have the list of guests to invite to your party, you want to know if dividing them into exactly k groups is possible so that villagers who get along very well are together.
* She wants to know the names of the villagers in each of the groups that will be formed. (A villager can only be part of 1 group).
* Print the group with the villager who has the strongest friendly relationship (If it does not exist, print "None"). does not exist print "None")
* If there are several answers, print any of them.
* If this is not possible, print "Not possible".

***

## Instructions
1. Implement the necessary code to solve the problem.
  - [ ] (a) Make sure that you apply greedy algorithms to your solution. : Done
  - [ ] (b) Do not forget to write clean code and follow best practices. : Done
2. Write a brief explanation of why you have chosen the greedy algorithm to solve the problem.
   problem.
  - [ ] The Greedy algorithm that was chosen to solve this problem was to work with DFS (Deep Search) and PRIM (It is a greedy algorithm that finds a minimum spanning tree (AEM) in a connected and weighted graph) because of its ability to identify meaningful connections between villagers and form cohesive groups with guests that get along well with each other, which will contribute to making the party a success.
    Also because the main goal is to maximize the number of guests on the list, making sure that all guests get along well with each other.

    Where the DFS algorithm traverses a graph recursively, first exploring the edges going deep (moving away from the initial vertex) and then backtracking to explore the edges that have not yet been visited and the Prim algorithm is used to find the minimum spanning tree in a weighted graph, where the objective is to minimize the total weight of all edges in the tree and is useful for selecting the most relevant or central villagers for the party and thus by selecting the villagers with the strongest friendship relationships, Prim can help maximize the cohesion within the guest groups and improve the overall atmosphere of the party.

    ### Why is the DFS Algorithm the most optimal algorithm for the problem?
  - It is better to use a DFS algorithm in this problem than a BFS (Breadth-First Search) because the DFS algorithm explores in depth before backtracking, which means that it will follow a branch of the network as far as possible before backtracking and exploring other branches. This can be beneficial for identifying cohesive groups of villagers that are strongly connected to each other. while BFS explores all nodes to a given depth before moving to the next level. This may result in identifying scattered groups at different levels of the network, which may not be optimal for identifying groups of villagers that get along well with each other.

    #### Features of DFS
    * Simple to implement.
    * Useful for finding cycles in networks.
    * Can be used to find the topological order of a graph.

    ### Why the Prim Algorithm is the most optimal for the problem?
  - Before visualizing the benefits of the PRIM algorithm in the problem, remember that the MST (Minimum Spanning Tree) is a subset of edges of a weighted and connected graph that meets some conditions. To find the MST there are 2 common algorithms, one is PRIM and the other Kruskal.
    * Prim: It selects the edges of less weight that do not generate cycles until all the vertices are connected.
    * Kruskal: it orders the edges by weight and selects them from lesser to greater weight as long as they do not form cycles.
    
    This means that for this problem the Prim algorithm selects guests based on the strongest friendship relationships first, which ensures that the groups formed are as cohesive as possible in terms of friendly relationships while Kruskal would select edges based solely on the weight of the connections, which may not take into account the cohesion between guests.
    Prim operates directly on the graph of friendship relations between villagers, which means that it can identify and form cohesive groups of guests more efficiently, as it does not require the additional step of identifying edges and sorting them by weight.

    #### Features of PRIM
    * Efficient, with a time complexity of O(E log V), where E is the number of edges and V the number of vertices.
    * Selection based on friendship relationships.
    * Efficiency in dense networks.

3. Identify the time complexity of your solution.
  - [ ] The time complexity of the solution depends mainly on two aspects:
  #### Class Dfs
    * iterates over all the nodes of the network and performs a depth-first search (DFS) from each node to obtain the largest connected subnetwork, This process is performed once for each node in the network.
      It has a complexity of: O(V*(V+E)), where V is the number of nodes and E is the number of edges.
  #### Class Prim
    * implements the Prim algorithm to find the minimum spanning tree of the given network. It uses a priority queue to select the edges with the minimum weight at each step.
    Constraint:
    - The complexity of this algorithm depends on the implementation of the priority queue used.
      The complexity is O(E log V), where V is the number of nodes and E is the number of edges.

Then the implementation of these algorithms in the AudiesParty class:
- The removeEgsBelowWt method removes edges from the network that have a weight below a certain threshold.
  The complexity is O(E).

- The removeIsolatedNodes method removes isolated nodes from the network.
  The complexity is O(V).

- The getNodesAboveWt method calls the above methods and returns a set of nodes that meet certain criteria.
  The complexity is O(V + E).

- The getGroupsOfAlgorithm method implements an algorithm to split the nodes of the network into a specified number of groups.
    Constraint
    * The complexity depends on the number of edges and nodes in the network.

- The getGroupsOf method calls getGroupsOfAlgorithm.
  The complexity is equal to that of the getGroupsOfAlgorithm method.

- The calculateSrg and calculateWrg methods simply format the output and have no significant complexity.

There are also less significant operations such as:
* Parsing txt data to networks.
* Use of graphs, directed and undirected.

In short the level of complexity provided depends on the degree size (number of nodes and edges) and is mainly O(V*(V+E)) due to the depth-first search and O(E log V) due to the Prim algorithm.

