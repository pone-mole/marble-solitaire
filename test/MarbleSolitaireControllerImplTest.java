import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MockModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class represents tests for Marble Solitaire Controller.
 */
public class MarbleSolitaireControllerImplTest extends ControllerImplStatic {

  @Test
  public void controllerConsDisallowNullModel() {
    init();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model1);

    try {
      MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(null,
              view, new StringReader("hello. "));
      fail("The constructor for controller should not take a null model. ");
    } catch (IllegalArgumentException e) {
      assertEquals("Arguments to construct controller cannot be null. ", e.getMessage());
    }
  }

  @Test
  public void controllerConsDisallowNullView() {
    init();

    try {
      MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model1,
              null, new StringReader("hello. "));
      fail("The constructor for controller should not take a null model. ");
    } catch (IllegalArgumentException e) {
      assertEquals("Arguments to construct controller cannot be null. ", e.getMessage());
    }
  }

  @Test
  public void controllerConsDisallowNullReadable() {
    init();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model1);

    try {
      MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model1,
              view, null);
      fail("The constructor for controller should not take a null model. ");
    } catch (IllegalArgumentException e) {
      assertEquals("Arguments to construct controller cannot be null. ", e.getMessage());
    }
  }

  @Test
  public void mockCheckInput() {
    ArrayList<String> testInput = new ArrayList<>(Arrays.asList(" 2 4 potato 2 2 q", "1 2 3 4 q",
            " 2 4 6 3 8 2 5 2 q"));
    ArrayList<String> expectedValues = new ArrayList<>(Arrays.asList("1 3 1 1 ", "0 1 2 3 ",
            "1 3 5 2 7 1 4 1 "));

    for (int i = 0; i < 3; i++) {
      StringReader controllerInput = new StringReader(testInput.get(i));

      Appendable inputReceived = new StringBuilder();
      MockModel model = new MockModel(inputReceived);

      Appendable controllerOutput = new StringBuilder();
      MarbleSolitaireView view = new MarbleSolitaireTextView(model, controllerOutput);
      MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view,
              controllerInput);

      controller.playGame();
      assertEquals(expectedValues.get(i), inputReceived.toString());
    }
  }

  // test invalid inputs: less than 4 int, extra int after a valid input, empty string
  @Test
  public void playGameTestingModelException() {
    ArrayList<String> testInputs = new ArrayList<>(Arrays.asList("1 2 3", "4 2 4 4 3", ""));
    for (String input : testInputs) {
      StringReader controllerInput = new StringReader(input);

      Appendable controllerOutput = new StringBuilder();

      init();
      MarbleSolitaireTextView view = new MarbleSolitaireTextView(model1, controllerOutput);
      try {
        MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model1, view,
                controllerInput);
        controller.playGame();
      } catch (IllegalStateException e) {
        assertEquals("Controller ran out of input. ", e.getMessage());
      }
    }
  }

  @Test
  // testing input of invalid moves and input out of bound
  public void controllerMessageInvalidInput() {
    ArrayList<String> listInput = new ArrayList<>(Arrays.asList("1 2 3 4 q", "999 1 2 4 q"));
    ArrayList<String> listExpectedOutput = new ArrayList<>(Arrays.asList(outputGameQuitAfter1234,
            outputInputOutOfBound));

    for (int i = 0; i < 2; i++) {
      StringReader controllerInput = new StringReader(listInput.get(i));

      Appendable controllerOutput = new StringBuilder();

      init();
      MarbleSolitaireTextView view = new MarbleSolitaireTextView(model1, controllerOutput);
      MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model1, view,
              controllerInput);
      controller.playGame();

      assertEquals(listExpectedOutput.get(i), controllerOutput.toString());
    }
  }

  @Test
  public void controllerGameQuitWorks() {
    String[] testInput = {"4 2 4 4 q", "q 4 2 4 4", "q", "4 q 2 4 4", "4 2 q 4 4", " 4 2 4 q 4"};
    String expected;

    for (String input : testInput) {
      if (input.equals("4 2 4 4 q")) {
        expected = outputGameQuit2;
      } else {
        expected = outputGameQuit1;
      }

      StringReader controllerInput = new StringReader(input);

      Appendable controllerOutput = new StringBuilder();

      init();
      MarbleSolitaireTextView view = new MarbleSolitaireTextView(model1, controllerOutput);
      MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model1, view,
              controllerInput);
      controller.playGame();

      assertEquals(expected, controllerOutput.toString());
    }
  }

  // test playing full game and extra inputs after game is over
  @Test
  public void controllerGameOverWorks1() {
    String[] testInput = {inputFullDefaultGame, inputFullDefaultGame + " 1 2 3 4"};

    for (String input : testInput) {
      StringReader controllerInput = new StringReader(input);

      Appendable controllerOutput = new StringBuilder();

      init();
      MarbleSolitaireTextView view = new MarbleSolitaireTextView(model1, controllerOutput);
      MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model1, view,
              controllerInput);
      controller.playGame();

      assertEquals(outputFullDefaultGame, controllerOutput.toString());
    }
  }
}