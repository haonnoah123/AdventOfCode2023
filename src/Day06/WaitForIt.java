package Day06;

public class WaitForIt {

  public static void main(String[] args) {
    partOne();
    partTwo();
  }
  
  public static void partTwo() {
    int testTime = 71530;
    int testDistance = 940200;
    long actualTime = 55999793;
    long actualDistance = 401148522741405L;
    System.out.println(getWaysToWin(actualTime, actualDistance));
  }
  
  public static void partOne() {
    int[] testTime = new int[] {7, 15, 30};
    int[] testDistance = new int[] {9, 40, 200};
    int[] actualTime = new int[] {55, 99, 97, 93};
    int[] actualDistance = new int[] {401, 1485, 2274, 1405};
    System.out.println(numWaysToBeat(actualTime, actualDistance));
  }
  
  public static int numWaysToBeat(int[] time, int[] distance) {
    int count = 1;
    for(int i = 0; i < time.length; i++) {
      count *= getWaysToWin(time[i], distance[i]);
    }
    return count;
  }
  
  public static long getWaysToWin(long time, long distance) {
    int count = 0;
    for(long i = 0; i < time - 1; i++) {
      long amountOfTime = time - i;
      long distanceTraveled = amountOfTime * i;
      if(distanceTraveled > distance) {
        count++;
      }
    }
    return count;
  }

}
