package graph.impl;

import graph.types.DirectedGraph;
import graph.types.Graph;
import graph.types.UndirectedGraph;
import graph.structures.Edge;
import graph.structures.Node;

import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;

public class Prim<T extends Comparable<T>> {

  private final Graph<T> minimumSpanningTree;
  private Map<Node<T>, Node<T>> parent;
  private Integer totalCost;

  public Prim(Graph<T> graph) {
    this.parent = new TreeMap<>();
    this.totalCost = 0;
    this.minimumSpanningTree = prim(graph);
  }

  public Graph<T> calculateMinimumSpanningTree() {
    return this.minimumSpanningTree;
  }

  private Graph<T> prim(Graph<T> graph) {
    Graph<T> prim = graph instanceof DirectedGraph ? new DirectedGraph<>() : new UndirectedGraph<>();
    Set<Node<T>> visitor = new HashSet<>();
    parent.clear();
    PriorityQueue<Edge<T>> priorityQueue = new PriorityQueue<>();

    Node<T> startNode = graph.getAllNodes().iterator().next();
    visitor.add(startNode);
    for (Edge<T> eg : graph.getEgsNode(startNode)) {
      priorityQueue.add(eg);
    }

    while (!priorityQueue.isEmpty()) {
      Edge<T> minEg = priorityQueue.poll();
      Node<T> u = minEg.getStartNode();
      Node<T> v = minEg.getEndNode();

      if (visitor.contains(u) && visitor.contains(v)) {
        continue;
      }

      prim.addEg(minEg.getEdgeWt(), u, v);
      totalCost += minEg.getEdgeWt();
      Node<T> nextNode = visitor.contains(u) ? v : u;
      visitor.add(nextNode);
      parent.put(v, u);
      parent.put(u, u);
      for (Edge<T> edge : graph.getEgsNode(nextNode)) {
        if (!visitor.contains(edge.getEndNode())) {
          priorityQueue.add(edge);
        }
      }
    }
    return prim;
  }

  @Override
  public String toString() {
    return this.minimumSpanningTree.toString() + "Total Quantity: " + this.totalCost;
  }
}
