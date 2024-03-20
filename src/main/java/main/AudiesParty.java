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
    String rlt = "";
    Set<Node<String>> nodes = getNodesAboveWt(minimumWt);

    Iterator<Node<String>> iterator = nodes.iterator();
    while (iterator.hasNext()) {
      Node<String> node = iterator.next();
      rlt += node + " ";
    }
    if (rlt.length() > 0) {
      if (!rlt.isEmpty()) {
        rlt = rlt.substring(0, rlt.length() - 1);
      }
    }
    return rlt;
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

    Map<Node<String>, Node<String>> nds = getNodes();
    Set<Edge<String>> visitedEgs = new HashSet<>();
    boolean relation = true;

    for (Edge<String> eg : this.graph.getAllEgs()) {
      if (groups.size() == groupsQuantity) {
        break;
      }

      Node<String> start = eg.getStartNode();
      Node<String> end = eg.getEndNode();

      if (visitedEgs.contains(eg) || nds.get(start) == nds.get(end)) {
        continue;
      }

      visitedEgs.add(eg);
      visitedEgs.add(new Edge<>(end, start, eg.getEdgeWt()));

      Node<String> ndEnd = nds.get(end);
      List<Node<String>> startGroup = groups.getOrDefault(nds.get(start), new ArrayList<>());

      for (Node<String> nd : groups.getOrDefault(ndEnd, Collections.emptyList())) {
        nds.put(nd, nds.get(start));
        startGroup.add(nd);
      }

      groups.put(nds.get(start), startGroup);
      groups.remove(ndEnd);

      if (relation) {
        this.srg = startGroup;
        relation = false;
      }

      if (groups.size() == groupsQuantity) {
        this.wrg = startGroup;
      }
    }

    return groups;
  }

  public String getGroupsOf(int groupsQuantity) {
    String groupsRlt = "";
    Map<Node<String>, List<Node<String>>> groups = getGroupsOfAlgorithm(groupsQuantity);

    if (groups.size() != groupsQuantity) {
      this.srg = new LinkedList<>();
      this.wrg = new LinkedList<>();
      groupsRlt += "It is not possible";
      return groupsRlt;
    }

    for (Map.Entry<Node<String>, List<Node<String>>> entry : groups.entrySet()) {
      List<Node<String>> nodeList = entry.getValue();
      for (Node<String> node : nodeList) {
        groupsRlt += node + " ";
      }
      if (!groupsRlt.isEmpty()) {
        groupsRlt = groupsRlt.substring(0, groupsRlt.length() - 1);
      }
      groupsRlt += "\n";
    }

    if (!groupsRlt.isEmpty()) {
      groupsRlt = groupsRlt.substring(0, groupsRlt.length() - 1);
    }
    return groupsRlt;
  }

  public String calculateSrg() {
    String srgRelation = "";
    srgRelation += "Group with strongest friendly relationship:";
    if (this.srg.isEmpty()) {
      srgRelation += " None";
      return srgRelation;
    }

    for (Node<String> node : this.srg) {
      srgRelation += " " + node;
    }
    return srgRelation;
  }

  public String calculateWrg() {
    String wrgRelation = "";
    wrgRelation += "Group with least friendly relationship:";

    if (this.wrg.isEmpty()) {
      wrgRelation += " None";
      return wrgRelation;
    }
    for (Node<String> node : this.wrg) {
      wrgRelation += " " + node;
    }
    return wrgRelation;
  }
}
