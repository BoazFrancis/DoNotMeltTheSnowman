public class DoNotMeltTheSnowman {

  public static void main(String[] args) {
    System.out.println("Please choose a level 1 - 11");
    int levelIndex = Integer.parseInt(IOUtil.readString()) - 1;
    Level level = Levels.getLevels()[levelIndex];
    char[] charArray = level.getCharArray();
    Piece[][] board = PieceUtils.charsToPieces(charArray, level.getWidth(), level.getHeight());
    Board gameBoard = new Board(board);
    System.out.println("Hello! Let's play!");

    while (true) {
      Result result = gameBoard.fireLaser();
      gameBoard.renderBoard();
      if (result == Result.MELT_SNOWMAN) {
        System.out.println("Sorry, a snowman has melted");
        break;
      }
      else if (result == Result.HIT_TARGET) {
        System.out.println("Congrats! You won!");
        break;
      }
      System.out.println("Target missed!");
      gameBoard.clearLasers();
      System.out.println("Please enter a row and a column of a piece you would like to rotate separated by a space: ");
      int row = IOUtil.readInt();
      int column = IOUtil.readInt();
      gameBoard.rotatePiece(new Coordinate(column, row));

    }
  }

}
