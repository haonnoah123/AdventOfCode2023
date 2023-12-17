package Day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MirageMaintenance {

  public static void main(String[] args) {
    partOne();
    partTwo();
  }

  public static void partTwo() {
    ArrayList<String> input = importFile("Day9Input");
    ArrayList<ArrayList<Integer>> initialSequences = getInitialSequences(input);
    System.out.println(getValueOfAllHistoriesBackwards(initialSequences));
  }

  public static int getValueOfAllHistoriesBackwards(
      ArrayList<ArrayList<Integer>> initialSequences) {
    int count = 0;
    for (ArrayList<Integer> sequence : initialSequences) {
      count += getValueOfHistoryBackwards(sequence);
    }
    return count;
  }

  public static int getValueOfHistoryBackwards(ArrayList<Integer> sequence) {
    ArrayList<ArrayList<Integer>> differences = new ArrayList<>();
    differences.add(sequence);
    while (!checkIsAllZero(differences.get(differences.size() - 1))) {
      differences.add(getDifferences(differences.get(differences.size() - 1)));
    }
    differences.get(differences.size() - 1).add(0, 0);
    for (int i = differences.size() - 1; i > 0; i--) {
      ArrayList<Integer> thisDifference = differences.get(i);
      ArrayList<Integer> nextDifference = differences.get(i - 1);
      int sub = nextDifference.get(0) - thisDifference.get(0);
      differences.get(i - 1).add(0, sub);
    }
    return differences.get(0).get(0);
  }

  public static void partOne() {
    ArrayList<String> input = importFile("Day9Input");
    ArrayList<ArrayList<Integer>> initialSequences = getInitialSequences(input);
    System.out.println(getValueOfAllHistories(initialSequences));
  }

  public static int getValueOfAllHistories(ArrayList<ArrayList<Integer>> initialSequences) {
    int count = 0;
    for (ArrayList<Integer> sequence : initialSequences) {
      count += getValueOfHistory(sequence);
    }
    return count;
  }

  public static int getValueOfHistory(ArrayList<Integer> sequence) {
    ArrayList<ArrayList<Integer>> differences = new ArrayList<>();
    differences.add(sequence);
    while (!checkIsAllZero(differences.get(differences.size() - 1))) {
      differences.add(getDifferences(differences.get(differences.size() - 1)));
    }
    differences.get(differences.size() - 1).add(0);
    for (int i = differences.size() - 2; i > 0; i--) {
      ArrayList<Integer> thisDifference = differences.get(i);
      ArrayList<Integer> nextDifference = differences.get(i - 1);
      int sum = thisDifference.get(thisDifference.size() - 1)
          + nextDifference.get(nextDifference.size() - 1);
      differences.get(i - 1).add(sum);
    }
    return differences.get(0).get(sequence.size() - 1);
  }

  public static boolean checkIsAllZero(ArrayList<Integer> differences) {
    for (int difference : differences) {
      if (difference != 0) {
        return false;
      }
    }
    return true;
  }

  public static ArrayList<Integer> getDifferences(ArrayList<Integer> sequence) {
    ArrayList<Integer> differences = new ArrayList<>();
    for (int i = 0; i < sequence.size() - 1; i++) {
      differences.add(sequence.get(i + 1) - sequence.get(i));
    }
    return differences;
  }

  public static ArrayList<ArrayList<Integer>> getInitialSequences(ArrayList<String> input) {
    ArrayList<ArrayList<Integer>> sequences = new ArrayList<>();
    for (String line : input) {
      ArrayList<Integer> sequence = new ArrayList<>();
      String[] splited = line.split(" ");
      for (String split : splited) {
        sequence.add(Integer.parseInt(split));
      }
      sequences.add(sequence);
    }
    return sequences;
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
