import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import static cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for EnglishSolitaireModel.
 */
public class EnglishSolitaireModelTest {
  static EnglishSolitaireModel model1;
  static EnglishSolitaireModel model2;
  static EnglishSolitaireModel model3;
  static EnglishSolitaireModel model4;

  // set up game models for testing
  protected static void init() {
    model1 = new EnglishSolitaireModel();
    model2 = new EnglishSolitaireModel(5);
    model3 = new EnglishSolitaireModel(2, 5);
    model4 = new EnglishSolitaireModel(5, 7, 3);
  }

  // test constructors and exceptions
  // test EnglishSolitaireModel(): armThickness 3, empty slot center
  @Test
  public void esmCons1Test() {
    init();

    // check if the initial score and board size are correct
    assertEquals(7, model1.getBoardSize());
    assertEquals(32, model1.getScore());
  }

  @Test(expected = IllegalArgumentException.class)
  public void esmCons2DisallowOutOfBoundRowNegative() {
    esmCons2(-6, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void esmCons2DisallowOutOfBoundRowPositive() {
    esmCons2(20, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void esmCons2DisallowOutOfBoundColNegative() {
    esmCons2(4, -7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void esmCons2DisallowOutOfBoundColPositive() {
    esmCons2(5, 7);
  }

  @Test
  public void esmCons2DisallowInvalidRowCol() {
    for (int i = 0; i < 7; i++) {
      if (i == 2) {
        i += 2;
      } else {
        for (int j = 0; j < 7; j++) {
          if (j == 2) {
            j += 2;
          } else {
            boolean failAsExpected = false;

            try {
              esmCons2(i, j);
            } catch (IllegalArgumentException e) {
              failAsExpected = true;
            }
            if (!(failAsExpected)) {
              throw new RuntimeException("Should not construct a new model with an empty slot "
                      + "at invalid slot. ");
            }
          }
        }
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void esmCons3DisallowNegativeArmThickness() {
    esmCons3(-1999);
  }

  @Test(expected = IllegalArgumentException.class)
  public void esmCons3DisallowZeroArmThickness() {
    esmCons3(0);
  }

  @Test
  public void esmCons3DisallowEvenArmThickness() {
    for (int i = 2; i < 100; i += 2) {
      boolean failAsExpected = false;
      try {
        esmCons3(i);
      }
      catch (IllegalArgumentException e) {
        failAsExpected = true;
      }
      if (!(failAsExpected)) {
        throw new RuntimeException("Third constructor for EnglishSolitaireModel should "
                + "not construct with even arm thickness. ");
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void esmCons4DisallowNegativeArmThickness() {
    esmCons3(-294);
  }

  @Test(expected = IllegalArgumentException.class)
  public void esmCons4DisallowZeroArmThickness() {
    esmCons3(0);
  }

  @Test
  public void esmCons4DisallowEvenArmThickness() {
    for (int i = 2; i < 100; i += 2) {
      boolean failAsExpected = false;
      try {
        esmCons4(i, 4, 5);
      }
      catch (IllegalArgumentException e) {
        failAsExpected = true;
      }
      if (!(failAsExpected)) {
        throw new RuntimeException("Third constructor for EnglishSolitaireModel should "
                + "not construct with even arm thickness. ");
      }
    }
  }

  @Test
  public void esmCons4DisallowInvalidRowCol() {
    init();

    for (int i = 0; i < 13; i++) {
      if (i == 4) {
        i += 4;
      } else {
        for (int j = 0; j < 13; j++) {
          if (j == 4) {
            j += 4;
          } else {
            boolean failAsExpected = false;

            try {
              esmCons4(5, i, j);
            } catch (IllegalArgumentException e) {
              failAsExpected = true;
            }
            if (!(failAsExpected)) {
              throw new RuntimeException("Should not construct a new model with an empty slot "
                      + "at invalid slot. ");
            }
          }
        }
      }
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void esmCons4DisallowOutOfBoundRowNegative() {
    esmCons4(5, -1, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void esmCons4DisallowOutOfBoundRowPositive() {
    esmCons4(7,19, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void esmCons4DisallowOutOfBoundColNegative() {
    esmCons4(5, 7, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void esmCons4DisallowOutOfBoundColPositive() {
    esmCons4(10, 7, 28);
  }

  // move works on with applicable slots (marble to an empty with a marble in between)
  @Test
  public void moveWorksHorizontal() {
    init();
    MarbleSolitaireTextView modelView = new MarbleSolitaireTextView(model1);
    String model1Before =
                          "    O O O\n"
                        + "    O O O\n"
                        + "O O O O O O O\n"
                        + "O O O _ O O O\n"
                        + "O O O O O O O\n"
                        + "    O O O\n"
                        + "    O O O";

    String model1After =
                          "    O O O\n"
                        + "    O O O\n"
                        + "O O O O O O O\n"
                        + "O _ _ O O O O\n"
                        + "O O O O O O O\n"
                        + "    O O O\n"
                        + "    O O O";

    // check initial cond: slots at (1, 3) and (2, 3) are Marbles, (3, 3) is an Empty
    assertEquals(SlotState.Marble, model1.getSlotAt(3,1));
    assertEquals(SlotState.Marble, model1.getSlotAt(3,2));
    assertEquals(SlotState.Empty, model1.getSlotAt(3,3));
    assertEquals(model1Before, modelView.toString());

    model1.move(3, 1, 3, 3);

    // check slot state after moving: (1, 3) and (2, 3) are Empties, (3, 3) is a Marble
    assertEquals(SlotState.Empty, model1.getSlotAt(3,1));
    assertEquals(SlotState.Empty, model1.getSlotAt(3,2));
    assertEquals(SlotState.Marble, model1.getSlotAt(3,3));
    assertEquals(model1After, modelView.toString());
  }

  @Test
  public void moveWorksVertical() {
    init();
    MarbleSolitaireTextView modelView = new MarbleSolitaireTextView(model1);
    String model1Before =
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O";
    String model1After =
            "    O O O\n"
                    + "    O _ O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O";

    // check initial cond: slots at (1, 3) and (2, 3) are Marbles, (3, 3) is an Empty
    assertEquals(SlotState.Marble, model1.getSlotAt(1,3));
    assertEquals(SlotState.Marble, model1.getSlotAt(2,3));
    assertEquals(SlotState.Empty, model1.getSlotAt(3,3));
    assertEquals(model1Before, modelView.toString());

    model1.move(1, 3, 3, 3);

    // check slot state after moving: (1, 3) and (2, 3) are Empties, (3, 3) is a Marble
    assertEquals(SlotState.Empty, model1.getSlotAt(1,3));
    assertEquals(SlotState.Empty, model1.getSlotAt(2,3));
    assertEquals(SlotState.Marble, model1.getSlotAt(3,3));
    assertEquals(model1After, modelView.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveDisallowMarbleToMarble() {
    init();
    model1.move(3, 2, 3, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveDisallowDiagonalAlthoughLegalSlotState() {
    init();
    model3.move(0, 3, 2, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveDisallowConsecutiveMove() {
    init();
    model3.move(2, 4, 2, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void moveDisallowMarbleToInvalid() {
    init();
    EnglishSolitaireModel model6 = esmCons4(5, 2, 4);
    model6.move(2,5, 2, 3);
  }

  @Test
  public void isGameOverWorks() {
    init();
    // false since there is still movable spot
    assertFalse(model1.isGameOver());
    // true since no move is viable with an 1 arm thick game
    assertTrue(esmCons3(1).isGameOver());
  }

  @Test
  public void isGameOverWorksFullGame() {
    String endGame = "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ O _ _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "    _ _ _\n"
            + "    _ _ _";

    init();
    // play Marble Solitaire with the default setup model1
    // game is not over at the beginning of the game
    assertFalse(model1.isGameOver());
    model1.move(3, 5, 3, 3);
    model1.move(1, 4, 3, 4);
    model1.move(2, 6, 2, 4);
    model1.move(4, 6, 2, 6);
    model1.move(2, 3, 2, 5);
    model1.move(2, 6, 2, 4);
    model1.move(2, 1, 2, 3);
    assertFalse(model1.isGameOver());
    model1.move(0, 2, 2, 2);
    model1.move(0, 4, 0, 2);
    model1.move(3, 2, 1, 2);
    model1.move(0, 2, 2, 2);
    //
    model1.move(5, 2, 3, 2);
    model1.move(4, 0, 4, 2);
    assertFalse(model1.isGameOver());
    model1.move(2, 0, 4, 0);
    model1.move(4, 3, 4, 1);
    //
    model1.move(4, 5, 4, 3);
    model1.move(6, 4, 4, 4);
    model1.move(6, 2, 6, 4);
    model1.move(3, 4, 5, 4);
    assertFalse(model1.isGameOver());
    model1.move(6, 4, 4, 4);
    model1.move(4, 0, 4, 2);
    // left arrow shaped
    model1.move(3, 2, 1, 2);
    model1.move(1, 2, 1, 4);
    model1.move(1, 4, 3, 4);
    assertFalse(model1.isGameOver());
    model1.move(3, 4, 5, 4);
    model1.move(5, 4, 5, 2);
    model1.move(5, 2, 3, 2);
    // T shaped
    model1.move(3, 3, 1, 3);
    model1.move(3, 1, 3, 3);
    assertFalse(model1.isGameOver());
    model1.move(4, 3, 2, 3);
    model1.move(1, 3, 3, 3);

    assertTrue(model1.isGameOver());
    MarbleSolitaireTextView modelView = new MarbleSolitaireTextView(model1);
    assertEquals(endGame, modelView.toString());
  }

  @Test
  public void getBoardSizeWorks() {
    init();
    assertEquals(7, model1.getBoardSize());
    assertEquals(13, model2.getBoardSize());
    assertEquals(7, model3.getBoardSize());
    assertEquals(13, model4.getBoardSize());
  }

  @Test
  public void getSlotAtWorks() {
    // using model1
    init();

    // get (4, 7) that is a Marble
    assertEquals(SlotState.Marble, model1.getSlotAt(4, 6));

    // get (3, 3) that is an Empty
    assertEquals(SlotState.Empty, model1.getSlotAt(3, 3));

    // get (0, 1) that is an Invalid
    assertEquals(SlotState.Invalid, model1.getSlotAt(0, 1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void cannotGetSlotOutOfBoundNegRow() {
    init();
    model1.getSlotAt(-1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void cannotGetSlotOutOfBoundPosRow() {
    init();
    model1.getSlotAt(7, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void cannotGetSlotOutOfBoundNegCol() {
    init();
    model1.getSlotAt(2, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void cannotGetSlotOutOfBoundPosCol() {
    init();
    model1.getSlotAt(4, 7);
  }

  @Test
  public void getScoreWorks() {
    boolean failAsExpected = false;
    init();
    //    model 1
    //            "    O O O \n" +
    //            "    O O O \n" +
    //            "O O O O O O O \n" +
    //            "O O O _ O O O \n" +
    //            "O O O O O O O \n" +
    //            "    O O O \n" +
    //            "    O O O \n"
    // score = 0 for game of arm thickness 1
    assertEquals(0, esmCons3(1).getScore());

    // score = 32 at the beginning of the game
    assertEquals(32, this.model1.getScore());

    // score = 31 after one valid move
    this.model1.move(3,1, 3, 3);
    assertEquals(31, this.model1.getScore());

    // score = 31 maintains after an invalid move
    try {
      this.model1.move(4,1, 3, 3);
    }
    catch (IllegalArgumentException e) {
      failAsExpected = true;
      assertEquals(31, this.model1.getScore());
    }

    if (!(failAsExpected)) {
      throw new RuntimeException("Should throw an exception for illegal move. ");
    }
  }

  static EnglishSolitaireModel esmCons2(int sRow, int sCol) {
    return new EnglishSolitaireModel(sRow, sCol);
  }

  static EnglishSolitaireModel esmCons3(int armThickness) {
    return new EnglishSolitaireModel(armThickness);
  }

  static EnglishSolitaireModel esmCons4(int armThickness, int sRow, int sCol) {
    return new EnglishSolitaireModel(armThickness, sRow, sCol);
  }
}