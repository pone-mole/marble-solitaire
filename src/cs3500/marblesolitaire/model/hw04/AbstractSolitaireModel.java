package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * This abstract class represents a solitaire model that implements MarbleSolitaireModel.
 */
public abstract class AbstractSolitaireModel implements MarbleSolitaireModel {
  protected final int dimension;
  protected final SlotState[][] board;

  /**
   * Constructs an abstract Marble Solitaire game where the arm thickness and the empty tile is at
   * the designated location.
   *
   * @param dimension the number of marbles in the top row
   * @param sRow      the designated row of the empty tile
   * @param sCol      the designated column of the empty tile
   * @throws IllegalArgumentException if argument armThickness is negative, zero or even
   * @throws IllegalArgumentException if either argument sRow or sCol has a value out of bound or
   *                                  in an invalid space
   */
  public AbstractSolitaireModel(int dimension, int sRow, int sCol) throws IllegalArgumentException {
    if (dimension <= 0) {
      throw new IllegalArgumentException("The dimension has to be a positive value. ");
    } else {
      this.dimension = dimension;
      this.board = new SlotState[this.getBoardSize()][this.getBoardSize()];
      setBoard(dimension);

      // checks and adds empty slot if the designated location is valid
      if (checkValidSlot(sRow, sCol, SlotState.Marble)) {
        this.board[sCol][sRow] = SlotState.Empty;
      } else {
        throw new IllegalArgumentException("The indicated position for empty slot is invalid. ");
      }
    }
  }

  /**
   * Set up the board accordingly to the dimension and type of the board.
   *
   * @param dimension the dimension of the board
   */
  protected abstract void setBoard(int dimension) throws IllegalArgumentException;

  @Override
  public abstract void move(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException;

  /**
   * Return whether the indicated slot has the expected slot state.
   *
   * @param row           the row of the slot to be checked
   * @param col           the column of the slot to be checked
   * @param expectedState the expected state of the slot
   * @return true if the state of the indicated slot is as expected and vice versa
   * @throws IllegalArgumentException if the indicated slot is out of bounds
   */
  protected boolean checkValidSlot(int row, int col, SlotState expectedState)
          throws IllegalArgumentException {
    return getSlotAt(row, col) == expectedState;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if ((row < 0 || row >= this.getBoardSize()) || (col < 0 || col >= this.getBoardSize())) {
      throw new IllegalArgumentException("Indicated slot out of bound (row, col): ("
              + (row + 1) + "," + (col + 1) + ")");
    } else {
      return this.board[col][row];
    }
  }

  @Override
  public abstract int getBoardSize();

  /**
   * Check whether the slot at the indicated location is moveable to the indicated destination.
   *
   * @param fromRow the row of the slot to be moved
   * @param fromCol the column of the slot to be moved
   * @param toRow   the row of the slot to be moved to
   * @param toCol   the column of the slot to be moved to
   * @return true if the indicated slot can be moved to the indicated location
   * @throws IllegalArgumentException if either the slot provided is out of bound
   */
  protected abstract boolean isMovable(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException;

  @Override
  public abstract boolean isGameOver();

  @Override
  public int getScore() {
    int result = 0;
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (checkValidSlot(i, j, SlotState.Marble)) {
          result += 1;
        }
      }
    }

    return result;
  }
}
