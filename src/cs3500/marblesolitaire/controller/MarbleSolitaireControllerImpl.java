package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.Utils;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * This class represents the implementation of the Marble Solitaire Controller. This controller
 * allows the user to play a game of Marble Solitaire.
 *
 * <p>This controller allows the user to play the game with their choices of model which belongs
 * to the MarbleSolitaireModel class, the appropriate view and their choice of readable input.
 * </p>
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private final MarbleSolitaireModel model;
  private final MarbleSolitaireView view;
  private final Readable input;

  /**
   * Constructs a controller for Marble Solitaire.
   *
   * @param model the model state of the current game currently being played
   * @param view  the view for the current model
   * @param input the input stream from which to read the input
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable input) throws IllegalArgumentException {
    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("Arguments to construct controller cannot be null. ");
    } else {
      this.model = model;
      this.view = view;
      this.input = input;
    }
  }

  @Override
  public void playGame() throws IllegalStateException {
    this.renderGame();

    while (!this.model.isGameOver()) {
      // If the game is ongoing, obtain the next user input through the Readable object
      Scanner s = new Scanner(this.input);

      LinkedList<String> list = new LinkedList<>();
      // if there is new input, start processing

      // add to the processed list if the list is less than 4 elements
      while (s.hasNext()) {
        Pair p = Utils.readHelper(s.next());

        switch (p.state) {
          case Quit:
            renderQuit();
            return;

          case Valid:
            list.add(p.value);
            break;

          case Invalid:
            try {
              this.view.renderMessage("Please re-enter a valid input.\n");
            } catch (IOException e) {
              throw new IllegalStateException("Cannot render message in case Invalid. ");
            }
            break;

          default:
            throw new IllegalStateException("Error generating correct format of Pair. ");
        }

        if (list.size() == 4) {
          this.proceedMove(list);
          this.renderGame();
        }

        // the game is over but still have inputs left to process
        if (this.model.isGameOver()) {
          break;
        }
      }

      // throw if the game is not over but ran out of input
      if (!s.hasNext() && !this.model.isGameOver()) {
        throw new IllegalStateException("Controller ran out of input. ");
      }
    }

    if (this.model.isGameOver()) {
      renderGameOver();
    }
  }

  private void proceedMove(LinkedList<String> list) throws IllegalStateException {
    try {
      int fromRow = Integer.parseInt(Objects.requireNonNull(list.pollFirst())) - 1;
      int fromCol = Integer.parseInt(Objects.requireNonNull(list.pollFirst())) - 1;
      int toRow = Integer.parseInt(Objects.requireNonNull(list.pollFirst())) - 1;
      int toCol = Integer.parseInt(Objects.requireNonNull(list.pollFirst())) - 1;
      list.clear();  // clear the move from the current list; refresh the list
      this.model.move(fromRow, fromCol, toRow, toCol);
    } catch (NumberFormatException e) {
      throw new IllegalStateException("Error formatting move. ");
    } catch (IllegalArgumentException e) {
      try {
        this.view.renderMessage("Invalid move. Play Again. " + e.getMessage() + "\n");
      } catch (IOException ex) {
        throw new IllegalStateException("Error rendering the message. ");
      }
    }
  }

  /**
   * Render the game, board and score, to the designated output.
   */
  private void renderGame() throws IllegalStateException {
    //    using the view, render the current state of the game
    try {
      this.view.renderBoard();
      this.view.renderMessage("\nScore: " + this.model.getScore() + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("There is an error while rendering the game. ");
    }
  }

  /**
   * Render the end game screen with the last state of the board and score.
   */
  private void renderGameOver() throws IllegalStateException {
    try {
      this.view.renderMessage("Game over!\n");
    } catch (IOException e) {
      throw new IllegalStateException("Error rendering ending message. ");
    }
    renderGame();
  }

  /**
   * Render the quit game screen with the last state of the board and score.
   */
  private void renderQuit() throws IllegalStateException {
    try {
      this.view.renderMessage("Game quit!\n");
      this.view.renderMessage("State of game when quit:\n");
    } catch (IOException e) {
      throw new IllegalStateException("Error rendering ending message. ");
    }
    renderGame();
  }
}

