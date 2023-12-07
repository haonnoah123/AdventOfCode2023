package Day7;

import java.util.Arrays;

public class Card implements Comparable<Card> {

  private String[] hand;
  private int bid;
  private CARDTYPE type;
  private boolean partOne;

  public Card(String[] hand, int bid, CARDTYPE type, boolean partOne) {
    this.hand = hand;
    this.bid = bid;
    this.type = type;
    this.partOne = partOne;
  }

  public CARDTYPE getType() {
    return type;
  }

  public void setType(CARDTYPE type) {
    this.type = type;
  }

  public String[] getHand() {
    return hand;
  }

  public void setHand(String[] hand) {
    this.hand = hand;
  }

  public int getBid() {
    return bid;
  }

  public void setBid(int bid) {
    this.bid = bid;
  }

  @Override
  public String toString() {
    return "Card [hand=" + Arrays.toString(hand) + ", bid=" + bid + ", type=" + type + "]";
  }

  @Override
  public int compareTo(Card comparing) {
    if (this.type.compareTo(comparing.type) != 0) {
      return this.type.compareTo(comparing.type) * -1;
    } else {
      String[] strength = null;
      if (partOne) {
        strength = new String[] {"A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2"};
      } else {
        strength = new String[] {"A", "K", "Q", "T", "9", "8", "7", "6", "5", "4", "3", "2", "J"};
      }
      for (int i = 0; i < 5; i++) {
        if (Arrays.asList(strength).indexOf(this.hand[i]) < Arrays.asList(strength)
            .indexOf(comparing.hand[i])) {
          return 1;
        } else if (Arrays.asList(strength).indexOf(this.hand[i]) > Arrays.asList(strength)
            .indexOf(comparing.hand[i])) {
          return -1;
        }
      }
    }
    return 0;
  }

}
