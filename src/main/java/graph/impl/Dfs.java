package graph.impl;

import graph.types.DirectedGraph;
import graph.types.Graph;
import graph.types.UndirectedGraph;
import graph.structures.Node;
import graph.structures.Edge;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Dfs<T extends Comparable<T>> {

  private final Set<Node<T>> visitors;

  private Graph<T> graph;

  public Dfs(Graph<T> graph) {
    this.graph = graph;
    this.visitors = new HashSet<>();
    this.graph = getMaxGraph();
  }

  public Graph<T> getGraph() {
    return this.graph;
  }

  private Graph<T> getMaxGraph() {
    PriorityQueue<Graph<T>> graphs = new PriorityQueue<>();
    for (Node<T> node : graph.getAllNodes()) {
      Graph<T> graph = dfs(node, this.graph instanceof DirectedGraph ? new DirectedGraph<>() : new UndirectedGraph<>());
      graphs.add(graph);
    }
    return graphs.peek();
  }

  private Graph<T> dfs(Node<T> node, Graph<T> dfsGraph) {
    visitors.add(node);
    for (Edge<T> neighbor : graph.getEgsNode(node)) {
      if (!visitors.contains(neighbor.getEndNode())) {
        dfsGraph.addEg(neighbor.getEdgeWt(), node, neighbor.getEndNode());
        dfs(neighbor.getEndNode(), dfsGraph);
      }
    }
    return dfsGraph;
  }
}
