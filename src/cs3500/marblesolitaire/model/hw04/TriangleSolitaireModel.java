package cs3500.marblesolitaire.model.hw04;

/**
 * This class represents a triangle solitaire model.
 */
public class TriangleSolitaireModel extends AbstractSolitaireModel {

  /**
   * Creates a default triangle solitaire model with the empty slot at (0,0).
   */
  public TriangleSolitaireModel() {
    this(5, 0, 0);
  }

  /**
   * Creates a triangle solitaire model with the indicated dimension (number of slots at the
   * bottom-most row and the empty slot at (0,0).
   *
   * @param dimension the dimension of the game
   * @throws IllegalArgumentException if the dimension provided is non-positive
   */
  public TriangleSolitaireModel(int dimension) throws IllegalArgumentException {
    this(dimension, 0, 0);
  }

  /**
   * Creates a triangle solitaire model with 5 rows and empty slot at indicated position.
   *
   * @param row the row of the empty slot
   * @param col the column of the empty slot
   * @throws IllegalArgumentException if the position of the empty slot is invalid (not a marble)
   */
  public TriangleSolitaireModel(int row, int col) throws IllegalArgumentException {
    this(5, row, col);
  }

  /**
   * Creates a triangle solitaire model with the specified dimension and location of empty slot.
   *
   * @param dimension the dimension of the board
   * @param row       the row of the empty slot
   * @param col       the column of the empty slot
   * @throws IllegalArgumentException if the dimension is non-positive or the position of the
   *                                  empty slot is invalid
   */
  public TriangleSolitaireModel(int dimension, int row, int col) throws IllegalArgumentException {
    super(dimension, row, col);
  }

  @Override
  protected void setBoard(int dimension) {
    int boardSize = this.getBoardSize();
    // set up all slots as invalid
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        this.board[i][j] = SlotState.Invalid;
      }
    }

    // set up marble slots
    for (int k = 0; k < boardSize; k++) {
      for (int l = 0; l <= k; l++) {
        this.board[l][k] = SlotState.Marble;
      }
    }
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (isMovable(fromRow, fromCol, toRow, toCol)) {
      this.board[fromCol][fromRow] = SlotState.Empty;
      this.board[toCol][toRow] = SlotState.Marble;
      // same row
      if ((fromRow == toRow) && (Math.abs(fromCol - toCol) == 2)) {
        this.board[Math.min(fromCol, toCol) + 1][fromRow] = SlotState.Empty;
      }
      // same right diagonal
      if ((fromCol == toCol) && (Math.abs(fromRow - toRow) == 2)) {
        this.board[fromCol][Math.min(fromRow, toRow) + 1] = SlotState.Empty;
      }
      // same left diagonal
      if (Math.abs(fromCol - toCol) == Math.abs(fromRow - toRow)) {
        this.board[Math.min(fromCol, toCol) + 1][Math.min(fromRow, toRow) + 1] = SlotState.Empty;
      }
    } else {
      throw new IllegalArgumentException("Invalid move (row, col): (" + (fromRow + 1) + ", "
              + (fromCol + 1) + ") to (" + (toRow + 1) + ", " + (toCol + 1) + "). ");
    }
  }

  @Override
  public int getBoardSize() {
    return this.dimension;
  }

  @Override
  protected boolean isMovable(int fromRow, int fromCol, int toRow, int toCol) {
    // check if from is valid and has a marble
    boolean checkFrom = checkValidSlot(fromRow, fromCol, SlotState.Marble);
    // check if to is valid and has an empty
    boolean checkTo = checkValidSlot(toRow, toCol, SlotState.Empty);

    if (checkFrom && checkTo) {
      // same row
      boolean isSameRow = (fromRow == toRow) && (Math.abs(fromCol - toCol) == 2);
      // same right diagonal
      boolean isSameRightDiag = (fromCol == toCol) && (Math.abs(fromRow - toRow) == 2);
      // same left diagonal
      boolean isSameLeftDiag =
              (Math.abs(fromCol - toCol) == 2) && (Math.abs(fromRow - toRow) == 2)
                      && ((fromCol - toCol) - (fromRow - toRow) == 0);

      if (isSameRow) {
        return checkValidSlot(fromRow, Math.min(fromCol, toCol) + 1, SlotState.Marble);
      }
      if (isSameRightDiag) {
        return checkValidSlot(Math.min(fromRow, toRow) + 1, fromCol, SlotState.Marble);
      }
      if (isSameLeftDiag) {
        return checkValidSlot(Math.min(fromRow, toRow) + 1, Math.min(fromCol, toCol) + 1,
                SlotState.Marble);
      } else {
        return false;
      }
    }
    return false;
  }

  @Override
  public boolean isGameOver() {
    boolean result = false;

    // checking every marble slot
    // row
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j <= i; j++) {
        // check if the current slot is a Marble
        if (checkValidSlot(i, j, SlotState.Marble)) {
          // check if the current slot can move to a slot 2 slots above or below it
          for (int k = i - 2; k <= i + 2; k = k + 4) {
            for (int l = j - 2; l <= i + 2; l = l + 4) {
              // check if the slot can move on the right diagonal
              if (k >= 0 && k < this.getBoardSize()) {
                result = result || isMovable(i, j, k, j);
              }
              // check if the slot can move horizontally
              if (l >= 0 && l < this.getBoardSize()) {
                result = result || isMovable(i, j, i, l);
              }
              // check if the slot can move on the left diagonal
              if ((k >= 0 && k < this.getBoardSize()) && (l >= 0 && l < this.getBoardSize())) {
                result = result || isMovable(i, j, k, l);
              }
            }
          }
        }
      }
    }
    return !result;
  }
}
