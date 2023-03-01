package cs3500.marblesolitaire.controller;

/**
 * This class represents the controller for the game Marble Solitaire.
 */
public interface MarbleSolitaireController {

  /**
   * Plays a new game of Marble Solitaire.
   *
   * @throws IllegalStateException if the controller is unable to successfully read input or
   *                               transmit output
   */
  void playGame();
}
