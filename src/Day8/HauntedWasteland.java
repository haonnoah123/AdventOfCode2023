package Day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class HauntedWasteland {

  public static void main(String[] args) {
    partOne();
    partTwo();
  }

  public static void partTwo() {
    ArrayList<String> input = importFile("Day8Input");
    String instructions = input.get(0);
    ArrayList<Node> nodes = putIntoNodes(input);
    System.out.println(traverseMultipleNodes(nodes, instructions));
  }

  public static long traverseMultipleNodes(ArrayList<Node> nodes, String instructions) {
    ArrayList<Node> starts = findStarts(nodes);
    ArrayList<Integer> results = new ArrayList<>();
    for (Node start : starts) {
      results.add(traverseNodesEndingInZ(nodes, instructions, start));
    }
    long curr = results.get(0);
    for (int i = 1; i < results.size(); i++) {
      curr = lcm(curr, results.get(i));
    }
    return curr;
  }

  public static long gcd(long a, long b) {
    if (b == 0)
      return a;
    return gcd(b, a % b);
  }

  public static long lcm(long a, long b) {
    return (a / gcd(a, b)) * b;
  }

  public static int traverseNodesEndingInZ(ArrayList<Node> nodes, String instructions, Node start) {
    int count = 0;
    Node node = start;
    String n = "";
    for (;;) {
      if (instructions.charAt(count % instructions.length()) == 'L') {
        // left
        n = node.getLeft();
      } else {
        // right
        n = node.getRight();
      }
      node = findNode(nodes, n);
      count++;
      if (n.substring(2).equals("Z")) {
        return count;
      }
    }
  }

  public static ArrayList<Node> findStarts(ArrayList<Node> nodes) {
    ArrayList<Node> starts = new ArrayList<>();
    for (Node n : nodes) {
      if (n.getStart().substring(2).equals("A")) {
        starts.add(n);
      }
    }
    return starts;
  }

  public static void partOne() {
    ArrayList<String> input = importFile("Day8Input");
    String instructions = input.get(0);
    ArrayList<Node> nodes = putIntoNodes(input);
    System.out.println(traverseNodes(nodes, instructions));
  }

  public static int traverseNodes(ArrayList<Node> nodes, String instructions) {
    int count = 0;
    Node node = findStart(nodes);
    String n = "";
    for (;;) {
      if (instructions.charAt(count % instructions.length()) == 'L') {
        // left
        n = node.getLeft();
      } else {
        // right
        n = node.getRight();
      }
      node = findNode(nodes, n);
      count++;
      if (n.equals("ZZZ")) {
        return count;
      }
    }
  }

  public static Node findStart(ArrayList<Node> nodes) {
    for (Node n : nodes) {
      if (n.getStart().equals("AAA")) {
        return n;
      }
    }
    return null;
  }

  public static Node findNode(ArrayList<Node> nodes, String toFind) {
    for (Node n : nodes) {
      if (n.getStart().equals(toFind)) {
        return n;
      }
    }
    return null;
  }

  public static ArrayList<Node> putIntoNodes(ArrayList<String> input) {
    input.remove(0);
    input.remove(0);
    ArrayList<Node> nodes = new ArrayList<>();
    for (String line : input) {
      String start = line.split(" = ")[0];
      String left = line.substring(7, 10);
      String right = line.substring(12, 15);
      nodes.add(new Node(start, left, right));
    }
    return nodes;
  }

  public static ArrayList<String> importFile(String fileName) {
    ArrayList<String> nums = new ArrayList<>();
    try {
      File myObj = new File(fileName);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        nums.add(data);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return nums;
  }

}
