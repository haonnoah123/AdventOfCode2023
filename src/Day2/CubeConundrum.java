package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CubeConundrum {

  public static void main(String[] args) {
    part1();
    part2();
  }

  public static void part2() {
    ArrayList<String> input = importFile("Day2Input");
    ArrayList<ArrayList<Game>> eachGameLine = new ArrayList<>();
    for (String line : input) {
      eachGameLine.add(convertLineToGame(line));
    }
    ArrayList<int[]> maxes = findAllMaxes(eachGameLine);
    System.out.println(multiplyAndAddMaxes(maxes));
  }

  public static int multiplyAndAddMaxes(ArrayList<int[]> maxes) {
    int total = 0;
    for (int[] max : maxes) {
      int subtotal = max[0] * max[1] * max[2];
      total += subtotal;
    }
    return total;
  }

  public static ArrayList<int[]> findAllMaxes(ArrayList<ArrayList<Game>> games) {
    ArrayList<ArrayList<Integer>> eachGameColors = putGameInIntArray(games);
    ArrayList<int[]> maxes = new ArrayList<int[]>();
    for (int i = 0; i < eachGameColors.size(); i += 3) {
      ArrayList<Integer> reds = eachGameColors.get(i);
      ArrayList<Integer> greens = eachGameColors.get(i + 1);
      ArrayList<Integer> blues = eachGameColors.get(i + 2);
      maxes.add(new int[] {findMax(reds), findMax(greens), findMax(blues)});
    }
    return maxes;
  }

  public static int findMax(ArrayList<Integer> colors) {
    int max = 1;
    for (int num : colors) {
      if (num > max) {
        max = num;
      }
    }
    return max;
  }

  public static void part1() {
    ArrayList<String> input = importFile("Day2Input");
    ArrayList<ArrayList<Game>> eachGameLine = new ArrayList<>();
    for (String line : input) {
      eachGameLine.add(convertLineToGame(line));
    }
    ArrayList<ArrayList<Integer>> sumOfEachGame = putGameInIntArray(eachGameLine);
    System.out.println(findPossibleGames(sumOfEachGame));
  }

  public static ArrayList<ArrayList<Integer>> putGameInIntArray(ArrayList<ArrayList<Game>> games) {
    ArrayList<ArrayList<Integer>> gameInArray = new ArrayList<>();
    for (int i = 0; i < games.size(); i++) {
      ArrayList<Game> thisLine = games.get(i);
      ArrayList<Integer> reds = new ArrayList<Integer>();
      ArrayList<Integer> greens = new ArrayList<Integer>();
      ArrayList<Integer> blues = new ArrayList<Integer>();
      for (int j = 0; j < thisLine.size(); j++) {
        Game thisGame = thisLine.get(j);
        reds.add(thisGame.getRed());
        greens.add(thisGame.getGreen());
        blues.add(thisGame.getBlue());
      }
      gameInArray.add(reds);
      gameInArray.add(greens);
      gameInArray.add(blues);
    }
    return gameInArray;
  }

  public static int findPossibleGames(ArrayList<ArrayList<Integer>> sumOfEachGame) {
    int[] possible = new int[] {12, 13, 14};
    int sumOfIndexes = 0;
    int index = 1;
    for (int i = 0; i < sumOfEachGame.size(); i += 3) {
      ArrayList<Integer> reds = sumOfEachGame.get(i);
      ArrayList<Integer> greens = sumOfEachGame.get(i + 1);
      ArrayList<Integer> blues = sumOfEachGame.get(i + 2);
      boolean pass = true;
      for (int red : reds) {
        if (red > possible[0]) {
          pass = false;
        }
      }
      for (int green : greens) {
        if (green > possible[1]) {
          pass = false;
        }
      }
      for (int blue : blues) {
        if (blue > possible[2]) {
          pass = false;
        }
      }
      if (pass) {
        sumOfIndexes += index;
      }
      index++;
    }
    return sumOfIndexes;
  }

  public static ArrayList<int[]> addEachGame(ArrayList<ArrayList<Game>> games) {
    ArrayList<int[]> sumOfEachGame = new ArrayList<>();
    for (ArrayList<Game> game : games) {
      int[] sumOfGame = new int[3];
      int sumRed = 0;
      int sumGreen = 0;
      int sumBlue = 0;
      for (Game subgame : game) {
        sumRed += subgame.getRed();
        sumGreen += subgame.getGreen();
        sumBlue += subgame.getBlue();
      }
      sumOfGame = new int[] {sumRed, sumGreen, sumBlue};
      sumOfEachGame.add(sumOfGame);
    }
    return sumOfEachGame;
  }

  public static ArrayList<Game> convertLineToGame(String line) {
    ArrayList<Game> thisLineGames = new ArrayList<>();
    line = line.split(":")[1];
    String[] splitAtColon = line.split(";");
    for (String splitColon : splitAtColon) {
      String[] splitAtComma = splitColon.split(",");
      int red = 0, green = 0, blue = 0;
      for (String splitComma : splitAtComma) {
        int indexOfRed = splitComma.indexOf("red");
        int indexOfGreen = splitComma.indexOf("green");
        int indexOfBlue = splitComma.indexOf("blue");
        if (indexOfRed != -1) {
          red = Integer.parseInt(splitComma.substring(0, indexOfRed).strip());
        }
        if (indexOfGreen != -1) {
          green = Integer.parseInt(splitComma.substring(0, indexOfGreen).strip());
        }
        if (indexOfBlue != -1) {
          blue = Integer.parseInt(splitComma.substring(0, indexOfBlue).strip());
        }
      }
      Game thisGame = new Game(red, green, blue);
      thisLineGames.add(thisGame);
    }
    // System.out.println(splitAtColon[1]);
    return thisLineGames;
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
