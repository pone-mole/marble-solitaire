package cs3500.marblesolitaire.model.hw02;

import java.io.IOException;

import cs3500.marblesolitaire.controller.Pair;
import cs3500.marblesolitaire.controller.Pair.ReadState;
import cs3500.marblesolitaire.model.hw04.EnglishSolitaireFactory;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireFactory;
import cs3500.marblesolitaire.model.hw04.MarbleSolitaireAbstractFactory;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireFactory;

import static cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;

/**
 * Represents a Utility class for checking inputs.
 */
public class Utils {
  /**
   * Return the string representation of the input slot state.
   *
   * @param slot the slot state to be printed out as a string
   * @return string representation of the slot state provided
   */
  public static String slotToString(SlotState slot) {
    String toReturn;

    switch (slot) {
      case Marble:
        toReturn = "O";
        break;
      case Empty:
        toReturn = "_";
        break;
      case Invalid:
        toReturn = " ";
        break;
      default:
        throw new IllegalArgumentException("Invalid SlotState type. ");
    }
    return toReturn;
  }

  /**
   * Render the triangle solitaire board to the provided output destination.
   *
   * @param model  the triangle solitaire model to be rendered
   * @param output the designated destination of the render
   * @throws IllegalStateException if the transmission of the board to the destination fails
   */
  public static void renderTriangleHelper(MarbleSolitaireModelState model, Appendable output)
          throws IllegalStateException {
    // y coordinate/pos row
    for (int i = 0; i < model.getBoardSize(); i++) {
      // x coordinate/pos col
      for (int j = 0; j <= i; j++) {
        SlotState current = model.getSlotAt(i, j);

        // rendering the first slot in the row
        if (j == 0) {
          // adding spaces in front
          for (int k = 0; k < model.getBoardSize() - i - 1; k++) {
            try {
              output.append(" ");
            } catch (IOException e) {
              throw new IllegalStateException("Cannot render message. ");
            }
          }
        }
        // render last slot in the row
        if (j == i) {
          // if not the last slot in the board
          if (j != model.getBoardSize() - 1) {
            try {
              output.append(slotToString(current)).append("\n");
              continue;
            } catch (IOException e) {
              throw new IllegalStateException("Cannot append string. ");
            }
          } else {
            try {
              output.append(slotToString(current));
              continue;
            } catch (IOException e) {
              throw new IllegalStateException("Cannot append string. ");
            }
          }
        }

        // render any other slots
        if (j < model.getBoardSize() - 1) {
          try {
            output.append(slotToString(current)).append(" ");
          } catch (IOException e) {
            throw new IllegalStateException("Cannot append string. ");
          }
        }
      }
    }
  }

  /**
   * Render board to the provided output destination.
   *
   * @param model  the model with the board to be rendered
   * @param output the designated destination of the render
   * @throws IllegalStateException if transmission of the board to the destination fails
   */
  public static void renderHelper(MarbleSolitaireModelState model, Appendable output)
          throws IllegalStateException {
    SlotState prev = SlotState.Invalid;
    // y coordinate/pos row
    for (int i = 0; i < model.getBoardSize(); i++) {
      // x coordinate/pos col
      for (int j = 0; j < model.getBoardSize(); j++) {
        SlotState current = model.getSlotAt(i, j);

        // if the current slot is the left edge of the board
        if (j == 0) {
          try {
            output.append(slotToString(current));
          } catch (IOException e) {
            throw new IllegalStateException("Cannot append string. ");
          }
          prev = current;
          continue;
        }

        // if the current slot is the right edge of the board
        if (renderRightEdge(prev, model, i, j)) {
          // if last row
          if (i == (model.getBoardSize() - 1)) {
            return;
          } else {
            try {
              output.append("\n");
              prev = SlotState.Invalid;
              break;
            } catch (IOException e) {
              throw new IllegalStateException("Cannot append string. ");
            }
          }
        }

        // if the current slot has col equal to the size of the board
        if (j == model.getBoardSize() - 1) {
          try {
            output.append(" ").append(slotToString(current)).append("\n");
            prev = current;
          } catch (IOException e) {
            throw new IllegalStateException("Cannot append string. ");
          }
        } else {
          try {
            output.append(" ").append(slotToString(current));
            prev = current;
          } catch (IOException e) {
            throw new IllegalStateException("Cannot append string. ");
          }
        }
      }
    }
  }

  /**
   * A helper for reading user input for Marble Solitaire Controller.
   *
   * @param input the String to be assessed without space
   * @return a Pair that represents the value processed. Returns an invalid pair with end line mark
   *         if there is nothing else to return from the scanner
   */
  public static Pair readHelper(String input) {

    // if the string is q
    if (input.equalsIgnoreCase("q")) {
      return new Pair(ReadState.Quit, "q");
    }

    try {
      int i = Integer.parseInt(input);
      if (i > 0) {
        // if string is valid
        return new Pair(ReadState.Valid, input);
      } else {
        // if string is less or equal to zero
        return new Pair(ReadState.Invalid, input);
      }
    } catch (NumberFormatException e) {
      // if string is gibberish
      return new Pair(ReadState.Invalid, input);
    }
  }

  // return if the current slot is the right edge and not the last row
  private static boolean renderRightEdge(SlotState prev, MarbleSolitaireModelState model, int row,
                                         int col) {
    SlotState current = model.getSlotAt(row, col);

    boolean isEdgeNonBoardSize = prev != SlotState.Invalid && current == SlotState.Invalid;
    boolean isNotLeftSide = col > 0;

    return isEdgeNonBoardSize && isNotLeftSide;
  }

  /**
   * Returns the correct default factory based on the provided input.
   *
   * @param name the name of the desired version of Marble Solitaire
   * @return the correct version of Marble Solitaire factory
   * @throws IllegalArgumentException if the string provided does not correspond with an existing
   *                                  factory
   */
  public static MarbleSolitaireAbstractFactory getFactory(String name) {
    if (!name.equalsIgnoreCase("triangle")) {
      return getFactory(name, 3);
    } else {
      return getFactory(name, 5);
    }
  }

  /**
   * Returns the correct factory based on the provided input with default center empty slot.
   *
   * @param name           the name of the desired version of Marble Solitaire
   * @param boardDimension the dimension of the board
   * @return the correct version of Marble Solitaire factory
   * @throws IllegalArgumentException if the string provided does not correspond with an existing
   *                                  * factory
   */
  public static MarbleSolitaireAbstractFactory getFactory(String name, int boardDimension)
          throws IllegalArgumentException {
    if (!name.equalsIgnoreCase("triangle")) {
      int midPoint = ((boardDimension * 3) - 3) / 2;
      return getFactory(name, boardDimension, midPoint, midPoint);
    } else {
      return getFactory(name, boardDimension, 0, 0);
    }
  }

  /**
   * Returns the correct factory based on the provided input with the default dimension.
   *
   * @param name the name of the desired version of Marble Solitaire
   * @param row  the row of the empty slot
   * @param col  the column of the empty slot
   * @return the correct version of Marble Solitaire factory
   * @throws IllegalArgumentException if the string provided does not correspond with an existing
   *                                  factory
   */
  public static MarbleSolitaireAbstractFactory getFactory(String name, int row, int col)
          throws IllegalArgumentException {
    if (!name.equalsIgnoreCase("triangle")) {
      return getFactory(name, 3, row, col);
    } else {
      return getFactory(name, 5, row, col);
    }
  }

  /**
   * Returns the correct factory based on the provided input with default center empty slot.
   *
   * @param name           the name of the desired version of Marble Solitaire
   * @param boardDimension the dimension of the board
   * @param row            the row of the empty slot
   * @param col            the column of the empty slot
   * @return the correct version of Marble Solitaire factory
   * @throws IllegalArgumentException if the string provided does not correspond with an existing
   *                                  * factory
   */
  public static MarbleSolitaireAbstractFactory getFactory(String name, int boardDimension,
                                                          int row, int col)
          throws IllegalArgumentException {

    switch (name) {
      case "english":
        MarbleSolitaireAbstractFactory factory = new EnglishSolitaireFactory(boardDimension, row,
                col);
        return factory;
      case "european":
        factory = new EuropeanSolitaireFactory(boardDimension, row, col);
        return factory;
      case "triangle":
        factory = new TriangleSolitaireFactory(boardDimension, row, col);
        return factory;
      default:
        throw new IllegalArgumentException("The provided name does not have an according factory "
                + "to return. ");
    }
  }
}
