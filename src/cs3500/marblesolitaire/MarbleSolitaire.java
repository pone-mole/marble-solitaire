package cs3500.marblesolitaire;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.MarbleSolitaireAbstractFactory;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static cs3500.marblesolitaire.model.hw02.Utils.getFactory;

/**
 * This class represents the main access to the game Marble Solitaire.
 */
public final class MarbleSolitaire {

  /**
   * Run the game Marble Solitaire. The default game is Marble Solitaire 7 x 7.
   *
   * @param args stores Java command-line arguments
   */
  public static void main(String[] args) {
    if (args.length > 0) {
      // read the command line arguments depending on the length
      // config models
      MarbleSolitaireAbstractFactory factory = assessFactory(args.length, args);

      MarbleSolitaireModel model = factory.createModel();
      MarbleSolitaireView view = factory.createView();

      MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view,
              new InputStreamReader(System.in));

      controller.playGame();
    }
  }

  private static MarbleSolitaireAbstractFactory assessFactory(int argsSize, String[] args) {
    MarbleSolitaireAbstractFactory factory;
    String modelToBuild = args[0];
    switch (args.length) {
      case 1: // only indicate models
        factory = getFactory(modelToBuild);
        break;
      case 3: // has model, -size and n
        int size = Integer.parseInt(args[2]);
        factory = getFactory(modelToBuild, size);
        break;
      case 4: // has model, -hole, sRow and sCol
        int sRow = Integer.parseInt(args[2]);
        int sCol = Integer.parseInt(args[3]);
        factory = getFactory(modelToBuild, sRow, sCol);
        break;
      case 6:
        if (args[1].equalsIgnoreCase("-size")) { // input size first then input hole
          size = Integer.parseInt(args[2]);
          sRow = Integer.parseInt(args[4]);
          sCol = Integer.parseInt(args[5]);
        } else { // input hole first then size
          size = Integer.parseInt(args[5]);
          sRow = Integer.parseInt(args[2]);
          sCol = Integer.parseInt(args[3]);
        }
        factory = getFactory(modelToBuild, size, sRow, sCol);
        break;
      default:
        throw new IllegalArgumentException("Unsupported input format. ");
    }
    return factory;
  }
}