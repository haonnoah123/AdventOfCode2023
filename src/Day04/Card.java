package Day04;

import java.util.ArrayList;

public class Card {

  private ArrayList<Integer> winning;
  private ArrayList<Integer> myNumbers;
  private int quantity = 1;

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Card(ArrayList<Integer> winning, ArrayList<Integer> myNumbers) {
    this.winning = winning;
    this.myNumbers = myNumbers;
  }

  public ArrayList<Integer> getWinning() {
    return winning;
  }

  public void setWinning(ArrayList<Integer> winning) {
    this.winning = winning;
  }

  public ArrayList<Integer> getMyNumbers() {
    return myNumbers;
  }

  public void setMyNumbers(ArrayList<Integer> myNumbers) {
    this.myNumbers = myNumbers;
  }

  @Override
  public String toString() {
    String output = "";
    for (int i : winning) {
      output += i + " ";
    }
    output += " | ";
    for (int i : myNumbers) {
      output += i + " ";
    }
    return output;
  }


}
