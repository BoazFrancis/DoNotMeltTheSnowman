public class Tests {

  public static void main(String[] args) {

    System.out.println("Running tests...");

    charToPieceTests();       // Part I, a, 1
    charsToPiecesTests();     // Part I, a, 2
    isEmitterTests();         // given method
    findEmitterTests();       // Part I, b.
    hideLaserTests();         // given method
    addLaserTests();          // Part I, c, 1
    moveTests();              // Part I, c, 2

    laserEndsTests();         // Part II, a, 1
    calculateResultTests();   // Part II, a, 2
    fireLaserTests();         // Part II, b
    rotatePieceTests();       // given method

    System.out.println("...all tests pass!");
  }

  public static void charToPieceTests() {
    assert PieceUtils.charToPiece('/') == Piece.MIRROR_SW_NE;
    assert PieceUtils.charToPiece('\\') == Piece.MIRROR_NW_SE;
    assert PieceUtils.charToPiece('|') == Piece.LASER_VERTICAL;
    assert PieceUtils.charToPiece('-') == Piece.LASER_HORIZONTAL;
    assert PieceUtils.charToPiece('+') == Piece.LASER_CROSSED;
    assert PieceUtils.charToPiece('o') == Piece.TARGET;
    assert PieceUtils.charToPiece('^') == Piece.EMITTER_NORTH;
    assert PieceUtils.charToPiece('>') == Piece.EMITTER_EAST;
    assert PieceUtils.charToPiece('v') == Piece.EMITTER_SOUTH;
    assert PieceUtils.charToPiece('<') == Piece.EMITTER_WEST;
    assert PieceUtils.charToPiece('#') == Piece.WALL;
    assert PieceUtils.charToPiece('@') == Piece.SNOWMAN;
    assert PieceUtils.charToPiece(' ') == Piece.EMPTY;
    assert PieceUtils.charToPiece('X') == null;
  }

  public static void charsToPiecesTests() {

    Piece[][] act = PieceUtils.charsToPieces(("/\\ "
                                            + "o^#"
                                            + "@@@"
                                            + "###").toCharArray(), 3, 4);

    assert act[0][0] == Piece.WALL;
    assert act[1][0] == Piece.WALL;
    assert act[2][0] == Piece.WALL;

    assert act[0][1] == Piece.SNOWMAN;
    assert act[1][1] == Piece.SNOWMAN;
    assert act[2][1] == Piece.SNOWMAN;

    assert act[0][2] == Piece.TARGET;
    assert act[1][2] == Piece.EMITTER_NORTH;
    assert act[2][2] == Piece.WALL;

    assert act[0][3] == Piece.MIRROR_SW_NE;
    assert act[1][3] == Piece.MIRROR_NW_SE;
    assert act[2][3] == Piece.EMPTY;
  }

  public static void isEmitterTests() {
    assert  PieceUtils.isEmitter(Piece.EMITTER_NORTH);
    assert  PieceUtils.isEmitter(Piece.EMITTER_EAST);
    assert  PieceUtils.isEmitter(Piece.EMITTER_SOUTH);
    assert  PieceUtils.isEmitter(Piece.EMITTER_WEST);
    assert !PieceUtils.isEmitter(Piece.LASER_VERTICAL);
    assert !PieceUtils.isEmitter(Piece.LASER_HORIZONTAL);
    assert !PieceUtils.isEmitter(Piece.LASER_CROSSED);
    assert !PieceUtils.isEmitter(Piece.MIRROR_SW_NE);
    assert !PieceUtils.isEmitter(Piece.MIRROR_NW_SE);
    assert !PieceUtils.isEmitter(Piece.WALL);
    assert !PieceUtils.isEmitter(Piece.TARGET);
    assert !PieceUtils.isEmitter(Piece.EMPTY);
    assert !PieceUtils.isEmitter(Piece.SNOWMAN);
  }

  public static void findEmitterTests() {
    Piece[][] act;

    act = PieceUtils.charsToPieces(("/\\ "
                                  + "o^#"
                                  + "@@@"
                                  + "###").toCharArray(), 3, 4);

    Coordinate c = PieceUtils.findEmitter(act);

    assert c != null;
    assert c.getX() == 1;
    assert c.getY() == 2;


    act = PieceUtils.charsToPieces(("/\\ "
                                  + "o@#"
                                  + "@@@"
                                  + "###").toCharArray(), 3, 4);

    c = PieceUtils.findEmitter(act);

    assert c == null;
  }

  public static void hideLaserTests() {
    assert PieceUtils.hideLaser(Piece.EMITTER_NORTH)    == Piece.EMITTER_NORTH;
    assert PieceUtils.hideLaser(Piece.EMITTER_EAST)     == Piece.EMITTER_EAST;
    assert PieceUtils.hideLaser(Piece.EMITTER_SOUTH)    == Piece.EMITTER_SOUTH;
    assert PieceUtils.hideLaser(Piece.EMITTER_WEST)     == Piece.EMITTER_WEST;
    assert PieceUtils.hideLaser(Piece.LASER_VERTICAL)   == Piece.EMPTY;
    assert PieceUtils.hideLaser(Piece.LASER_HORIZONTAL) == Piece.EMPTY;
    assert PieceUtils.hideLaser(Piece.LASER_CROSSED)    == Piece.EMPTY;
    assert PieceUtils.hideLaser(Piece.MIRROR_SW_NE)     == Piece.MIRROR_SW_NE;
    assert PieceUtils.hideLaser(Piece.MIRROR_NW_SE)     == Piece.MIRROR_NW_SE;
    assert PieceUtils.hideLaser(Piece.WALL)             == Piece.WALL;
    assert PieceUtils.hideLaser(Piece.TARGET)           == Piece.TARGET;
    assert PieceUtils.hideLaser(Piece.EMPTY)            == Piece.EMPTY;
    assert PieceUtils.hideLaser(Piece.SNOWMAN)          == Piece.SNOWMAN;
  }

  public static void addLaserTests() {
    assert
      PieceUtils.addLaser(Piece.EMITTER_NORTH, true)    == Piece.EMITTER_NORTH;
    assert
      PieceUtils.addLaser(Piece.EMITTER_EAST, true)     == Piece.EMITTER_EAST;
    assert
      PieceUtils.addLaser(Piece.EMITTER_SOUTH, true)    == Piece.EMITTER_SOUTH;
    assert
      PieceUtils.addLaser(Piece.EMITTER_WEST, true)     == Piece.EMITTER_WEST;
    assert
      PieceUtils.addLaser(Piece.LASER_VERTICAL, true)   == Piece.LASER_CROSSED;
    assert
      PieceUtils.addLaser(Piece.LASER_HORIZONTAL, true) == Piece.LASER_HORIZONTAL;
    assert
      PieceUtils.addLaser(Piece.LASER_CROSSED, true)    == Piece.LASER_CROSSED;
    assert
      PieceUtils.addLaser(Piece.MIRROR_SW_NE, true)     == Piece.MIRROR_SW_NE;
    assert
      PieceUtils.addLaser(Piece.MIRROR_NW_SE, true)     == Piece.MIRROR_NW_SE;
    assert PieceUtils.addLaser(Piece.WALL, true)        == Piece.WALL;
    assert PieceUtils.addLaser(Piece.TARGET, true)      == Piece.TARGET;
    assert
      PieceUtils.addLaser(Piece.EMPTY, true)            == Piece.LASER_HORIZONTAL;
    assert PieceUtils.addLaser(Piece.SNOWMAN, true)     == Piece.SNOWMAN;

    assert
      PieceUtils.addLaser(Piece.EMITTER_NORTH, false)    == Piece.EMITTER_NORTH;
    assert
      PieceUtils.addLaser(Piece.EMITTER_EAST, false)     == Piece.EMITTER_EAST;
    assert
      PieceUtils.addLaser(Piece.EMITTER_SOUTH, false)    == Piece.EMITTER_SOUTH;
    assert
      PieceUtils.addLaser(Piece.EMITTER_WEST, false)     == Piece.EMITTER_WEST;
    assert
      PieceUtils.addLaser(Piece.LASER_VERTICAL, false)   == Piece.LASER_VERTICAL;
    assert
      PieceUtils.addLaser(Piece.LASER_HORIZONTAL, false) == Piece.LASER_CROSSED;
    assert
      PieceUtils.addLaser(Piece.LASER_CROSSED, false)    == Piece.LASER_CROSSED;
    assert
      PieceUtils.addLaser(Piece.MIRROR_SW_NE, false)     == Piece.MIRROR_SW_NE;
    assert
      PieceUtils.addLaser(Piece.MIRROR_NW_SE, false)     == Piece.MIRROR_NW_SE;
    assert PieceUtils.addLaser(Piece.WALL, false)        == Piece.WALL;
    assert PieceUtils.addLaser(Piece.TARGET, false)      == Piece.TARGET;
    assert
      PieceUtils.addLaser(Piece.EMPTY, false)            == Piece.LASER_VERTICAL;
    assert PieceUtils.addLaser(Piece.SNOWMAN, false)     == Piece.SNOWMAN;
  }

  public static void moveTests() {

    Coordinate c = new Coordinate(5,3);

    Coordinate out;

    out = PieceUtils.move(Piece.EMITTER_NORTH, c, 0, 0);
    assert out.getX() == 5;
    assert out.getY() == 4;

    out = PieceUtils.move(Piece.EMITTER_EAST, c, 0, 0);
    assert out.getX() == 6;
    assert out.getY() == 3;

    out = PieceUtils.move(Piece.EMITTER_SOUTH, c, 0, 0);
    assert out.getX() == 5;
    assert out.getY() == 2;

    out = PieceUtils.move(Piece.EMITTER_WEST, c, 0, 0);
    assert out.getX() == 4;
    assert out.getY() == 3;


    out = PieceUtils.move(Piece.LASER_VERTICAL, c, 1, 0);
    assert out.getX() == 6;
    assert out.getY() == 3;

    out = PieceUtils.move(Piece.LASER_HORIZONTAL, c, 0, 1);
    assert out.getX() == 5;
    assert out.getY() == 4;

    out = PieceUtils.move(Piece.LASER_CROSSED, c, 0, -1);
    assert out.getX() == 5;
    assert out.getY() == 2;

    out = PieceUtils.move(Piece.EMPTY, c, -1, 0);
    assert out.getX() == 4;
    assert out.getY() == 3;


    out = PieceUtils.move(Piece.MIRROR_SW_NE, c, 1, 0);
    assert out.getX() == 5;
    assert out.getY() == 4;
    out = PieceUtils.move(Piece.MIRROR_SW_NE, c, 0, -1);
    assert out.getX() == 4;
    assert out.getY() == 3;

    out = PieceUtils.move(Piece.MIRROR_NW_SE, c, 0, -1);
    assert out.getX() == 6;
    assert out.getY() == 3;
    out = PieceUtils.move(Piece.MIRROR_NW_SE, c, 1, 0);
    assert out.getX() == 5;
    assert out.getY() == 2;

    out = PieceUtils.move(Piece.WALL, c, 1, 0);
    assert out.getX() == 5;
    assert out.getY() == 3;

    out = PieceUtils.move(Piece.TARGET, c, 0, 1);
    assert out.getX() == 5;
    assert out.getY() == 3;

    out = PieceUtils.move(Piece.SNOWMAN, c, -1, 0);
    assert out.getX() == 5;
    assert out.getY() == 3;

  }



  public static void laserEndsTests() {
    Piece[][] act = PieceUtils.charsToPieces(("/\\ "
                                            + "o^#"
                                            + "@  "
                                            ).toCharArray(), 3, 3);

    Board b = new Board(act);

    assert b.laserEnds(new Coordinate(-1, 0));
    assert b.laserEnds(new Coordinate(0, -1));
    assert b.laserEnds(new Coordinate(3, 0));
    assert b.laserEnds(new Coordinate(0, 3));

    assert b.laserEnds(new Coordinate(0,0));
    assert b.laserEnds(new Coordinate(0,1));
    assert b.laserEnds(new Coordinate(1,1));
    assert b.laserEnds(new Coordinate(2,1));

    assert !b.laserEnds(new Coordinate(0,2));
    assert !b.laserEnds(new Coordinate(1,2));
    assert !b.laserEnds(new Coordinate(2,2));
  }

  public static void calculateResultTests() {
    Piece[][] act = PieceUtils.charsToPieces(("/\\ "
                                            + "o^#"
                                            + "@  "
                                            ).toCharArray(), 3, 3);

    Board b = new Board(act);

    assert b.calculateResult(new Coordinate(-1, 0)) == Result.MISS;
    assert b.calculateResult(new Coordinate(0, -1)) == Result.MISS;
    assert b.calculateResult(new Coordinate(3, 0))  == Result.MISS;
    assert b.calculateResult(new Coordinate(0, 3))  == Result.MISS;

    assert b.calculateResult(new Coordinate(0,0)) == Result.MELT_SNOWMAN;
    assert b.calculateResult(new Coordinate(0,1)) == Result.HIT_TARGET;
    assert b.calculateResult(new Coordinate(1,1)) == Result.MISS;
    assert b.calculateResult(new Coordinate(2,1)) == Result.MISS;

    assert b.calculateResult(new Coordinate(0,2)) == Result.MISS;
    assert b.calculateResult(new Coordinate(1,2)) == Result.MISS;
    assert b.calculateResult(new Coordinate(2,2)) == Result.MISS;
  }

  public static void fireLaserTests() {
    Piece[][] act;
    Board b;

    act = PieceUtils.charsToPieces(("/\\ "
                                  + "o^#"
                                  + "@  "
                                  ).toCharArray(), 3, 3);
    b = new Board(act);
    assert b.fireLaser() == Result.HIT_TARGET;



    act = PieceUtils.charsToPieces(("/  "
                                  + "o^#"
                                  + "@  "
                                  ).toCharArray(), 3, 3);
    b = new Board(act);
    assert b.fireLaser() == Result.MISS;


    act = PieceUtils.charsToPieces(("/\\ "
                                  + " ^#"
                                  + "@o "
                                  ).toCharArray(), 3, 3);
    b = new Board(act);
    assert b.fireLaser() == Result.MELT_SNOWMAN;
  }

  public static void rotatePieceTests() {
    Piece[][] act;
    Board b;

    act = PieceUtils.charsToPieces(("// "
                                  + "o^#"
                                  + "@  "
                                  ).toCharArray(), 3, 3);
    b = new Board(act);
    b.rotatePiece(new Coordinate(1,2));
    assert b.fireLaser() == Result.HIT_TARGET;

    act = PieceUtils.charsToPieces(("// "
                                  + "ov#"
                                  + "@  "
                                  ).toCharArray(), 3, 3);
    b = new Board(act);
    b.rotatePiece(new Coordinate(1,1));
    assert b.fireLaser() == Result.HIT_TARGET;
  }

}
