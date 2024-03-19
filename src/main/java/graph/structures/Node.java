package graph.structures;

import java.util.Objects;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {

  private T data;

  public Node(T data) {
    this.data = data;
  }

  public T getData() {
    return this.data;
  }

  public void setData(T data) {
    this.data = data;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(data);
  }

  @Override
  public boolean equals(Object object) {
    if (object == null) {
      return false;
    }
    if (this.getClass() != object.getClass()) {
      return false;
    }
    Node<?> obj = (Node<?>) object;
    return this.data.equals(obj.getData());
  }

  @Override
  public String toString() {
    return this.data.toString();
  }

  @Override
  public int compareTo(Node<T> o) {
    return this.data.compareTo(o.getData());
  }
}
