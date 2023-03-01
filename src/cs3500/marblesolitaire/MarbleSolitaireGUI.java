package cs3500.marblesolitaire;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerGUI;
import cs3500.marblesolitaire.controller.MarbleSolitaireFeature;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;
import cs3500.marblesolitaire.view.SwingGuiView;

/**
 * This class represents a main for English Marble Solitaire with GUI.
 */
public class MarbleSolitaireGUI {
  /**
   * Start the game.
   *
   * @param args the input from the command line
   */
  public static void main(String[] args) {
    MarbleSolitaireModel english = new EnglishSolitaireModel();
    MarbleSolitaireGuiView view = new SwingGuiView(english);
    MarbleSolitaireFeature controller = new MarbleSolitaireControllerGUI(english, view);
  }
}
