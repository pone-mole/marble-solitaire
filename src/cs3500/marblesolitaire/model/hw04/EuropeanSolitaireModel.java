package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;

/**
 * This class represents an European solitaire model.
 */
public class EuropeanSolitaireModel extends EnglishSolitaireModel {

  /**
   * Default constructor for European solitaire model.
   */
  public EuropeanSolitaireModel() {
    super();
  }

  /**
   * Constructor for creating a game with the specified side length, and an empty slot at the
   * center of the board.
   *
   * @param sideLength the length of the side of the board
   * @throws IllegalArgumentException if input side length is negative, zero or even
   */
  public EuropeanSolitaireModel(int sideLength) throws IllegalArgumentException {
    super(sideLength);
  }

  /**
   * Constructor for creating a new game with the specified row and column of the empty slot on a
   * default side length 3 board.
   *
   * @param row the row of the empty slot
   * @param col the column of the empty slot
   * @throws IllegalArgumentException if either arguments fall into an invalid space
   */
  public EuropeanSolitaireModel(int row, int col) {
    super(row, col);
  }

  /**
   * Constructor for creating a new game with the specified side length, row and column of the
   * empty slot.
   *
   * @param sideLength the side length of the board
   * @param row        the row of the empty slot
   * @param col        the column of the empty slot
   * @throws IllegalArgumentException if argument armThickness is negative, zero or even
   * @throws IllegalArgumentException if either argument sRow or sCol has a value out of bound or
   *                                  in an invalid space
   */
  public EuropeanSolitaireModel(int sideLength, int row, int col) {
    super(sideLength, row, col);
    // FIXME: change the side length/arm thickness in EnglishSolitaire into a field
  }

  @Override
  protected void setBoard(int sideLength) {
    super.setBoard(sideLength);

    // set the additional section that is not covered in EnglishSolitaireModel
    setBoardLeft();
    setBoardRight();
  }

  private void setBoardLeft() {
    int counter = 0;

    // y-coord/ row
    for (int i = 1; i <= this.getBoardSize() - 2; i++) {
      if ((i >= this.dimension - 1) && (i <= this.dimension * 2 - 2)) {
        continue;
      }
      // x-coord/ col
      for (int j = this.dimension - 1; j >= this.dimension - 2 - counter; j--) {
        this.board[j][i] = SlotState.Marble;
      }
      if (i <= this.dimension - 3) {
        counter += 1;
      }
      if (i >= this.dimension * 2 - 1) {
        counter -= 1;
      }
    }
  }

  private void setBoardRight() {
    int counter = (this.getBoardSize() - this.dimension) / 2 - 1;

    // y-coord/ row
    for (int i = 1; i <= this.getBoardSize() - 2; i++) {
      if ((i >= this.dimension - 1) && (i <= this.dimension * 2 - 2)) {
        continue;
      }
      // x-coord/ col
      for (int j = this.dimension * 2 - 1; j <= this.getBoardSize() - 1 - counter; j++) {
        this.board[j][i] = SlotState.Marble;
      }
      if (i <= this.dimension - 3) {
        counter -= 1;
      }
      if (i >= this.dimension * 2 - 1) {
        counter += 1;
      }
    }
  }
}
