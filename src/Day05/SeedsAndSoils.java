package Day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SeedsAndSoils {

  public static void main(String[] args) {
    partOne();
    partTwo(); // returns 69323688
  }

  public static void partTwo() {
    ArrayList<String> input = importFile("Day5Input");
    ArrayList<Long> initialSeeds = findInitialSeeds(input);
    ArrayList<Maps> maps = getMaps(input);
    System.out.println(updateInitialSeeds(initialSeeds, maps));
  }

  public static Long updateInitialSeeds(ArrayList<Long> initialSeeds, ArrayList<Maps> maps) {
    Long min = Long.MAX_VALUE;
    for (int i = 0; i < initialSeeds.size(); i += 2) {
      Long seedAtI = initialSeeds.get(i);
      Long goToSeed = initialSeeds.get((int) (i + 1)) + seedAtI;
      for (Long j = seedAtI; j < goToSeed; j++) {
        Long curr = followInitialSeed(j, maps);
        if (curr < min) {
          min = curr;
        }
      }
    }
    return min;
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
        Long maxSource = m.getSourceRangeStart() + m.getRangeLength();
        if (maxSource > thisSeed && m.getSourceRangeStart() <= thisSeed) {
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
