package Day15;

import java.util.ArrayList;

public class Box {

  ArrayList<Lens> lenses;

  public Box() {
    this.lenses = new ArrayList<>();
  }

  public int getMax() {
    int max = Integer.MIN_VALUE;
    for (Lens l : lenses) {
      if (l.getHashValue() > max) {
        max = (int) l.getHashValue();
      }
    }
    return max;
  }

  public int getMin() {
    int min = Integer.MAX_VALUE;
    for (Lens l : lenses) {
      if (l.getHashValue() < min) {
        min = (int) l.getHashValue();
      }
    }
    return min;
  }

  public void addLens(Lens l) {
    for (int i = 0; i < lenses.size(); i++) {
      if (l.getLabel().equals(lenses.get(i).getLabel())) {
        lenses.set(i, l);
        return;
      }
    }
    lenses.add(l);
  }

  public void removeLens(Lens l) {
    for (Lens lens : lenses) {
      if (lens.getLabel().equals(l.getLabel())) {
        lenses.remove(lens);
        break;
      }
    }
  }

  public ArrayList<Lens> getLenses() {
    return lenses;
  }

  public void setLenses(ArrayList<Lens> lenses) {
    this.lenses = lenses;
  }

}
