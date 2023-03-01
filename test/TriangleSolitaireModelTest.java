import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class represents tests for Triangle Solitaire Model.
 */
public class TriangleSolitaireModelTest {
  static TriangleSolitaireModel model;
  static TriangleSolitaireModel model2;
  static TriangleSolitaireModel model3;
  static TriangleSolitaireModel model4;
  static TriangleSolitaireModel centerModel;

  static TriangleSolitaireTextView view;
  static TriangleSolitaireTextView view2;
  static TriangleSolitaireTextView view3;
  static TriangleSolitaireTextView view4;
  static TriangleSolitaireTextView centerView;

  static String tsmDefault = "    _\n" + "   O O\n" + "  O O O\n" + " O O O O\n" + "O O O O O";
  static String tsmDimension8 =
          "       _\n" + "      O O\n" + "     O O O\n" + "    O O O O\n" + "   O O O O O\n" + " "
                  + " O O O O O O\n" + " O O O O O O O\n" + "O O O O O O O O";
  static String tsm3_3 = "    O\n" + "   O O\n" + "  O O O\n" + " O O O _\n" + "O O O O O";
  static String tsm10_5_5 = "         O\n" + "        O O\n" + "       O O O\n" + "      O O O "
          + "O\n" + "     O O O O O\n" + "    O O O O O _\n" + "   O O O O O O O\n"
          + "  O O O O O " + "O O O\n" + " O O O O O O O O O\n" + "O O O O O O O O O O";
  static String tsmDimension8Center = "       O\n" + "      O O\n" + "     O O O\n" + "    O O O "
          + "O\n" + "   O O _ O O\n" + "  O O O O O O\n" + " O O O O O O O\n" + "O O O O O O O O";
  // center is row 4, col 2

  private void init() {
    model = new TriangleSolitaireModel();
    model2 = new TriangleSolitaireModel(8);
    model3 = new TriangleSolitaireModel(3, 3);
    model4 = new TriangleSolitaireModel(10, 5, 5);
    centerModel = new TriangleSolitaireModel(8, 4, 2);

    view = new TriangleSolitaireTextView(model);
    view2 = new TriangleSolitaireTextView(model2);
    view3 = new TriangleSolitaireTextView(model3);
    view4 = new TriangleSolitaireTextView(model4);
    centerView = new TriangleSolitaireTextView(centerModel);
  }

  @Test
  public void tsmConsDisallowNegativeDimension() {
    try {
      TriangleSolitaireModel testModel = new TriangleSolitaireModel(-10);
      fail("Model should not be constructed with negative dimension. ");
    } catch (IllegalArgumentException e) {
      assertEquals("The dimension has to be a positive value. ", e.getMessage());
    }
  }

  @Test
  public void tsmConsDisallowZeroDimension() {
    try {
      TriangleSolitaireModel testModel = new TriangleSolitaireModel(0);
      fail("Model should not be constructed with negative dimension. ");
    } catch (IllegalArgumentException e) {
      assertEquals("The dimension has to be a positive value. ", e.getMessage());
    }
  }

  @Test
  public void tsmConsDisallowInvalidEmptySlot() {
    try {
      TriangleSolitaireModel testModel = new TriangleSolitaireModel(0, 3);
      fail("Model should not be constructed with negative dimension. ");
    } catch (IllegalArgumentException e) {
      assertEquals("The indicated position for empty slot is invalid. ", e.getMessage());
    }
  }

  @Test
  public void tsmConstructsCorrectly() {
    init();

    assertEquals(tsmDefault, view.toString());
    assertEquals(tsmDimension8, view2.toString());
    assertEquals(tsm3_3, view3.toString());
    assertEquals(tsm10_5_5, view4.toString());
  }

  @Test
  public void moveRightDiagWorks() {
    String tsmAfterMove = "    O\n" + "   _ O\n" + "  _ O O\n" + " O O O O\n" + "O O O O O";
    String tsmDimension8Moved =
            "       O\n" + "      O O\n" + "     O O _\n" + "    O O _ O\n" + "   O O O O O\n"
                    + "  O O O O O O\n" + " O O O O O O O\n" + "O O O O O O O O";
    init();
    // check initial shape
    assertEquals(tsmDefault, view.toString());
    // check shape after move; right diagonal up move
    model.move(2, 0, 0, 0);
    assertEquals(tsmAfterMove, view.toString());

    // right diagonal down
    assertEquals(tsmDimension8Center, centerView.toString());
    centerModel.move(2, 2, 4, 2);
    assertEquals(tsmDimension8Moved, centerView.toString());
  }

  @Test
  public void moveLeftDiagWorks() {
    String tsmAfterMove = "    O\n" + "   O _\n" + "  O O _\n" + " O O O O\n" + "O O O O O";
    String tsmDimension8Moved =
            "       O\n" + "      O O\n" + "     _ O O\n" + "    O _ O O\n" + "   O O O O O\n"
                    + "  O O O O O O\n" + " O O O O O O O\n" + "O O O O O O O O";

    init();
    // check initial shape
    assertEquals(tsmDefault, view.toString());
    // check shape after move; left diagonal up move
    model.move(2, 2, 0, 0);
    assertEquals(tsmAfterMove, view.toString());

    // left diagonal down
    assertEquals(tsmDimension8Center, centerView.toString());
    centerModel.move(2, 0, 4, 2);
    assertEquals(tsmDimension8Moved, centerView.toString());
  }

  @Test
  public void moveHorizontalWorks() {
    String tsmDimension8MovedLeft = "       O\n" + "      O O\n" + "     O O O\n" + "    O O O "
            + "O\n" + "   O O O _ _\n" + "  O O O O O O\n" + " O O O O O O O\n" + "O O O O O O O O";
    String tsmDimension8MovedRight = "       O\n" + "      O O\n" + "     O O O\n" + "    O O O "
            + "O\n" + "   _ _ O O O\n" + "  O O O O O O\n" + " O O O O O O O\n" + "O O O O O O O O";

    init();
    // check initial shape
    assertEquals(tsmDimension8Center, centerView.toString());
    // check shape after move; left diagonal up move
    centerModel.move(4, 4, 4, 2);
    assertEquals(tsmDimension8MovedLeft, centerView.toString());

    // left diagonal down
    init();
    assertEquals(tsmDimension8Center, centerView.toString());
    centerModel.move(4, 0, 4, 2);
    assertEquals(tsmDimension8MovedRight, centerView.toString());
  }

  @Test
  public void getBoardSizeWorks() {
    init();
    assertEquals(5, model.getBoardSize());
    assertEquals(8, centerModel.getBoardSize());
  }

  @Test
  public void gameOverWorks() {
    init();
    // the beginning of a default board game should be playable
    assertFalse(model.isGameOver());

    // a board game of dimension 1 should be game over
    TriangleSolitaireModel modelOver = new TriangleSolitaireModel(1);
    assertTrue(modelOver.isGameOver());
  }

  @Test
  public void gameOverFullDefaultGame() {
    init();
    model.move(2, 0, 0, 0);
    model.move(2, 2, 2, 0);
    model.move(0, 0, 2, 2);

    String currentBoard = "    _\n" + "   _ _\n" + "  O _ O\n" + " O O O O\n" + "O O O O O";
    assertEquals(currentBoard, view.toString());
    assertFalse(model.isGameOver());

    model.move(3, 0, 1, 0);
    model.move(4, 2, 2, 0);
    model.move(3, 3, 3, 1);

    String currentBoard2 = "    _\n" + "   O _\n" + "  O _ O\n" + " _ O _ _\n" + "O O _ O O";
    assertEquals(currentBoard2, view.toString());
    assertFalse(model.isGameOver());

    model.move(1, 0, 3, 0);
    model.move(3, 0, 3, 2);
    model.move(4, 4, 4, 2);

    String currentBoard3 = "    _\n" + "   _ _\n" + "  _ _ O\n" + " _ _ O _\n" + "O O O _ _";
    assertEquals(currentBoard3, view.toString());
    assertFalse(model.isGameOver());

    model.move(4, 1, 4, 3);
    model.move(2, 2, 4, 2);
    model.move(4, 3, 4, 1);
    model.move(4, 0, 4, 2);

    String currentBoardFinal = "    _\n" + "   _ _\n" + "  _ _ _\n" + " _ _ _ _\n" + "_ _ O " +
            "_ _";
    assertEquals(currentBoardFinal, view.toString());
    assertTrue(model.isGameOver());
  }

  @Test
  public void moveDisallowInvalidMoves() {
    String tsmDefault = "    _\n" + "   O O\n" + "  O O O\n" + " O O O O\n" + "O O O O O";

    init();
    // check initial shape
    assertEquals(tsmDefault, view.toString());

    // first move: move to another marble
    // second move: move an empty space
    // third move: move from (1,1) to (0,0)
    ArrayList<Integer> inputFromRow = new ArrayList<>(Arrays.asList(1, 0, 1));
    ArrayList<Integer> inputFromCol = new ArrayList<>(Arrays.asList(1, 0, 1));
    ArrayList<Integer> inputToRow = new ArrayList<>(Arrays.asList(3, 2, 0));
    ArrayList<Integer> inputToCol = new ArrayList<>(Arrays.asList(1, 2, 0));
    for (int i = 0; i < 3; i++) {
      try {
        model.move(inputFromRow.get(i), inputFromCol.get(i), inputToRow.get(i), inputToCol.get(i));
        fail("The move should not be valid. ");
      } catch (IllegalArgumentException e) {
        assertEquals("Invalid move (row, col): (" + (inputFromRow.get(i) + 1) + ", "
                + (inputFromCol.get(i) + 1) + ") to (" + (inputToRow.get(i) + 1) + ", "
                + (inputToCol.get(i) + 1) + "). ", e.getMessage());
      }
    }
  }

  @Test
  public void getScoreWorks() {
    init();
    assertEquals(14, model.getScore());
    assertEquals(35, centerModel.getScore());
    assertEquals(35, model2.getScore());
    TriangleSolitaireModel modelDim1 = new TriangleSolitaireModel(1);
    assertEquals(0, modelDim1.getScore());
  }
}