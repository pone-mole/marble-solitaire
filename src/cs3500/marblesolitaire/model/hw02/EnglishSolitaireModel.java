package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.AbstractSolitaireModel;

/**
 * An EnglishSolitaireModel represents a game of Marble Solitaire.
 */
public class EnglishSolitaireModel extends AbstractSolitaireModel {

  /**
   * Constructs the default set up of the Marble Solitaire game where the arm thickness is 3 and
   * the empty tile is in the center of the board.
   */
  public EnglishSolitaireModel() {
    this(3, 3, 3);
  }

  /**
   * Constructs a Marble Solitaire game where the arm thickness is 3 and the empty tile is as the
   * designated location.
   *
   * @param sRow the designated row of the empty tile
   * @param sCol the designated column of the empty tile
   * @throws IllegalArgumentException if either argument falls in an invalid space
   */
  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this(3, sRow, sCol);
  }

  /**
   * Constructs a Marble Solitaire game where the arm thickness is as designated
   * and the empty tile is at the center of the board.
   *
   * @param armThickness the number of marbles in the top row
   * @throws IllegalArgumentException if the argument is negative, equals to 0 or even
   */
  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    this(armThickness, (armThickness * 3 - 3) / 2, (armThickness * 3 - 3) / 2);
  }

  /**
   * Constructs a Marble Solitaire game where the arm thickness and the empty tile is at the
   * designated location.
   *
   * @param armThickness the number of marbles in the top row
   * @param sRow         the designated row of the empty tile
   * @param sCol         the designated column of the empty tile
   * @throws IllegalArgumentException if argument armThickness is negative, zero or even
   * @throws IllegalArgumentException if either argument sRow or sCol has a value out of bound or
   *                                  in an invalid space
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol)
          throws IllegalArgumentException {
    super(armThickness, sRow, sCol);
  }

  // Format the board at the beginning of the game to appropriate dimension without the empty slot
  protected void setBoard(int armThickness) {
    setBoardHelp(armThickness);
  }

  private void setBoardHelp(int armThickness) {
    if (armThickness % 2 != 1) {
      throw new IllegalArgumentException("Arm thickness needs to be an odd number that is "
              + "larger than 0. ");
    } else {
      // set up all slots as invalid slots
      for (int i = 0; i < this.getBoardSize(); i++) {
        for (int j = 0; j < this.getBoardSize(); j++) {
          this.board[i][j] = SlotState.Invalid;
        }
      }
      setBoardVertical(armThickness);
      setBoardHorizontal(armThickness);
    }
  }

  // Format the vertical columns of the board game
  private void setBoardVertical(int armThickness) {
    for (int i = armThickness - 1; i < armThickness * 2 - 1; i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        this.board[i][j] = SlotState.Marble;
      }
    }
  }

  // Format the horizontal columns of the board game
  private void setBoardHorizontal(int armThickness) {
    for (int i = armThickness - 1; i < armThickness * 2 - 1; i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        this.board[j][i] = SlotState.Marble;
      }
    }
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (isMovable(fromRow, fromCol, toRow, toCol)) {
      this.board[fromCol][fromRow] = SlotState.Empty;
      this.board[toCol][toRow] = SlotState.Marble;
      if (fromRow == toRow) {
        this.board[Math.min(fromCol, toCol) + 1][fromRow] = SlotState.Empty;
      } else {
        this.board[fromCol][Math.min(fromRow, toRow) + 1] = SlotState.Empty;
      }
    } else {
      throw new IllegalArgumentException("Invalid move (row, col): (" + (fromRow + 1) + ", "
              + (fromCol + 1) + ") to (" + (toRow + 1) + ", " + (toCol + 1) + "). ");
    }
  }

  // return true if this slot is movable
  @Override
  protected boolean isMovable(int fromRow, int fromCol, int toRow, int toCol) {
    // check if from is valid and has a marble
    boolean checkFrom = checkValidSlot(fromRow, fromCol, SlotState.Marble);
    // check if to is valid and has an empty
    boolean checkTo = checkValidSlot(toRow, toCol, SlotState.Empty);

    if (checkFrom && checkTo) {
      // check if to and from is exactly 2 pos away (horizontally or vertically)
      boolean checkHorizontal = ((fromRow == toRow) && Math.abs(fromCol - toCol) == 2);
      boolean checkVertical = ((fromCol == toCol) && Math.abs(fromRow - toRow) == 2);

      // check if there is a marble between to and from
      if (checkHorizontal) {
        return checkValidSlot(fromRow, Math.min(fromCol, toCol) + 1, SlotState.Marble);
      } else if (checkVertical) {
        return checkValidSlot(Math.min(fromRow, toRow) + 1, fromCol, SlotState.Marble);
      } else {
        return false;
      }
    }
    return false;
  }

  @Override
  public boolean isGameOver() {
    boolean result = false;

    // check every slot on the board if there is at least one that is movable
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {

        // check if the current slot is a Marble
        if (checkValidSlot(i, j, SlotState.Marble)) {
          // check if the current slot can move to a slot 2 slots above or below it
          for (int k = i - 2; k <= i + 2; k = k + 4) {
            if (k >= 0 && k < this.getBoardSize()) {
              result = result || isMovable(i, j, k, j);
            }
          }

          // check if the current slot can move to a slot 2 slots to the left or the right of it
          for (int l = j - 2; l <= i + 2; l = l + 4) {
            if (l >= 0 && l < this.getBoardSize()) {
              result = result || isMovable(i, j, i, l);
            }
          }
        }
      }
    }
    return !result;
  }

  @Override
  public int getBoardSize() {
    return this.dimension * 3 - 2;
  }
}