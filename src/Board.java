public class Board {

  private Piece[][] board;
  private Coordinate emitter;

  public Board(Piece[][] board) {
    this.board    = board;
    this.emitter  = PieceUtils.findEmitter(board);
  }

  public boolean laserEnds(Coordinate c) {
    int x = c.getX();
    int y = c.getY();
    if (isOutOfBoard(c)) {
      return true;
    }
    else {
      Piece p = board[x][y];
      if ((PieceUtils.isEmitter(p)) || (p == Piece.WALL) || (p == Piece.SNOWMAN) || (p == Piece.TARGET)) {
        return true;
      }
      return false;
    }
  }

  public boolean isOutOfBoard(Coordinate c) {
    int x = c.getX();
    int y = c.getY();
    if ((x < 0) || (x > board.length - 1) || (y < 0) || (y > board[0].length - 1)){
      return true;
    }
    return false;
  }

  public Result calculateResult(Coordinate c) {
    int x = c.getX();
    int y = c.getY();
    if (!isOutOfBoard(c)) {
      Piece p = board[x][y];
      if (p == Piece.TARGET) return Result.HIT_TARGET;
      else if (p == Piece.SNOWMAN) return Result.MELT_SNOWMAN;
    }
    return Result.MISS;
  }


  public Result fireLaser() {
    Coordinate current = emitter;
    int velX = 0;
    int velY = 0;
    boolean isHorizontal;
    do {
      int x = current.getX();
      int y = current.getY();
      if (velX != 0) isHorizontal = true;
      else isHorizontal = false;
      board[x][y] = PieceUtils.addLaser(board[x][y], isHorizontal);
      Coordinate next = PieceUtils.move(board[x][y], current, velX, velY);
      velX = next.getX() - current.getX();
      velY = next.getY() - current.getY();
      current = next;
    } while (!laserEnds(current));
    return calculateResult(current);
  }

  public void rotatePiece(Coordinate c) {
    assert c.getX() >= 0 && c.getX() < board.length
        && c.getY() >= 0 && c.getY() < board[0].length;

    board[c.getX()][c.getY()]
      = PieceUtils.rotate(board[c.getX()][c.getY()]);
  }

  public void clearLasers() {
    for (int i = 0; i < board.length ; i++) {
      for (int j = 0; j < board[i].length ; j++) {
        board[i][j] = PieceUtils.hideLaser(board[i][j]);
      }
    }
  }


  private static final char ESC = 27;

  public void renderBoard() {

    System.out.print(ESC + "[30;47m  ");
    for (int i = 0 ; i < board.length ; i++) {
      System.out.print(i);
    }
    System.out.println(" ");

    System.out.print(" ┏");
    for (int i = 0 ; i < board.length ; i++) {
      System.out.print("━");
    }
    System.out.println("┓");

    for (int j = board[0].length - 1 ; j >= 0 ; j--) {
      System.out.print(ESC + "[30m" + j +"┃");
      for (int i = 0 ; i < board.length ; i++ ) {
        System.out.print(renderPiece(board[i][j]));
      }
      System.out.println(ESC + "[30m┃");
    }
    System.out.print(ESC + "[30m ┗");
    for (int i = 0 ; i < board.length ; i++) {
      System.out.print("━");
    }
    System.out.println("┛");

  }

  private static String renderPiece(Piece p) {
    switch (p) {
      case EMITTER_NORTH:
        return ESC + "[32m↑";
      case EMITTER_EAST:
        return ESC + "[32m→";
      case EMITTER_SOUTH:
        return ESC + "[32m↓";
      case EMITTER_WEST:
        return ESC + "[32m←";

      case LASER_VERTICAL:
        return ESC + "[31m│";
      case LASER_HORIZONTAL:
        return ESC + "[31m─";
      case LASER_CROSSED:
        return ESC + "[31m┼";

      case MIRROR_SW_NE:
        return ESC + "[34m╱";
      case MIRROR_NW_SE:
        return ESC + "[34m╲";

      case WALL:
        return ESC + "[36m█";

      case TARGET:
        return ESC + "[35m☼";

      case EMPTY:
        return " ";

      case SNOWMAN:
        return ESC + "[30m☃";
    }
    return "!";
  }

}
