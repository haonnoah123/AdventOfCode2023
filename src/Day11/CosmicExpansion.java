package Day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CosmicExpansion {

  public static void main(String[] args) {
    partOne();
    partTwo();
  }

  public static void partTwo() {
    ArrayList<String> input = importFile("Day11Input");
    ArrayList<Point> points = getNewPoints(input);
    System.out.println(getDistanceBetweenPoints(points));
  }

  public static void partOne() {
    ArrayList<String> input = importFile("Day11Input");
    ArrayList<Point> points = getPoints(input);
    System.out.println(getDistanceBetweenPoints(points));
  }

  public static ArrayList<Point> getNewPoints(ArrayList<String> input) {
    int addX = 1;
    int addY = 1;
    ArrayList<Point> points = new ArrayList<>();
    for (int i = 0; i < input.size(); i++) {
      if (!input.get(i).contains("#")) {
        addY += 999999;
      }
      addX = 1;
      for (int j = 0; j < input.get(i).length(); j++) {
        boolean add = true;
        for (int k = 0; k < input.size(); k++) {
          if (input.get(k).charAt(j) == '#') {
            add = false;
          }
        }
        if (add) {
          addX += 999999;
        }
        if (input.get(i).charAt(j) == '#') {
          points.add(new Point(j + addX, i + addY));
        }
      }
    }
    return points;
  }

  public static long getDistanceBetweenPoints(ArrayList<Point> points) {
    long count = 0;
    for (int i = 0; i < points.size(); i++) {
      for (int j = i + 1; j < points.size(); j++) {
        count += Math.abs(points.get(i).getX() - points.get(j).getX())
            + Math.abs(points.get(i).getY() - points.get(j).getY());
      }
    }
    return count;
  }

  public static ArrayList<Point> getPoints(ArrayList<String> input) {
    int addX = 1;
    int addY = 1;
    ArrayList<Point> points = new ArrayList<>();
    for (int i = 0; i < input.size(); i++) {
      if (!input.get(i).contains("#")) {
        addY++;
      }
      addX = 1;
      for (int j = 0; j < input.get(i).length(); j++) {
        boolean add = true;
        for (int k = 0; k < input.size(); k++) {
          if (input.get(k).charAt(j) == '#') {
            add = false;
          }
        }
        if (add) {
          addX++;
        }
        if (input.get(i).charAt(j) == '#') {
          points.add(new Point(j + addX, i + addY));
        }
      }
    }
    return points;
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
