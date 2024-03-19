package graph.types;

import graph.structures.Edge;
import graph.structures.Node;
import java.util.Set;

public class UndirectedGraph<T extends Comparable<T>> extends Graph<T> {

  public UndirectedGraph() {
    super();
  }

  @Override
  public boolean removeNode(Node<T> node) {
    if (!super.containsNode(node)) {
      return false;
    }
    int numEgsToRemove = 0;
    for (Node<T> startNode : super.getAllNodes()) {
      if (!startNode.equals(node)) {
        continue;
      }
      Set<Edge<T>> egs = this.getEgsNode(startNode);
      numEgsToRemove = egs.size();
      for (Edge<T> eg : egs) {
        Integer wt = eg.getEdgeWt();
        Node<T> endNode = eg.getEndNode();
        Edge<T> oppositeEg = new Edge<>(endNode, startNode, wt);
        super.getEgsNode(endNode).remove(oppositeEg);
        super.getAdjacencySets().get(endNode).remove(oppositeEg);
      }
    }
    super.getAdjacencySets().remove(node);
    super.decreaseAnAmountEgsQuantity(numEgsToRemove);
    super.decreaseNodesQuantity();
    return true;
  }

  @Override
  public boolean addEg(Integer wt, Node<T> start, Node<T> end) {
    addNode(start);
    addNode(end);
    if (addEgFromTo(wt, start, end) || addEgFromTo(wt, end, start)) {
      return false;
    }
    super.increaseEgsQuantity();
    return true;
  }

  @Override
  public boolean removeEg(Integer wt, Node<T> start, Node<T> end) {
    Edge<T> eg1 = new Edge<>(start, end, wt);
    Edge<T> eg2 = new Edge<>(end, start, wt);
    boolean rlt1 = false;
    boolean rlt2 = false;
    for (Node<T> node : super.getAllNodes()) {
      Set<Edge<T>> egs = super.getEgsNode(node);
      boolean containsEg1 = egs.stream().anyMatch(e -> e.equals(eg1));
      if (!rlt1 && containsEg1) {
        rlt1 = egs.removeIf(e -> e.equals(eg1));
      }
      boolean containsEg2 = egs.stream().anyMatch(e -> e.equals(eg2));
      if (!rlt2 && containsEg2) {
        rlt2 = egs.removeIf(e -> e.equals(eg2));
      }
      if (rlt1 && rlt2) {
        super.decreaseEgsQuantity();
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean updateEg(Integer wt, Node<T> start, Node<T> end, int newWt) {
    Edge<T> eg1 = new Edge<>(start, end, wt);
    Edge<T> eg2 = new Edge<>(end, start, wt);
    boolean rlt1 = false;
    boolean rlt2 = false;
    for (Node<T> currentNode : super.getAllNodes()) {
      Set<Edge<T>> egs = super.getEgsNode(currentNode);
      boolean containsEg1 = egs.stream().anyMatch(e -> e.equals(eg1));
      if (!rlt1 && containsEg1) {
        egs.stream().filter(e -> e.equals(eg1)).findFirst().orElseThrow().setEdgeWt(newWt);
        rlt1 = true;
      }
      boolean containsEg2 = egs.stream().anyMatch(e -> e.equals(eg2));
      if (!rlt2 && containsEg2) {
        egs.stream().filter(e -> e.equals(eg2)).findFirst().orElseThrow().setEdgeWt(newWt);
        rlt2 = true;
      }
      if (rlt1 && rlt2) {
        return true;
      }
    }
    return false;
  }
}
