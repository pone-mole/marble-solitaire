package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw02.Utils;

/**
 * Represent a view of a game of Marble Solitaire.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  protected final MarbleSolitaireModelState model;
  protected final Appendable out;

  /**
   * Constructs a view of the Marble Solitaire game given the model.
   *
   * @param model the model of the Marble Solitaire game to be viewed
   * @throws IllegalArgumentException if the provided model is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) throws IllegalArgumentException {
    this(model, System.out);
  }

  /**
   * Constructs a view of the Marble Solitaire game given the model to the designated output.
   *
   * @param model the model of the Marble Solitaire game to be viewed
   * @param out   the destination of this view
   * @throws IllegalArgumentException if either of the fields are null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable out)
          throws IllegalArgumentException {
    if (model == null || out == null) {
      throw new IllegalArgumentException("Both model and output appendable cannot be null. ");
    } else {
      this.model = model;
      this.out = out;
    }
  }

  @Override
  public String toString() throws IllegalStateException {
    StringBuilder stringBuilder = new StringBuilder();

    try {
      Utils.renderHelper(this.model, stringBuilder);
    } catch (IllegalStateException e) {
      throw new IllegalStateException("Cannot construct the string. ");
    }
    return stringBuilder.toString();
  }

  @Override
  public void renderBoard() throws IllegalStateException {
    Utils.renderHelper(this.model, this.out);
  }

  @Override
  public void renderMessage(String message) throws IllegalStateException {
    try {
      this.out.append(message);
    } catch (IOException e) {
      throw new IllegalStateException("The transmission of the board to the provided " + "data "
              + "destination fails");
    }
  }
}
