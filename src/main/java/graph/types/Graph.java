package graph.types;

import graph.structures.Edge;
import graph.structures.Node;

import java.util.Objects;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Stream;

public abstract class Graph<T extends Comparable<T>> implements Comparable<Graph<T>> {

  private final Map<Node<T>, Set<Edge<T>>> adjacencySets;
  private Integer egsQuantity = 0;
  private Integer nodesQuantity = 0;

  protected Graph() {
    adjacencySets = new TreeMap<>();
  }

  public Map<Node<T>, Set<Edge<T>>> getAdjacencySets() {
    return this.adjacencySets;
  }

  private void increaseNodesQuantity() {
    this.nodesQuantity++;
  }

  protected void decreaseNodesQuantity() {
    this.nodesQuantity--;
  }

  protected void increaseEgsQuantity() {
    this.egsQuantity++;
  }

  protected void decreaseEgsQuantity() {
    this.egsQuantity--;
  }

  protected void decreaseAnAmountEgsQuantity(int num) {
    this.egsQuantity -= num;
  }

  public Set<Edge<T>> getAllEgs() {
    Set<Edge<T>> allEgs = new TreeSet<>();
    for (Node<T> node : getAllNodes()) {
      Set<Edge<T>> egs = getEgsNode(node);
      allEgs.addAll(egs);
    }
    return allEgs;
  }

  public Set<Node<T>> getAllNodes() {
    return adjacencySets.keySet();
  }

  public boolean containsNode(Node<T> node) {
    return adjacencySets.containsKey(node);
  }

  public Set<Edge<T>> getEgsNode(Node<T> node) {
    return adjacencySets.get(node);
  }

  public boolean addNode(Node<T> newNode) {
    if (newNode == null || this.containsNode(newNode)) {
      return false;
    }
    Set<Edge<T>> newAdjacencySet = new TreeSet<>();
    adjacencySets.put(newNode, newAdjacencySet);
    this.increaseNodesQuantity();
    return true;
  }

  public boolean addNode(T newNode) {
    Node<T> node = new Node<>(newNode);
    if (this.containsNode(node)) {
      return false;
    }
    Set<Edge<T>> newAdjacencySet = new TreeSet<>();
    adjacencySets.put(node, newAdjacencySet);
    this.increaseNodesQuantity();
    return true;
  }

  public Edge<T> getEdge(Integer wt, Node<T> start, Node<T> end) {
    if (!this.containsNode(start)) {
      return null;
    }
    Edge<T> eg = new Edge<>(start, end, wt);
    Set<Edge<T>> startEgs = this.getEgsNode(start);
    boolean containsEg = startEgs.stream().anyMatch(e -> e.equals(eg));
    if (!containsEg) {
      return null;
    }
    return startEgs.stream().filter(e -> e.equals(eg)).findFirst().orElse(null);
  }

  public boolean updateNode(Node<T> node, T newData) {
    try {
      Stream<Node<T>> nodeToUpdate = this.getAllNodes().stream().filter(n -> n.equals(node));
      nodeToUpdate.findFirst().orElseThrow().setData(newData);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  protected boolean addEgFromTo(Integer wt, Node<T> start, Node<T> end) {
    Edge<T> newEg = new Edge<>(start, end, wt);
    Set<Edge<T>> startEgs = this.getEgsNode(start);
    boolean containsEg = startEgs.stream().anyMatch(e -> e.equals(newEg));
    if (containsEg) {
      return true;
    }
    startEgs.add(newEg);
    return false;
  }

  private int dfs(Node<T> node, Set<Node<T>> visited, int connections) {
    visited.add(node);
    for (Edge<T> neighbor : getEgsNode(node)) {
      if (!visited.contains(neighbor.getEndNode())) {
        connections++;
        dfs(neighbor.getEndNode(), visited, connections);
      }
    }
    return connections;
  }

  public int getNumberConnectedNodes() {
    Set<Node<T>> visited = new HashSet<>();
    int nodes = 0;
    for (Node<T> node : getAllNodes()) {
      int tmpNode = dfs(node, visited, 0);
      if (nodes < tmpNode) {
        nodes = tmpNode;
      }
    }
    return nodes;
  }

  public abstract boolean removeNode(Node<T> node);

  public abstract boolean addEg(Integer wt, Node<T> start, Node<T> end);

  public abstract boolean updateEg(Integer wt, Node<T> start, Node<T> end, int newWt);

  public abstract boolean removeEg(Integer wt, Node<T> start, Node<T> end);

  public int getWtSum() {
    return this.getAllEgs().stream().mapToInt(Edge::getEdgeWt).sum();
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.adjacencySets);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Graph<?> object = (Graph<?>) obj;
    return this.getAllEgs().equals(object.getAllEgs());
  }

  @Override
  public int compareTo(Graph<T> graph) {
    int selfSize = this.getNumberConnectedNodes();
    int graphSize = graph.getNumberConnectedNodes();
    if (selfSize == graphSize) {
      return Integer.compare(graph.getWtSum(), this.getWtSum());
    }
    return Integer.compare(graphSize, selfSize);
  }
}
