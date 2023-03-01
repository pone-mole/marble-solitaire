package cs3500.marblesolitaire.model.hw02;

import java.io.IOException;

/**
 * This class represents a mock model of Marble Solitaire to test input.
 */
public class MockModel implements MarbleSolitaireModel {
  private final Appendable inputReceived;

  /**
   * Constructs a mock model.
   *
   * @param inputReceived the input received from the controller
   */
  public MockModel(Appendable inputReceived) {
    this.inputReceived = inputReceived;
  }

  /**
   * Append the inputed value which initiates a move.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalStateException if the appendable cannot append the values
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalStateException {
    try {
      inputReceived.append(fromRow + " " + fromCol + " " + toRow + " " + toCol + " ");
    } catch (IOException e) {
      throw new IllegalStateException("Appendable failed at MockModel.move. ");
    }
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public int getBoardSize() {
    return 0;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    return null;
  }

  @Override
  public int getScore() {
    return 0;
  }
}
