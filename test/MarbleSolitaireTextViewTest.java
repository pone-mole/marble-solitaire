import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for MarbleSolitaireTextView.
 */
public class MarbleSolitaireTextViewTest extends EnglishSolitaireModelTest {

  @Test
  public void MarbleSolitaireTextViewCons1DisallowNull() {
    try {
      MarbleSolitaireTextView model = new MarbleSolitaireTextView(null);
      fail("The first constructor for Marble Solitaire Text View should not accept null. ");
    }
    catch (IllegalArgumentException e) {
      assertEquals("Both model and output appendable cannot be null. ", e.getMessage());
    }
  }

  @Test
  public void MarbleSolitaireTextViewCons2DisallowNullOut() {
    init();
    try {
      MarbleSolitaireTextView model = new MarbleSolitaireTextView(model1, null);
      fail("The second constructor for Marble Solitaire Text View should not accept null. ");
    }
    catch (IllegalArgumentException e) {
      assertEquals("Both model and output appendable cannot be null. ", e.getMessage());
    }
  }

  @Test
  public void MarbleSolitaireTextViewCons2DisallowNullModel() {
    try {
      MarbleSolitaireTextView model = new MarbleSolitaireTextView(null, new StringBuilder());
      fail("The second constructor for Marble Solitaire Text View should not accept null. ");
    }
    catch (IllegalArgumentException e) {
      assertEquals("Both model and output appendable cannot be null. ", e.getMessage());
    }
  }

  @Test
  public void testToString() {
    init();
    EnglishSolitaireModel model5 = esmCons3(9);
    MarbleSolitaireTextView modelView = new MarbleSolitaireTextView(model5);
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
    assertEquals(printedModel5, modelView.toString());
  }

  @Test
  public void renderBoardThrowExceptionFailAppendable() {
    init();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model1, new FailAppendable());

    try {
      view.renderBoard();
      fail("The board should not render with a faulty appendable. ");
    } catch (IllegalStateException e) {
      assertEquals("Cannot append string. ", e.getMessage());
    }
  }

  @Test
  public void renderMessageWorks() {
    init();
    StringBuilder stringBuilder = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model1, stringBuilder);

    try {
      view.renderMessage("hello. ");
      assertEquals("hello. ", stringBuilder.toString());
    } catch (IllegalStateException e) {
      throw new IllegalStateException("The method renderMessage should render the message 'hello."
              + " '");
    }
  }

  @Test
  public void testRenderBoardThenRenderMessage() {
    init();
    StringBuilder stringBuilder = new StringBuilder();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model1, stringBuilder);
    String model1String =
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O";

    try {
      view.renderBoard();
      assertEquals(model1String, stringBuilder.toString());
    } catch (IllegalStateException e) {
      throw new IllegalStateException("The method renderBoard should render the board exactly " +
              "as model1String. ");
    }

    String model1StringHello =
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O\n"
                    + "hello. \n";

    try {
      view.renderMessage("\nhello. \n");
      assertEquals(model1StringHello, stringBuilder.toString());
    } catch (IllegalStateException e) {
      throw new IllegalStateException("The method renderMessage should render the message 'hello."
              + " '");
    }
  }

  @Test
  public void renderMessageThrowExceptionFailAppendable() {
    init();
    MarbleSolitaireTextView view = new MarbleSolitaireTextView(model1, new FailAppendable());

    try {
      view.renderMessage("hi. ");
      fail("The message should not render with a faulty appendable. ");
    } catch (IllegalStateException e) {
      assertEquals("The transmission of the board " +
              "to the provided data destination fails", e.getMessage());
    }
  }

  // test the set up of each of the four constructors
  @Test
  public void cons1SetUpTest() {
    String model1String =
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O O O\n"
                    + "O O O _ O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O";
    init();
    MarbleSolitaireTextView modelView = new MarbleSolitaireTextView(model1);
    assertEquals(model1String, modelView.toString());
  }

  @Test
  public void cons2SetUpTest() {
    String model2String =
            "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O _ O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O";
    init();
    MarbleSolitaireTextView modelView = new MarbleSolitaireTextView(model2);
    assertEquals(model2String, modelView.toString());
  }

  @Test
  public void cons3SetUpTest() {
    String model3String =
            "    O O O\n"
                    + "    O O O\n"
                    + "O O O O O _ O\n"
                    + "O O O O O O O\n"
                    + "O O O O O O O\n"
                    + "    O O O\n"
                    + "    O O O";
    init();
    MarbleSolitaireTextView modelView = new MarbleSolitaireTextView(model3);
    assertEquals(model3String, modelView.toString());
  }

  @Test
  public void cons4SetUpTest() {
    String model4String =
            "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "O O O _ O O O O O O O O O\n"
                    + "O O O O O O O O O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O\n"
                    + "        O O O O O";
    init();
    MarbleSolitaireTextView modelView = new MarbleSolitaireTextView(model4);
    assertEquals(model4String, modelView.toString());
  }
}