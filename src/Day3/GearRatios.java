package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GearRatios {

  public static void main(String[] args) {
    ArrayList<String> input = importFile("Day3Input");
    String[][] inputInArr = putInputIn2DArray(input);
    inputInArr = surroundWithPeriod(inputInArr);
    // System.out.println(twoDArrayToString(inputInArr));
    Gear[][] gearArray = convertStringArraytoGear(inputInArr);
    System.out.println(getWhereTouchingStar(inputInArr, gearArray));
    partOne();
  }

  public static long getWhereTouchingStar(String[][] input, Gear[][] gears) {
    int previousNum = 0;
    long count = 0;
    for (int i = 0; i < input.length; i++) {
      for (int j = 0; j < input[i].length; j++) {
        if (!checkIfDigit(input[i][j])) {
          previousNum = 0;
        }
        count += checkIfTouchingStar(i, j, input, gears);
      }
    }
    return count;
  }

  public static int checkIfTouchingStar(int i, int j, String[][] input, Gear[][] gears) {
    String[] getAround = new String[8];
    Gear[] getGearsAround = new Gear[8];
    if (input[i][j].equals("*")) {
      getAround =
          new String[] {input[i + 1][j + 1], input[i + 1][j], input[i][j + 1], input[i][j - 1],
              input[i - 1][j - 1], input[i - 1][j], input[i - 1][j + 1], input[i + 1][j - 1]};
      getGearsAround =
          new Gear[] {gears[i + 1][j + 1], gears[i + 1][j], gears[i][j + 1], gears[i][j - 1],
              gears[i - 1][j - 1], gears[i - 1][j], gears[i - 1][j + 1], gears[i + 1][j - 1]};
    } else {
      return 0;
    }
    ArrayList<Integer> added = new ArrayList<>();
    for(int a = 0; a < getAround.length; a++) {
      if(checkIfDigit(getAround[a]) && !added.contains(getGearsAround[a].getNumber())) {
        added.add(getGearsAround[a].getNumber());
      }
    }
    //System.out.println(added);
    if(added.size() == 2) {
      return added.get(0) * added.get(1);
    }
    return 0;
  }

  public static void partOne() {
    ArrayList<String> input = importFile("Day3Input");
    String[][] inputInArr = putInputIn2DArray(input);
    inputInArr = surroundWithPeriod(inputInArr);
    // System.out.println(twoDArrayToString(inputInArr));
    Gear[][] gearArray = convertStringArraytoGear(inputInArr);
    System.out.println(getWhereTouching(inputInArr, gearArray));
  }

  public static int getWhereTouching(String[][] input, Gear[][] gears) {
    int previousNum = 0;
    int count = 0;
    for (int i = 0; i < input.length; i++) {
      for (int j = 0; j < input[i].length; j++) {
        if (!checkIfDigit(input[i][j])) {
          previousNum = 0;
        }
        if (checkIfTouchingAtIndexes(i, j, input) && gears[i][j].getNumber() != previousNum) {
          // System.out.println(input[i][j] + " " + gears[i][j].getNumber());
          count += gears[i][j].getNumber();
          previousNum = gears[i][j].getNumber();
        }
      }
    }
    return count;
  }

  public static Gear[][] convertStringArraytoGear(String[][] input) {
    Gear[][] output = new Gear[input.length][input[0].length];
    for (int i = 0; i < input.length; i++) {
      for (int j = 0; j < input[i].length; j++) {
        output[i][j] = findGear(input, i, j);
      }
    }
    return output;
  }

  public static int addAllNums(Gear[][] input) {
    int count = 0;
    int lastAdded = 0;
    for (int i = 0; i < input.length; i++) {
      for (int j = 0; j < input[i].length; j++) {
        if (lastAdded != input[i][j].getNumber()) {
          count += input[i][j].getNumber();
          lastAdded = input[i][j].getNumber();
        }
      }
    }
    return count;
  }

  public static boolean checkIfDigit(String str) {
    try {
      Integer.parseInt(str.trim());
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public static boolean checkIfTouchingAtIndexes(int i, int j, String[][] input) {
    String[] getAround = new String[8];
    try {
      Integer.parseInt(input[i][j]);
      getAround =
          new String[] {input[i + 1][j + 1], input[i + 1][j], input[i][j + 1], input[i][j - 1],
              input[i - 1][j - 1], input[i - 1][j], input[i - 1][j + 1], input[i + 1][j - 1]};
    } catch (Exception e) {
      return false;
    }
    for (String str : getAround) {
      if (!checkIfDigit(str) && !str.equals(".")) {
        // System.out.println(str);
        return true;
      }
    }
    return false;
  }

  public static String[][] surroundWithPeriod(String[][] input) {
    String[][] output = new String[input.length + 2][input[0].length + 2];
    for (int i = 0; i < input.length + 2; i++) {
      for (int j = 0; j < input[0].length + 2; j++) {
        if (i == 0 || j == 0 || i == input.length + 1 || j == input[0].length + 1) {
          output[i][j] = ".";
        } else {
          output[i][j] = input[i - 1][j - 1];
        }
      }
    }
    return output;
  }

  public static Gear findGear(String[][] input, int i, int j) {
    String[] line = input[i];
    String character = input[i][j];
    String num = "";
    for (int k = j - 1; k > 0; k--) {
      try {
        int a = Integer.parseInt(line[k]);
        num = a + "" + num;
      } catch (Exception e) {
        break;
      }
    }
    for (int k = j; k < line.length; k++) {
      try {
        int a = Integer.parseInt(line[k]);
        num += ("" + a);
      } catch (Exception e) {
        break;
      }
    }
    int number = 0;
    try {
      int a = Integer.parseInt(num);
      number = a;
    } catch (Exception e) {

    }
    return new Gear(character, number);
  }

  public static String[][] putInputIn2DArray(ArrayList<String> input) {
    String[][] output = new String[input.get(0).length()][input.size()];
    int index = 0;
    for (String line : input) {
      for (int i = 0; i < line.length(); i++) {
        output[index][i] = line.substring(i, i + 1);
      }
      index++;
    }
    return output;
  }

  public static String twoDArrayToString(String[][] array) {
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[i].length; j++) {
        result.append(array[i][j]);
        if (j < array[i].length - 1) {
          // Add a separator (e.g., comma) between elements in the same row
          result.append("");
        }
      }
      if (i < array.length - 1) {
        // Add a newline between rows
        result.append("\n");
      }
    }

    return result.toString();
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
