package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Trebuchet {

  public static void main(String[] args) {
     //partOne();
    String[] digits =
        new String[] {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    ArrayList<String> input = importFile("InputDay1");
    int total = 0;
    for (String line : input) {
      int low = getLtoR(line, digits);
      int high = getRtoL(line, digits);
      int curr = Integer.parseInt(low + "" + high);
      total += curr;
      // System.out.println(line);
      // System.out.println(low + "" + high);
    }
    System.out.println(total);
  }

  public static int getRtoL(String line, String[] digits) {
    for (int i = line.length(); i > 0; i--) {
      try {
        int worstCase = Integer.parseInt(line.substring(i - 1, i));
        String parsedLine = line.substring(i);
        //System.out.println(parsedLine);
        int lowest = getHighestIndexValue(parsedLine, digits);
        // System.out.println(lowest);
        return (lowest == Integer.MAX_VALUE ? worstCase : lowest + 1);
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
    int max = getHighestIndexValue(line, digits);
    return (max == Integer.MAX_VALUE ? 0 : max + 1);
  }

  public static int getHighestIndexValue(String line, String[] digits) {
    int[] indexes = new int[9];
    for (int j = 0; j < digits.length; j++) {
      int indexOfDigit = line.lastIndexOf(digits[j]);
      // indexOfDigit = (indexOfDigit == -1 ? Integer.MAX_VALUE : indexOfDigit);
      // System.out.println(indexOfDigit);
      indexes[j] = indexOfDigit;
    }
    // System.out.println();
    int maxIndex = getIndexOfMax(indexes);
    // System.out.println(minIndex);
    if (maxIndex != Integer.MAX_VALUE) {
      return maxIndex;
    }
    return Integer.MAX_VALUE;
  }

  public static int getIndexOfMax(int[] indexes) {
    int max = Integer.MIN_VALUE;
    int index = Integer.MAX_VALUE;
    for (int i = 0; i < indexes.length; i++) {
      // System.out.println(indexes[i]);
      if (indexes[i] != -1 && indexes[i] > max) {
        max = indexes[i];
        index = i;
      }
    }
    return index;
  }

  public static int getLtoR(String line, String[] digits) {
    for (int i = 0; i < line.length(); i++) {
      try {
        int worstCase = Integer.parseInt(line.substring(i, i + 1));
        String parsedLine = line.substring(0, i);
        int lowest = getLowestIndexValue(parsedLine, digits);
        // System.out.println(lowest);
        return (lowest == Integer.MAX_VALUE ? worstCase : lowest + 1);
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
    int min = getLowestIndexValue(line, digits);
    return (min == Integer.MAX_VALUE ? 0 : min + 1);
  }

  public static int getLowestIndexValue(String line, String[] digits) {
    int[] indexes = new int[9];
    for (int j = 0; j < digits.length; j++) {
      int indexOfDigit = line.indexOf(digits[j]);
      // indexOfDigit = (indexOfDigit == -1 ? Integer.MAX_VALUE : indexOfDigit);
      // System.out.println(indexOfDigit);
      indexes[j] = indexOfDigit;
    }
    // System.out.println();
    int minIndex = getIndexOfMin(indexes);
    // System.out.println(minIndex);
    if (minIndex != Integer.MAX_VALUE) {
      return minIndex;
    }
    return Integer.MAX_VALUE;
  }

  public static int getIndexOfMin(int[] indexes) {
    int min = Integer.MAX_VALUE;
    int index = Integer.MAX_VALUE;
    for (int i = 0; i < indexes.length; i++) {
      // System.out.println(indexes[i]);
      if (indexes[i] != -1 && indexes[i] < min) {
        min = indexes[i];
        index = i;
      }
    }
    // System.out.println(index);
    return index;
  }

  public static void partOne() {
    ArrayList<String> input = importFile("InputDay1");
    int total = 0;
    for (String line : input) {
      total += Integer.parseInt(getFromLtoR(line) + getFromRtoL(line));
    }
    System.out.println(total);
  }

  public static String getFromLtoR(String line) {
    for (int i = 0; i < line.length(); i++) {
      try {
        Integer.parseInt(line.substring(i, i + 1));
        return line.substring(i, i + 1);
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
    return "";
  }

  public static String getFromRtoL(String line) {
    for (int i = line.length(); i > 0; i--) {
      try {
        Integer.parseInt(line.substring(i - 1, i));
        return line.substring(i - 1, i);
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
    return "";
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
