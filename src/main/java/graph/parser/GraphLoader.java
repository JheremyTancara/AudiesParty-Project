package graph.parser;

import receiving.file.data.text.ReadText;
import graph.types.Graph;
import graph.types.UndirectedGraph;
import graph.structures.Node;
import java.util.HashSet;
import java.util.Set;

public class GraphLoader {

  private final Set<String> neighbors;
  private final Set<String> friendlyRelationships;

  public GraphLoader(String fileName) {
    String fileContent = ReadText.readText(fileName);
    this.neighbors = new HashSet<>();
    this.friendlyRelationships = new HashSet<>();
    this.parseFileContent(fileContent);
  }

  public Graph<String> graphLoader() {
    Graph<String> graph = new UndirectedGraph<>();
    for (String neighbor : neighbors) {
      graph.addNode(neighbor);
    }
    for (String friendlyRelationship : friendlyRelationships) {
      String[] friendlyRelation = friendlyRelationship.split(" ");
      graph.addEg(Integer.valueOf(friendlyRelation[2]),
              new Node<>(friendlyRelation[0]),
              new Node<>(friendlyRelation[1]));
    }
    return graph;
  }

  private void parseFileContent(String fileContent) {
    String[] lines = fileContent.split("\n");
    for (String line : lines) {
      String[] split = line.split(" ");
      if (split.length == 1) {
        neighbors.add(split[0]);
      } else {
        friendlyRelationships.add(line);
      }
    }
  }
}
