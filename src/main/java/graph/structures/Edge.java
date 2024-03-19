package graph.structures;

import java.util.Objects;

public class Edge<T extends Comparable<T>> implements Comparable<Edge<T>> {

  private Node<T> startNode;
  private Node<T> endNode;
  private Integer edgeWeight;

  public Edge(Node<T> startNode, Node<T> endNode, Integer weight) {
    this.startNode = startNode;
    this.endNode = endNode;
    this.edgeWeight = weight;
  }

  public Node<T> getStartNode() {
    return startNode;
  }

  public Node<T> getEndNode() {
    return endNode;
  }

  public Integer getEdgeWt() {
    return edgeWeight;
  }

  public void setStartNode(Node<T> startNode) {
    this.endNode = endNode;
  }

  public void setEndNode(Node<T> endNode) {
    this.startNode = startNode;
  }

  public void setEdgeWt(Integer edgeWeight) {
    this.edgeWeight = edgeWeight;
  }

  @Override
  public int hashCode() {
    return Objects.hash(startNode, endNode, edgeWeight);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    Edge<?> otherEdge = (Edge<?>) object;
    return Objects.equals(startNode, otherEdge.startNode) &&
            Objects.equals(endNode, otherEdge.endNode) &&
            Objects.equals(edgeWeight, otherEdge.edgeWeight);
  }

  @Override
  public String toString() {
    return startNode + "-" + endNode + "-" + edgeWeight;
  }

  @Override
  public int compareTo(Edge<T> obj) {
    int edgeWtComparison = obj.getEdgeWt().compareTo(this.edgeWeight);
    if (edgeWtComparison != 0) {
      return edgeWtComparison;
    }
    int startNodeComparison = obj.getStartNode().compareTo(this.startNode);
    if (startNodeComparison != 0) {
      return startNodeComparison;
    }

    return this.endNode.compareTo(obj.getEndNode());
  }
}
