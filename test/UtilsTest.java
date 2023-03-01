import org.junit.Test;

import cs3500.marblesolitaire.controller.Pair;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.Utils;
import cs3500.marblesolitaire.model.hw04.MarbleSolitaireAbstractFactory;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * Tests for Utils.
 */
public class UtilsTest extends EnglishSolitaireModelTest {
  TriangleSolitaireModel model;
  TriangleSolitaireModel funnyModel; // triangle model with dimension 2 and empty slot (1,1)
  TriangleSolitaireTextView view;
  TriangleSolitaireTextView view2;

  private void initTriangle() {
    model = new TriangleSolitaireModel();
    funnyModel = new TriangleSolitaireModel(2, 1, 1);
    view = new TriangleSolitaireTextView(model);
    view2 = new TriangleSolitaireTextView(funnyModel);
  }

  @Test
  public void slotToStringTest() {
    assertEquals(" ", Utils.slotToString(SlotState.Invalid));
    assertEquals("_", Utils.slotToString(SlotState.Empty));
    assertEquals("O", Utils.slotToString(SlotState.Marble));
  }

  @Test
  public void renderHelperWorks() {
    init();
    EnglishSolitaireModel model5 = esmCons3(9);
    StringBuilder stringBuilder = new StringBuilder();
    String printedModel5 =
            "                O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O _ O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O O O O O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "                O O O O O O O O O\n"
                    + "                O O O O O O O O O";
    try {
      Utils.renderHelper(model5, stringBuilder);
      assertEquals(printedModel5, stringBuilder.toString());
    } catch (IllegalStateException e) {
      throw new IllegalStateException("The method renderHelper should render the board "
              + "accurately to printedModel5. ");
    }
  }

  @Test
  public void renderHelpThrowIOExceptionIfErrorAppendable() {
    FailAppendable appendable = new FailAppendable();

    init();

    try {
      Utils.renderHelper(model1, appendable);
      fail("The method renderHelper should throw an IOException for fail appendable. ");
    } catch (IllegalStateException e) {
      assertEquals("Cannot append string. ", e.getMessage());
    }
  }

  @Test
  public void readHelperWorks() {
    // read a positive integer
    assertEquals(new Pair(Pair.ReadState.Valid, "1"), Utils.readHelper("1"));
    // read a negative integer
    assertEquals(new Pair(Pair.ReadState.Invalid, "-10"), Utils.readHelper("-10"));
    // read a 0
    assertEquals(new Pair(Pair.ReadState.Invalid, "0"), Utils.readHelper("0"));
    // read a string that is not a q
    assertEquals(new Pair(Pair.ReadState.Invalid, "potato"), Utils.readHelper("potato"));
    // read a string that is a q
    assertEquals(new Pair(Pair.ReadState.Quit, "q"), Utils.readHelper("q"));
    // read a mixture of integer and string
    assertEquals(new Pair(Pair.ReadState.Invalid, "2384jic"), Utils.readHelper("2384jic"));
  }

  @Test
  public void triangleRenderHelperWorks() {
    String expectedOutput = "    _\n" + "   O O\n" + "  O O O\n" + " O O O O\n" + "O O O O O";
    String expectedFunnyOutput = " O\n" + "O _";
    StringBuilder output = new StringBuilder();
    StringBuilder funnyOutput = new StringBuilder();
    initTriangle();
    Utils.renderTriangleHelper(model, output);
    Utils.renderTriangleHelper(funnyModel, funnyOutput);
    assertEquals(expectedOutput, output.toString());
    assertEquals(expectedFunnyOutput, funnyOutput.toString());
  }

  @Test
  public void getFactoryTriangleThreeFieldsWorks() {
    String expectedBoard = "       O\n"
            + "      O O\n"
            + "     O O O\n"
            + "    O O O _\n"
            + "   O O O O O\n"
            + "  O O O O O O\n"
            + " O O O O O O O\n"
            + "O O O O O O O O";
    String expectedBoardChanged = "       O\n"
            + "      O O\n"
            + "     O O O\n"
            + "    O _ _ O\n"
            + "   O O O O O\n"
            + "  O O O O O O\n"
            + " O O O O O O O\n"
            + "O O O O O O O O";

    MarbleSolitaireAbstractFactory factory =
            Utils.getFactory("triangle", 8, 3, 3);
    MarbleSolitaireModel model = factory.createModel();
    MarbleSolitaireView view = factory.createView();

    // view of the correct model
    assertEquals(expectedBoard, view.toString());

    // the model returned is the same as the model in the view
    // move in the model is reflected in the view
    model.move(3, 1, 3, 3);
    assertNotEquals(expectedBoard, view.toString());
    assertEquals(expectedBoardChanged, view.toString());
  }

  @Test
  public void getFactoryEuropeanThreeFieldsWorks() {
    String expectedBoard = "        O O O O O\n"
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
    String expectedBoardChanged = "        O O O O O\n"
            + "      O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "  _ _ O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n"
            + "    O O O O O O O O O\n"
            + "      O O O O O O O\n"
            + "        O O O O O";

    MarbleSolitaireAbstractFactory factory =
            Utils.getFactory("european", 5, 3, 3);
    MarbleSolitaireModel model = factory.createModel();
    MarbleSolitaireView view = factory.createView();

    // view of the correct model
    assertEquals(expectedBoard, view.toString());

    // the model returned is the same as the model in the view
    // move in the model is reflected in the view
    model.move(3, 1, 3, 3);
    assertNotEquals(expectedBoard, view.toString());
    assertEquals(expectedBoardChanged, view.toString());
  }

  @Test
  public void getFactoryEnglishThreeFieldsWorks() {
    String expectedBoard = "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O _ O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O";
    String expectedBoardChanged = "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O _ _ O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O";

    MarbleSolitaireAbstractFactory factory =
            Utils.getFactory("english", 5, 5, 3);
    MarbleSolitaireModel model = factory.createModel();
    MarbleSolitaireView view = factory.createView();

    // view of the correct model
    assertEquals(expectedBoard, view.toString());

    // the model returned is the same as the model in the view
    // move in the model is reflected in the view
    model.move(5, 1, 5, 3);
    assertNotEquals(expectedBoard, view.toString());
    assertEquals(expectedBoardChanged, view.toString());
  }
}