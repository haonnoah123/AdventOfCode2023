package Day15;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LensLibrary {

  public static void main(String[] args) {
    partOne();
    partTwo();
  }

  public static void partTwo() {
    String sequence = "";
    try {
      sequence = Files.readString(Paths.get("Day15Input"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    String[] input = sequence.split(",");
    ArrayList<Lens> lenses = getLenses(input);
    ArrayList<Box> boxes = new ArrayList<>();
    for (int i = 0; i < 256; i++) {
      boxes.add(new Box());
    }
    for (Lens l : lenses) {
      if (l.getOperation() == '=') {
        boxes.get(l.getHashValue()).addLens(l);
      } else {
        for (Box b : boxes) {
          b.removeLens(l);
        }
      }
    }
    long count = 0;
    for (int i = 0; i < boxes.size(); i++) {
      int j = 1;
      for (Lens l : boxes.get(i).getLenses()) {
        count += ((i + 1) * (j) * l.getFocalLength());
        j++;
      }
    }
    System.out.println(count);
  }

  public static ArrayList<Lens> getLenses(String[] input) {
    ArrayList<Lens> lenses = new ArrayList<>();
    for (String line : input) {
      if (line.indexOf("=") != -1) {
        lenses.add(new Lens(line.substring(0, line.indexOf("=")), '=',
            Integer.parseInt(line.substring(line.length() - 1))));
      } else {
        lenses.add(new Lens(line.substring(0, line.length() - 1), '-', -1));
      }
    }
    return lenses;
  }

  public static void partOne() {
    String sequence = "";
    try {
      sequence = Files.readString(Paths.get("Day15Input"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    String[] input = sequence.split(",");
    long sum = 0;
    for (String input1 : input) {
      byte[] bytes = input1.getBytes(StandardCharsets.US_ASCII);
      List<Integer> result = new ArrayList<>();
      for (byte aByte : bytes) {
        int ascii = (int) aByte;
        result.add(ascii);
      }
      long curr = 0;
      for (int num : result) {
        curr += num;
        curr *= 17;
        curr %= 256;
      }
      sum += curr;
    }
    System.out.println(sum);
  }

}
