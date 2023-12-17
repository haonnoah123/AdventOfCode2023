package Day02;

public class Game {

  public int blue;
  public int red;
  public int green;

  public Game(int red, int green, int blue) {
    this.blue = blue;
    this.red = red;
    this.green = green;
  }

  public int getBlue() {
    return blue;
  }

  public void setBlue(int blue) {
    this.blue = blue;
  }

  public int getRed() {
    return red;
  }

  public void setRed(int red) {
    this.red = red;
  }

  public int getGreen() {
    return green;
  }

  public void setGreen(int green) {
    this.green = green;
  }

  @Override
  public String toString() {
    return "red " + red + ", green " + green + ", blue " + blue;
  }

}
