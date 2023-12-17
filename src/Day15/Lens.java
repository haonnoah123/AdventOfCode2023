package Day15;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Lens {

  private String label;
  private char operation;
  private int focalLength;
  private int hashValue;

  public Lens(String label, char operation, int focalLength) {
    this.label = label;
    this.operation = operation;
    this.focalLength = focalLength;
    byte[] bytes = label.getBytes(StandardCharsets.US_ASCII);
    List<Integer> result = new ArrayList<>();
    for (byte aByte : bytes) {
      int ascii = (int) aByte;
      result.add(ascii);
    }
    int curr = 0;
    for (int num : result) {
      curr += num;
      curr *= 17;
      curr %= 256;
    }
    this.hashValue = curr;
  }

  public int getHashValue() {
    return hashValue;
  }

  public void setHashValue(int hashValue) {
    this.hashValue = hashValue;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public char getOperation() {
    return operation;
  }

  public void setOperation(char operation) {
    this.operation = operation;
  }

  public int getFocalLength() {
    return focalLength;
  }

  public void setFocalLength(int focalLength) {
    this.focalLength = focalLength;
  }

  @Override
  public String toString() {
    return "[" + this.label + " " + this.focalLength + "]";
  }

}
