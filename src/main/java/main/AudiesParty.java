package main;

import graph.types.Graph;
import graph.impl.Dfs;
import graph.structures.Edge;
import graph.structures.Node;
import graph.impl.Prim;
import graph.parser.GraphLoader;

import java.util.*;

public class AudiesParty {

  private List<Node<String>> srg; // strongestRelationshipGroup
  private List<Node<String>> wrg; // weakestRelationshipGroup
  private Graph<String> graph;

  public AudiesParty(String path) {
    this.srg = new LinkedList<>();
    this.wrg = new LinkedList<>();
    this.graph = new Prim<>(new GraphLoader(path).graphLoader()).calculateMinimumSpanningTree();
  }

  private void removeEgsBelowWt(int maxWtToRemove) {
    Set<Edge<String>> allEgs = this.graph.getAllEgs();
    for (Edge eg : allEgs) {
      if (eg.getEdgeWt() <= maxWtToRemove) {
        this.graph.removeEg(eg.getEdgeWt(), eg.getStartNode(), eg.getEndNode());
      }
    }
  }

  private void removeIsolatedNodes() {
    Set<Node<String>> nodesToRemove = new HashSet<>();
    for (Node<String> node : this.graph.getAllNodes()) {
      if (this.graph.getEgsNode(node).isEmpty()) {
        nodesToRemove.add(node);
      }
    }
    this.graph.getAllNodes().removeAll(nodesToRemove);
    this.graph = new Dfs<>(this.graph).getGraph();
  }

  private Set<Node<String>> getNodesAboveWt(int minimumEgWt) {
    removeEgsBelowWt(minimumEgWt);
    removeIsolatedNodes();
    return this.graph.getAllNodes();
  }

  public String getNodesAboveWtString(int minimumWt) {
    Set<Node<String>> nodes = getNodesAboveWt(minimumWt);
    StringBuilder result = new StringBuilder();
    //result.append("Guests:\n");

    Iterator<Node<String>> iterator = nodes.iterator();
    while (iterator.hasNext()) {
      Node<String> node = iterator.next();
      result.append(node).append(" ");
    }
    if (result.length() > 0) {
      result.deleteCharAt(result.length() - 1);
    }
    return result.toString();
  }

  private Map<Node<String>, List<Node<String>>> initializeGroupsByNode() {
    Map<Node<String>, List<Node<String>>> groups = new HashMap<>();
    Set<Node<String>> allNodes = this.graph.getAllNodes();

    for (Node<String> node : allNodes) {
      List<Node<String>> group = new LinkedList<>();
      group.add(node);
      groups.put(node, group);
    }
    return groups;
  }

  private Map<Node<String>, Node<String>> getNodes() {
    Map<Node<String>, Node<String>> nodes = new HashMap<>();
    Set<Node<String>> allNodes = this.graph.getAllNodes();

    for (Node<String> node : allNodes) {
      nodes.put(node, node);
    }
    return nodes;
  }

  private Map<Node<String>, List<Node<String>>> getGroupsOfAlgorithm(int groupsQuantity) {
    Map<Node<String>, List<Node<String>>> groups = initializeGroupsByNode();
    if (groups.size() == groupsQuantity) {
      return groups;
    }
    Map<Node<String>, Node<String>> nodes = getNodes();
    Set<Edge<String>> visitedEdges = new HashSet<>();
    boolean firstRelation = true;
    for (Edge<String> edge : this.graph.getAllEgs()) {
      Node<String> source = edge.getStartNode();
      Node<String> destination = edge.getEndNode();
      if (visitedEdges.contains(edge) || nodes.get(source) == nodes.get(destination)) {
        continue;
      }
      Edge<String> imageEdge = new Edge<>(edge.getEndNode(), edge.getStartNode(), edge.getEdgeWt());
      visitedEdges.add(edge);
      visitedEdges.add(imageEdge);
      Node<String> prevDestination = nodes.get(destination);
      for (Node<String> node : groups.get(prevDestination)) {
        nodes.put(node, nodes.get(source));
        groups.get(nodes.get(source)).add(node);
      }
      groups.remove(prevDestination);
      if (firstRelation) {
        this.srg = groups.get(nodes.get(source));
        firstRelation = false;
      }
      if (groups.size() == groupsQuantity) {
        this.wrg = groups.get(nodes.get(source));
        break;
      }
    }
    return groups;
  }

  public String getGroupsOf(int groupsQuantity) {
    StringBuilder result = new StringBuilder();
    Map<Node<String>, List<Node<String>>> groups = getGroupsOfAlgorithm(groupsQuantity);

    if (groups.size() != groupsQuantity) {
      this.srg = new LinkedList<>();
      this.wrg = new LinkedList<>();
      return result.append("It is not possible").toString();
    }

    for (Map.Entry<Node<String>, List<Node<String>>> entry : groups.entrySet()) {
      List<Node<String>> nodeList = entry.getValue();
      for (Node<String> node : nodeList) {
        result.append(node).append(" ");
      }
      result.deleteCharAt(result.length() - 1);
      result.append("\n");
    }

    result.deleteCharAt(result.length() - 1);
    return result.toString();
  }

  public String getSrg() {
    StringBuilder result = new StringBuilder();
    result.append("Group with strongest friendly relationship:");

    if (this.srg.isEmpty()) {
      return result.append(" None").toString();
    }

    for (Node<String> node : this.srg) {
      result.append(" ").append(node);
    }

    return result.toString();
  }

  public String getWrg() {
    StringBuilder result = new StringBuilder();
    result.append("Group with least friendly relationship: ");
    if (this.wrg.isEmpty()) {
      return result.append("None").toString();
    }
    for (Node<String> node : this.wrg) {
      result.append(node).append(" ");
    }
    result.deleteCharAt(result.length() - 1);
    return result.toString();
  }
}
