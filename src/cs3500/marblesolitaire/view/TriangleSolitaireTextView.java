package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.Utils;

/**
 * This class represents the text view for triangle solitaire.
 */
public class TriangleSolitaireTextView extends MarbleSolitaireTextView {

  /**
   * Constructs a view of the Triangle Marble Solitaire game given the model to the designated
   * output.
   *
   * @param model the model of the Triangle Marble Solitaire game to be viewed
   * @throws IllegalArgumentException if either of the fields are null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model)
          throws IllegalArgumentException {
    super(model, System.out);
  }

  /**
   * Constructs a view of the Triangle Marble Solitaire game given the model to the designated
   * output.
   *
   * @param model the model of the Triangle Marble Solitaire game to be viewed
   * @param out   the destination of this view
   * @throws IllegalArgumentException if either of the fields are null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable out)
          throws IllegalArgumentException {
    super(model, out);
  }

  @Override
  public String toString() throws IllegalStateException {
    StringBuilder stringBuilder = new StringBuilder();

    try {
      Utils.renderTriangleHelper(this.model, stringBuilder);
    } catch (IllegalStateException e) {
      throw new IllegalStateException("Cannot construct the string. ");
    }
    return stringBuilder.toString();
  }

  @Override
  public void renderBoard() throws IllegalStateException {
    Utils.renderTriangleHelper(this.model, this.out);
  }
}
