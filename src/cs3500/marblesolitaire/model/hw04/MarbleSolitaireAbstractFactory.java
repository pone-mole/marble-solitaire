package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * This class represents an object which writes the correct model or view for a game of Marble
 * Solitaire.
 */
public interface MarbleSolitaireAbstractFactory {
  /**
   * Create a model of Marble Solitaire.
   *
   * @return a model of Marble Solitaire
   */
  MarbleSolitaireModel createModel();

  /**
   * Creates a view of Marble Solitaire with the default appendable.
   *
   * @return a view of Marble Solitaire
   */
  MarbleSolitaireView createView();

  /**
   * Creates a view of Marble Solitaire with the provided appendable.
   *
   * @param appendable the appendable to be used in the view
   * @return the correct view of Marble Solitaire
   */
  MarbleSolitaireView createView(Appendable appendable);
}
