import org.junit.Test;

import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * This class represents tests for EuropeanSolitaireModel.
 */
public class EuropeanSolitaireModelTest {
  static String defaulteusm = "    O O O\n"
          + "  O O O O O\n"
          + "O O O O O O O\n"
          + "O O O _ O O O\n"
          + "O O O O O O O\n"
          + "  O O O O O\n"
          + "    O O O";
  static String eusmWidth5 = "        O O O O O\n"
          + "      O O O O O O O\n"
          + "    O O O O O O O O O\n"
          + "  O O O O O O O O O O O\n"
          + "O O O O O O O O O O O O O\n"
          + "O O O O O O O O O O O O O\n"
          + "O O O O O O _ O O O O O O\n"
          + "O O O O O O O O O O O O O\n"
          + "O O O O O O O O O O O O O\n"
          + "  O O O O O O O O O O O\n"
          + "    O O O O O O O O O\n"
          + "      O O O O O O O\n"
          + "        O O O O O";

  static String eusmWidth5Empt3_3 = "        O O O O O\n"
          + "      O O O O O O O\n"
          + "    O O O O O O O O O\n"
          + "  O O _ O O O O O O O O\n"
          + "O O O O O O O O O O O O O\n"
          + "O O O O O O O O O O O O O\n"
          + "O O O O O O O O O O O O O\n"
          + "O O O O O O O O O O O O O\n"
          + "O O O O O O O O O O O O O\n"
          + "  O O O O O O O O O O O\n"
          + "    O O O O O O O O O\n"
          + "      O O O O O O O\n"
          + "        O O O O O";

  @Test
  public void eusmDisallowInvalidSideLength() {
    try {
      EuropeanSolitaireModel model = new EuropeanSolitaireModel(-100);
      fail("The model should not accept negative value for sideLength. ");
    } catch (IllegalArgumentException e) {
      assertEquals("The dimension has to be a positive value. ", e.getMessage());
    }

    try {
      EuropeanSolitaireModel model = new EuropeanSolitaireModel(0);
      fail("The model should not accept zero for sideLength. ");
    } catch (IllegalArgumentException e) {
      assertEquals("The dimension has to be a positive value. ", e.getMessage());
    }

    try {
      EuropeanSolitaireModel model = new EuropeanSolitaireModel(6);
      fail("The model should not accept even value for sideLength. ");
    } catch (IllegalArgumentException e) {
      assertEquals("Arm thickness needs to be an odd number that is "
              + "larger than 0. ", e.getMessage());
    }
  }

  @Test
  public void eusmDisallowNegativeEmptySlot() {
    // negative row, valid col
    try {
      EuropeanSolitaireModel model = new EuropeanSolitaireModel(3, -100, 3);
      fail("The model should not accept negative value for row. ");
    } catch (IllegalArgumentException e) {
      assertEquals("Indicated slot out of bound (row, col): (-99,4)", e.getMessage());
    }

    // valid row, negative col
    try {
      EuropeanSolitaireModel model = new EuropeanSolitaireModel(3, 3, -100);
      fail("The model should not accept even value for sideLength. ");
    } catch (IllegalArgumentException e) {
      assertEquals("Indicated slot out of bound (row, col): (4,-99)", e.getMessage());
    }
  }

  @Test
  public void eusmDisallowInvalidEmptySlot() {
    // empty slot out of bound positive
    try {
      EuropeanSolitaireModel model = new EuropeanSolitaireModel(3, 7, 7);
      fail("The model should not accept even value for sideLength. ");
    } catch (IllegalArgumentException e) {
      assertEquals("Indicated slot out of bound (row, col): (8,8)", e.getMessage());
    }

    // empty slot on invalid slot
    try {
      EuropeanSolitaireModel model = new EuropeanSolitaireModel(3, 0, 0);
      fail("The model should not accept even value for sideLength. ");
    } catch (IllegalArgumentException e) {
      assertEquals("The indicated position for empty slot is invalid. ", e.getMessage());
    }
  }

  @Test
  public void eusmCorrectSetup() {
    EuropeanSolitaireModel model = new EuropeanSolitaireModel();
    EuropeanSolitaireModel model2 = new EuropeanSolitaireModel(5);
    EuropeanSolitaireModel model3 = new EuropeanSolitaireModel(5, 3, 3);
    EuropeanSolitaireModel model4 = new EuropeanSolitaireModel(1);

    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model);
    MarbleSolitaireTextView view2 = new MarbleSolitaireTextView(model2);
    MarbleSolitaireTextView view3 = new MarbleSolitaireTextView(model3);
    MarbleSolitaireTextView view4 = new MarbleSolitaireTextView(model4);

    assertEquals(defaulteusm, view.toString());
    assertEquals(eusmWidth5, view2.toString());
    assertEquals(eusmWidth5Empt3_3, view3.toString());
    assertEquals("_", view4.toString());
  }

  @Test // for board of the same arm width/side length
  public void moveValidInEuButNotEng() {
    String expectedOutput = "    O O O\n"
            + "  _ O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O";
    String expectedOutputChanged = "    O O O\n"
            + "  O O O O O\n"
            + "O _ O O O O O\n"
            + "O _ O O O O O\n"
            + "O O O O O O O\n"
            + "  O O O O O\n"
            + "    O O O";

    EuropeanSolitaireModel model = new EuropeanSolitaireModel(1, 1);
    MarbleSolitaireTextView textView = new MarbleSolitaireTextView(model);

    assertEquals(expectedOutput, textView.toString());

    model.move(3, 1, 1, 1);
    assertNotEquals(expectedOutput, textView.toString());
    assertEquals(expectedOutputChanged, textView.toString());
  }
}