package Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SeedsAndSoils {

  public static void main(String[] args) {
    // partOne();
    ArrayList<String> input = importFile("Day5Input");
    ArrayList<Long> initialSeeds = findInitialSeeds(input);
    ArrayList<Maps> maps = getMaps(input);
    //updateInitialSeeds(initialSeeds);
    for(Maps m : maps) {
      m.reverseMaps();
    }
    System.out.println(followInitialSeed((long) 35, maps));
  }

  public static void updateInitialSeeds(ArrayList<Long> initialSeeds) {
    ArrayList<Long> newSeeds = (ArrayList<Long>) initialSeeds.clone();
    initialSeeds.clear();
    for (int i = 0; i < newSeeds.size(); i += 2) {
      Long seedAtI = newSeeds.get(i);
      for (Long j = seedAtI; j < newSeeds.get((int) (i + 1)) + seedAtI; j++) {
        initialSeeds.add((long) j);
      }
    }
  }

  public static void partOne() {
    ArrayList<String> input = importFile("Day5Input");
    ArrayList<Long> initialSeeds = findInitialSeeds(input);
    ArrayList<Maps> maps = getMaps(input);
    System.out.println(getLowestInitialSeed(maps, initialSeeds));
  }

  public static Long getLowestInitialSeed(ArrayList<Maps> maps, ArrayList<Long> initialSeeds) {
    ArrayList<Long> destinations = new ArrayList<>();
    for (Long initialSeed : initialSeeds) {
      destinations.add(followInitialSeed(initialSeed, maps));
    }
    Long min = Long.MAX_VALUE;
    for (Long destination : destinations) {
      if (destination < min) {
        min = destination;
      }
    }
    return min;
  }

  public static Long followInitialSeed(Long initialSeed, ArrayList<Maps> maps) {
    Long thisSeed = initialSeed;
    for (Maps map : maps) {
      for (Map m : map.getMaps()) {
        long maxSource = m.getSourceRangeStart() + m.getRangeLength();
        if (maxSource >= thisSeed && m.getSourceRangeStart() <= thisSeed) {
          thisSeed = m.getDestinationRangeStart() + thisSeed - m.getSourceRangeStart();
          break;
        }
      }
    }
    return thisSeed;
  }

  public static ArrayList<Maps> getMaps(ArrayList<String> input) {
    ArrayList<Maps> maps = new ArrayList<>();
    for (int i = 0; i < input.size(); i++) {
      if (input.get(i).equals("")) {
        ArrayList<Map> tempMaps = new ArrayList<>();
        for (int j = i + 2; j < input.size(); j++) {
          if (input.get(j).equals("")) {
            break;
          }
          Long destinationRangeStart = Long.parseLong(input.get(j).split(" ")[0]);
          Long sourceRangeStart = Long.parseLong(input.get(j).split(" ")[1]);
          Long rangeLength = Long.parseLong(input.get(j).split(" ")[2]);
          Map tempMap = new Map(destinationRangeStart, sourceRangeStart, rangeLength);
          tempMaps.add(tempMap);
        }
        maps.add(new Maps(tempMaps));
      }
    }
    return maps;
  }

  public static ArrayList<Long> findInitialSeeds(ArrayList<String> input) {
    ArrayList<Long> initialSeeds = new ArrayList<>();
    String firstLine = input.get(0).split(": ")[1];
    String[] seeds = firstLine.split(" ");
    for (String seed : seeds) {
      initialSeeds.add(Long.parseLong(seed));
    }
    return initialSeeds;
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
