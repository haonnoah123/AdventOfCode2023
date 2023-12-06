package Day5;

import java.util.ArrayList;

public class Maps {

  private ArrayList<Map> maps = new ArrayList<>();
  // private ArrayList<Long> sourceRange = new ArrayList<>();
  // private ArrayList<Long> destinationRange = new ArrayList<>();

  public Maps(ArrayList<Map> maps) {
    this.maps = maps;
    // for (Map map : maps) {
    // for (int i = 0; i < map.getRangeLength(); i++) {
    // sourceRange.add(map.getSourceRangeStart() + i);
    // destinationRange.add(map.getDestinationRangeStart() + i);
    // }
    // }
  }

  public ArrayList<Map> getMaps() {
    return maps;
  }

  public void reverseMaps() {
    for (Map map : maps) {
      Long tempDestination = map.getDestinationRangeStart();
      map.setDestinationRangeStart(map.getSourceRangeStart());
      map.setSourceRangeStart(tempDestination);
    }
  }

  // public ArrayList<Long> getSourceRange() {
  // return sourceRange;
  // }
  //
  // public ArrayList<Long> getDestinationRange() {
  // return destinationRange;
  // }

  // @Override
  // public String toString() {
  // String output = "seed" + "\t" + "soil" + "\n";
  // for(int i = 0; i < this.sourceRange.size(); i++) {
  // output+= this.sourceRange.get(i) + "\t" + this.destinationRange.get(i) + "\n";
  // }
  // // System.out.println(output);
  // return output;
  // }

}
