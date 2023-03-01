package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.controller.MarbleSolitaireFeature;

/**
 * This interface contains all methods in which an interactive board game of Marble Solitaire
 * must have.
 */
public interface InteractiveBoard {
  /**
   * Emits the information from the user's interaction to the feature.
   *
   * @param feature the feature to be notified of
   */
  void registerFeature(MarbleSolitaireFeature feature);
}
