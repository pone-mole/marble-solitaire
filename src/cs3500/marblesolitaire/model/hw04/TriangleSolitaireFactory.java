package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * This class represents a triangle solitaire factory.
 */
public class TriangleSolitaireFactory implements MarbleSolitaireAbstractFactory {
  private final MarbleSolitaireModel model;

  /**
   * Creates a triangle solitaire factory.
   *
   * @param boardDimension the dimension of the board
   * @param row            the row of the empty slot
   * @param col            the column of the empty slot
   * @throws IllegalArgumentException if any of the argument is negative or zero
   */
  public TriangleSolitaireFactory(int boardDimension, int row, int col)
          throws IllegalArgumentException {
    this.model = new TriangleSolitaireModel(boardDimension, row, col);
  }

  @Override
  public MarbleSolitaireModel createModel() {
    return this.model;
  }

  @Override
  public MarbleSolitaireView createView() {
    return new TriangleSolitaireTextView(this.model);
  }

  @Override
  public MarbleSolitaireView createView(Appendable appendable) {
    return new TriangleSolitaireTextView(this.model, appendable);
  }
}
