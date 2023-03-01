package cs3500.marblesolitaire.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import cs3500.marblesolitaire.controller.MarbleSolitaireFeature;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

public class BoardPanel extends JPanel implements InteractiveBoard {
  private final int cellDimension;
  private final MarbleSolitaireModelState modelState;
  private MarbleSolitaireFeature feature;
  private Image emptySlot;
  private Image marbleSlot;
  private Image blankSlot;
  private int originX;
  private int originY;

  /**
   * Constructs a new BoardPanel.
   *
   * @param state the model state to build the model
   * @throws IllegalStateException if the image cannot be read
   */
  public BoardPanel(MarbleSolitaireModelState state) throws IllegalStateException {
    super();
    this.modelState = state;
    this.setBackground(Color.WHITE);
    this.cellDimension = 50;
    try {
      emptySlot = ImageIO.read(new FileInputStream("res/empty.png"));
      emptySlot = emptySlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      marbleSlot = ImageIO.read(new FileInputStream("res/marble.png"));
      marbleSlot = marbleSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      blankSlot = ImageIO.read(new FileInputStream("res/blank.png"));
      blankSlot = blankSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      this.setPreferredSize(new Dimension((this.modelState.getBoardSize() + 4) * cellDimension,
              (this.modelState.getBoardSize() + 4) * cellDimension));
    } catch (IOException e) {
      throw new IllegalStateException("Icons not found!");
    }

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    originX =
            (int) (this.getPreferredSize().getWidth() / 2
                    - this.modelState.getBoardSize() * cellDimension / 2);
    originY =
            (int) (this.getPreferredSize().getHeight() / 2
                    - this.modelState.getBoardSize() * cellDimension / 2);

    int currentX = originX;
    int currentY = originY;
    //your code to the draw the board should go here.
    for (int x = 0; x < this.modelState.getBoardSize(); x++) {
      for (int y = 0; y < this.modelState.getBoardSize(); y++) {
        Image image = getSlotImage(x, y);
        g.drawImage(image, currentX, currentY, this);
        currentY += this.cellDimension;
      }
      currentX += this.cellDimension;
      currentY = originY;
    }
    //The originX and originY is the top-left of where the cell (0,0) should start
    //cellDimension is the width (and height) occupied by every cell

  }

  /**
   * Return appropriate slot image at the position of the slot provided on the board.
   *
   * @param x the row of the slot which its image to be returned for
   * @param y the column of the slot which its image to be returned for
   * @return the image according to the slot state at that position
   */
  private Image getSlotImage(int x, int y) {
    switch (this.modelState.getSlotAt(y, x)) {
      case Empty:
        return this.emptySlot;
      case Marble:
        return this.marbleSlot;
      case Invalid:
        return this.blankSlot;
      default:
        throw new IllegalArgumentException("Invalid slot state.");
    }
  }

  @Override
  public void registerFeature(MarbleSolitaireFeature feature) {
    this.feature = feature;
    this.addMouseListener(new MouseClickListener());
  }

  /**
   * Highlight the slot provided its row and col.
   *
   * @param x pos x of the slot to be highlighted
   * @param y pos y of the slot to be highlighted
   */
  private void highlightSlot(int x, int y) {
    Component component = this.getComponentAt(x, y);
    component.requestFocus();
  }

  private class MouseClickListener extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
      int x = (e.getX() - originX) / cellDimension;
      int y = (e.getY() - originY) / cellDimension;

      System.out.println("clicked " + x + " " + y);
      feature.move(y, x); // row, col
    }
  }
}
