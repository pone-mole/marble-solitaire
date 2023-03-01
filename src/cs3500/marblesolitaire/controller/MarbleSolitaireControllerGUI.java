package cs3500.marblesolitaire.controller;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireGuiView;

/**
 * This class represents an implementation of the interface Marble Solitaire Feature.
 */
public class MarbleSolitaireControllerGUI implements MarbleSolitaireFeature {
  private final MarbleSolitaireModel model;
  private final MarbleSolitaireGuiView view;
  private int col = -1;
  private int row = -1;

  /**
   * Constructs a new controller which supports view with GUI.
   *
   * @param model the model to be work with
   * @param view the view to be work with
   * @throws IllegalArgumentException if either the model or the view is null
   */
  public MarbleSolitaireControllerGUI(MarbleSolitaireModel model, MarbleSolitaireGuiView view)
          throws IllegalArgumentException {
    if (model == null || view == null) {
      throw new IllegalArgumentException("The model and view cannot be null.");
    }
    this.model = model;
    this.view = view;
    this.view.registerFeature(this);
  }

  @Override
  public void move(int row, int col) {
    if (this.col < 0 && this.row < 0) { // no input provided yet
      this.col = col;
      this.row = row;
    } else {
      if (this.col >= 0 && this.row >= 0) { // input provided previously
        try {
          this.model.move(this.row, this.col, row, col);
          System.out.println("moved!");
          this.col = -1;
          this.row = -1;
          this.view.refresh();
        } catch (IllegalArgumentException e) {
          this.view.renderMessage(e.getMessage());
          this.col = -1;
          this.row = -1;
        }
      }
    }
  }
}
