public class Level {

  private final String levelString;
  private final int width;
  private final int height;

  public Level(String levelString, int width, int height) {
    this.levelString = levelString;
    this.width = width;
    this.height = height;
  }

  public char[] getCharArray() {
    return levelString.toCharArray();
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

}
