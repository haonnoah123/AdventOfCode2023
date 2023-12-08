package Day8;

public class Node {

  private String start;
  private String left;
  private String right;

  public Node(String start, String left, String right) {
    this.start = start;
    this.left = left;
    this.right = right;
  }

  public String getStart() {
    return start;
  }

  public void setStart(String start) {
    this.start = start;
  }

  public String getLeft() {
    return left;
  }

  public void setLeft(String left) {
    this.left = left;
  }

  public String getRight() {
    return right;
  }

  public void setRight(String right) {
    this.right = right;
  }

  @Override
  public String toString() {
    return "Node [start=" + start + ", left=" + left + ", right=" + right + "]";
  }

}
