import org.junit.Test;

import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class represents tests for triangle solitaire text view.
 */
public class TriangleSolitaireTextViewTest {
  TriangleSolitaireModel model;
  TriangleSolitaireTextView view;

  private void init() {
    model = new TriangleSolitaireModel();
    view = new TriangleSolitaireTextView(model, new FailAppendable());
  }

  @Test
  public void renderBoardFailWithFailAppendable() {
    init();
    try {
      view.renderBoard();
      fail("Should not render board with a fail appendable. ");
    } catch (IllegalStateException e) {
      assertEquals("Cannot render message. ", e.getMessage());
    }
  }

  @Test
  public void renderMessageFailWithFailAppendable() {
    init();
    try {
      view.renderBoard();
      fail("Should not render message with a fail appendable. ");
    } catch (IllegalStateException e) {
      assertEquals("Cannot render message. ", e.getMessage());
    }
  }

  @Test
  public void renderMessageWorks() {
    init();
    StringBuilder output = new StringBuilder();
    TriangleSolitaireTextView view1 = new TriangleSolitaireTextView(model, output);
    try {
      view1.renderMessage("hello. ");
      assertEquals("hello. ", output.toString());
    } catch (IllegalStateException e) {
      throw new IllegalStateException("View should render message with working appendable. ");
    }
  }

  @Test
  public void toStringWorks() {
    String expectedOutput = "    _\n" + "   O O\n" + "  O O O\n" + " O O O O\n" + "O O O O O";

    init();
    StringBuilder output = new StringBuilder();
    TriangleSolitaireTextView view1 = new TriangleSolitaireTextView(model, output);
    assertEquals(expectedOutput, view1.toString());
  }

  @Test
  public void renderBoardWorks() {
    String expectedOutput = "    _\n" + "   O O\n" + "  O O O\n" + " O O O O\n" + "O O O O O";

    init();
    StringBuilder output = new StringBuilder();
    TriangleSolitaireTextView view1 = new TriangleSolitaireTextView(model, output);
    view1.renderBoard();
    assertEquals(expectedOutput, output.toString());
  }
}