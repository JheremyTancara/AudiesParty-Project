package graph.types;

import graph.structures.Edge;
import graph.structures.Node;

import java.util.Set;

public class DirectedGraph<T extends Comparable<T>> extends Graph<T> {

  public DirectedGraph() {
    super();
  }

  @Override
  public boolean removeNode(Node<T> targetNode) {
    if (!super.containsNode(targetNode)) {
      return false;
    }

    int numEgsToRemove = 0;

    for (Node<T> startNode : super.getAllNodes()) {
      if (startNode.equals(targetNode)) {
        continue;
      }
      Set<Edge<T>> egs = super.getEgsNode(startNode);
      numEgsToRemove += egs.removeIf(edge -> edge.getEndNode().equals(targetNode)) ? 1 : 0;
    }

    numEgsToRemove += super.getEgsNode(targetNode).size();
    super.getAdjacencySets().remove(targetNode);

    super.decreaseAnAmountEgsQuantity(numEgsToRemove);
    super.decreaseNodesQuantity();

    return true;
  }

  @Override
  public boolean addEg(Integer wt, Node<T> start, Node<T> end) {
    super.addNode(start);
    super.addNode(end);

    if (super.addEgFromTo(wt, start, end)) {
      super.increaseEgsQuantity();
      return true;
    }
    return false;
  }

  @Override
  public boolean removeEg(Integer wt, Node<T> start, Node<T> end) {
    Edge<T> egToRemove = new Edge<>(start, end, wt);
    for (Node<T> node : super.getAllNodes()) {
      Set<Edge<T>> egs = super.getEgsNode(node);
      if (egs.removeIf(eg -> eg.equals(egToRemove))) {
        super.decreaseEgsQuantity();
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean updateEg(Integer wt, Node<T> start, Node<T> end, int newWt) {
    Edge<T> egToUpdate = new Edge<>(start, end, wt);
    for (Node<T> node : super.getAllNodes()) {
      Set<Edge<T>> egs = super.getEgsNode(node);
      for (Edge<T> eg : egs) {
        if (eg.equals(egToUpdate)) {
          eg.setEdgeWt(newWt);
          return true;
        }
      }
    }
    return false;
  }
}
