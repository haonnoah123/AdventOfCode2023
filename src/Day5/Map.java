package Day5;

public class Map {

  private Long destinationRangeStart;
  private Long sourceRangeStart;
  private Long rangeLength;

  public Map(Long destinationRangeStart, Long sourceRangeStart, Long rangeLength) {
    this.destinationRangeStart = destinationRangeStart;
    this.sourceRangeStart = sourceRangeStart;
    this.rangeLength = rangeLength;
  }

  public Long getDestinationRangeStart() {
    return destinationRangeStart;
  }

  public void setDestinationRangeStart(Long destinationRangeStart) {
    this.destinationRangeStart = destinationRangeStart;
  }

  public Long getSourceRangeStart() {
    return sourceRangeStart;
  }

  public void setSourceRangeStart(Long sourceRangeStart) {
    this.sourceRangeStart = sourceRangeStart;
  }

  public Long getRangeLength() {
    return rangeLength;
  }

  public void setRangeLength(Long rangeLength) {
    this.rangeLength = rangeLength;
  }

  @Override
  public String toString() {
    String output = "seed" + "\t" + "soil" + "\n";
    for (int i = 0; i < this.rangeLength; i++) {
      output += (this.sourceRangeStart + i) + "\t" + (this.destinationRangeStart + i) + "\n";
    }
    //System.out.prLongln(output);
    return output;
  }

}
