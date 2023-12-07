package Day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class CamelCards {

  public static void main(String[] args) {
    partOne();
    partTwo();
  }

  public static void partTwo() {
    ArrayList<String> input = importFile("Day7Input");
    ArrayList<Card> cards = getCardsFromInput(input, false);
    Collections.sort(cards);
    System.out.println(totalWinnings(cards));
  }

  public static CARDTYPE getCardTypeJoker(String[] hand) {
    long amountJ = Arrays.stream(hand).filter(item -> item.equals("J")).count();
    long amountFirst = Arrays.stream(hand).filter(item -> item.equals(hand[0])).count();
    long amountSecond = Arrays.stream(hand).filter(item -> item.equals(hand[1])).count();
    long amountThird = Arrays.stream(hand).filter(item -> item.equals(hand[2])).count();
    long amountFourth = Arrays.stream(hand).filter(item -> item.equals(hand[3])).count();
    long amountFifth = Arrays.stream(hand).filter(item -> item.equals(hand[4])).count();
    long[] amounts = new long[] {amountFirst, amountSecond, amountThird, amountFourth, amountFifth};
    Arrays.sort(amounts);
    CARDTYPE normal = getCardType(hand);
    if (amountJ == 3) {
      if (Arrays.equals(amounts, new long[] {1, 1, 3, 3, 3})) {
        return CARDTYPE.FourOfAKind;
      }
    } else if (amountJ == 2) {
      if (Arrays.equals(amounts, new long[] {1, 1, 1, 2, 2})) {
        return CARDTYPE.ThreeOfAKind;
      }
    }
    for (int i = 0; i < amountJ; i++) {
      normal = upgradeCardType(normal);
      if (normal == null) {
        System.out.println(Arrays.toString(hand));
      }
    }
    return normal;
  }

  public static CARDTYPE upgradeCardType(CARDTYPE type) {
    if (type == CARDTYPE.FourOfAKind) {
      return CARDTYPE.FiveOfAKind;
    } else if (type == CARDTYPE.FullHouse) {
      return CARDTYPE.FourOfAKind;
    } else if (type == CARDTYPE.ThreeOfAKind) {
      return CARDTYPE.FourOfAKind;
    } else if (type == CARDTYPE.TwoPair) {
      return CARDTYPE.FullHouse;
    } else if (type == CARDTYPE.OnePair) {
      return CARDTYPE.ThreeOfAKind;
    } else if (type == CARDTYPE.HighCard) {
      return CARDTYPE.OnePair;
    } else {
      return type;
    }
  }

  public static void partOne() {
    ArrayList<String> input = importFile("Day7Input");
    ArrayList<Card> cards = getCardsFromInput(input, true);
    Collections.sort(cards);
    System.out.println(totalWinnings(cards));
  }

  public static long totalWinnings(ArrayList<Card> cards) {
    long total = 0L;
    for (int i = 0; i < cards.size(); i++) {
      total += (cards.get(i).getBid() * (i + 1));
    }
    return total;
  }

  public static CARDTYPE getCardType(String[] hand) {
    // check for five
    long amountFirst = Arrays.stream(hand).filter(item -> item.equals(hand[0])).count();
    long amountSecond = Arrays.stream(hand).filter(item -> item.equals(hand[1])).count();
    long amountThird = Arrays.stream(hand).filter(item -> item.equals(hand[2])).count();
    long amountFourth = Arrays.stream(hand).filter(item -> item.equals(hand[3])).count();
    long amountFifth = Arrays.stream(hand).filter(item -> item.equals(hand[4])).count();
    long[] amounts = new long[] {amountFirst, amountSecond, amountThird, amountFourth, amountFifth};
    Arrays.sort(amounts);
    if (amountFirst == 5) {
      return CARDTYPE.FiveOfAKind;
    }
    if (amountFirst == 4 || amountSecond == 4) {
      return CARDTYPE.FourOfAKind;
    }
    if (Arrays.equals(amounts, new long[] {1, 1, 1, 1, 1})) {
      return CARDTYPE.HighCard;
    }
    if (Arrays.equals(amounts, new long[] {2, 2, 3, 3, 3})) {
      return CARDTYPE.FullHouse;
    }
    if (Arrays.equals(amounts, new long[] {1, 1, 3, 3, 3})) {
      return CARDTYPE.ThreeOfAKind;
    }
    if (Arrays.equals(amounts, new long[] {1, 2, 2, 2, 2})) {
      return CARDTYPE.TwoPair;
    }
    if (Arrays.equals(amounts, new long[] {1, 1, 1, 2, 2})) {
      return CARDTYPE.OnePair;
    }
    return null;
  }

  public static ArrayList<Card> getCardsFromInput(ArrayList<String> input, boolean partOne) {
    ArrayList<Card> cards = new ArrayList<>();
    for (String line : input) {
      String[] hand = line.split(" ")[0].split("");
      int bid = Integer.parseInt(line.split(" ")[1]);
      if (partOne) {
        cards.add(new Card(hand, bid, getCardType(hand), partOne));
      } else {
        cards.add(new Card(hand, bid, getCardTypeJoker(hand), partOne));
      }
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
