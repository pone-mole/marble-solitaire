package cs3500.marblesolitaire.controller;

/**
 * This interface contains all features which a Marble Solitaire Controller with GUI should have.
 */
public interface MarbleSolitaireFeature {
  /**
   * Record the input row and col; initiate a move accordingly and transmit messages if needed.
   *
   * @param row the row provided from the user
   * @param col the column provided from the user
   */
  void move(int row, int col);
}
