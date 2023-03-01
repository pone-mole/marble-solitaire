package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * This class represents an English Marble Solitaire factory.
 */
public class EnglishSolitaireFactory implements MarbleSolitaireAbstractFactory {
  private final MarbleSolitaireModel model;

  /**
   * Creates an English solitaire factory.
   *
   * @param boardDimension the dimension of the board
   * @param row            the row of the empty slot
   * @param col            the column of the empty slot
   * @throws IllegalArgumentException if any of the argument is negative or zero
   */
  public EnglishSolitaireFactory(int boardDimension, int row, int col)
          throws IllegalArgumentException {
    this.model = new EnglishSolitaireModel(boardDimension, row, col);
  }

  @Override
  public MarbleSolitaireModel createModel() {
    return this.model;
  }

  @Override
  public MarbleSolitaireView createView() {
    return new MarbleSolitaireTextView(this.model);
  }

  @Override
  public MarbleSolitaireView createView(Appendable appendable) {
    return new MarbleSolitaireTextView(this.model, appendable);
  }
}
