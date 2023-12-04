package Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Scratchcards {

  public static void main(String[] args) {
    partOne();
    partTwo();
  }

  public static void partTwo() {
    ArrayList<String> input = importFile("Day4Input");
    ArrayList<Card> cards = getCards(input);
    getAmountOfCards(cards);
    System.out.println(addAllQuantities(cards));
  }

  public static int addAllQuantities(ArrayList<Card> cards) {
    int count = 0;
    for (Card c : cards) {
      count += c.getQuantity();
    }
    return count;
  }

  public static ArrayList<Card> getAmountOfCards(ArrayList<Card> cards) {
    ArrayList<Integer> contains = findHowManyContain(cards);
    cards.get(0).setQuantity(1);
    for (int i = 0; i < cards.size(); i++) {
      int amount = contains.get(i);
      int amountOfThisCard = cards.get(i).getQuantity();
      for (int j = 0; j < amountOfThisCard; j++) {
        for (int k = 1; k <= amount; k++) {
          cards.get(i + k).setQuantity(cards.get(i + k).getQuantity() + 1);
        }
      }
    }
    return cards;
  }

  public static void partOne() {
    ArrayList<String> input = importFile("Day4Input");
    ArrayList<Card> cards = getCards(input);
    ArrayList<Integer> contains = findHowManyContain(cards);
    System.out.println(addAndFactorialContains(contains));
  }

  public static long addAndFactorialContains(ArrayList<Integer> contains) {
    long count = 0;
    for (int num : contains) {
      if (num != 0) {
        count += formula(num);
      }
    }
    return count;
  }

  public static long formula(int num) {
    int count = 1;
    for (int i = 1; i <= num - 1; i++) {
      count *= 2;
    }
    return count;
  }

  public static ArrayList<Integer> findHowManyContain(ArrayList<Card> cards) {
    ArrayList<Integer> contain = new ArrayList<>();
    for (Card card : cards) {
      int count = 0;
      ArrayList<Integer> winning = card.getWinning();
      ArrayList<Integer> myNumbers = card.getMyNumbers();
      for (int win : winning) {
        if (myNumbers.contains(win)) {
          count++;
        }
      }
      contain.add(count);
    }
    return contain;
  }

  public static ArrayList<Card> getCards(ArrayList<String> input) {
    ArrayList<Card> cards = new ArrayList<>();
    for (String card : input) {
      String str = card.split(":")[1];
      String[] parts = str.split(" | ");
      ArrayList<Integer> myNumbers = new ArrayList<>();
      ArrayList<Integer> winning = new ArrayList<>();
      boolean firstHalf = true;
      for (String s : parts) {
        if (s.equals("|")) {
          firstHalf = false;
        }
        if (firstHalf) {
          try {
            int a = Integer.parseInt(s);
            winning.add(a);
          } catch (Exception e) {

          }
        } else {
          try {
            int a = Integer.parseInt(s);
            myNumbers.add(a);
          } catch (Exception e) {

          }
        }

      }
      Card temp = new Card(winning, myNumbers);
      cards.add(temp);
    }
    return cards;
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
