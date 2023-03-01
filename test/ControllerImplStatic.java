/**
 * This class represents the static methods to be inherited in MarbleSolitaireControllerImplTest.
 */
public class ControllerImplStatic extends EnglishSolitaireModelTest {
  static String inputFullDefaultGame = "4 6 4 4 2 5 4 5 3 7 3 5 5 7 3 7 3 4 3 6 3 7 3 5 3 2 3 4 1"
          + " 3 3 3 1 5 1 3 4 3 2 3 1 3 3 3 6 3 4 3 5 1 5 3 3 1 5 1 5 4 5 2 5 6 5 4 7 5 5 5 7 3 7 5"
          + " 4 5 6 5 7 5 5 5 5 1 5 3 4 3 2 3 2 3 2 5 2 5 4 5 4 5 6 5 6 5 6 3 6 3 4 3 4 4 2 4 4 2"
          + "  4 4 5 4 3 4 2 4 4 4 ";

  static String outputFullDefaultGame = "    O O O\n" +
          "    O O O\n" +
          "O O O O O O O\n" +
          "O O O _ O O O\n" +
          "O O O O O O O\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 32\n" +
          "    O O O\n" +
          "    O O O\n" +
          "O O O O O O O\n" +
          "O O O O _ _ O\n" +
          "O O O O O O O\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 31\n" +
          "    O O O\n" +
          "    O O _\n" +
          "O O O O _ O O\n" +
          "O O O O O _ O\n" +
          "O O O O O O O\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 30\n" +
          "    O O O\n" +
          "    O O _\n" +
          "O O O O O _ _\n" +
          "O O O O O _ O\n" +
          "O O O O O O O\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 29\n" +
          "    O O O\n" +
          "    O O _\n" +
          "O O O O O _ O\n" +
          "O O O O O _ _\n" +
          "O O O O O O _\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 28\n" +
          "    O O O\n" +
          "    O O _\n" +
          "O O O _ _ O O\n" +
          "O O O O O _ _\n" +
          "O O O O O O _\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 27\n" +
          "    O O O\n" +
          "    O O _\n" +
          "O O O _ O _ _\n" +
          "O O O O O _ _\n" +
          "O O O O O O _\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 26\n" +
          "    O O O\n" +
          "    O O _\n" +
          "O _ _ O O _ _\n" +
          "O O O O O _ _\n" +
          "O O O O O O _\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 25\n" +
          "    _ O O\n" +
          "    _ O _\n" +
          "O _ O O O _ _\n" +
          "O O O O O _ _\n" +
          "O O O O O O _\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 24\n" +
          "    O _ _\n" +
          "    _ O _\n" +
          "O _ O O O _ _\n" +
          "O O O O O _ _\n" +
          "O O O O O O _\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 23\n" +
          "    O _ _\n" +
          "    O O _\n" +
          "O _ _ O O _ _\n" +
          "O O _ O O _ _\n" +
          "O O O O O O _\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 22\n" +
          "    _ _ _\n" +
          "    _ O _\n" +
          "O _ O O O _ _\n" +
          "O O _ O O _ _\n" +
          "O O O O O O _\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 21\n" +
          "    _ _ _\n" +
          "    _ O _\n" +
          "O _ O O O _ _\n" +
          "O O O O O _ _\n" +
          "O O _ O O O _\n" +
          "    _ O O\n" +
          "    O O O\n" +
          "Score: 20\n" +
          "    _ _ _\n" +
          "    _ O _\n" +
          "O _ O O O _ _\n" +
          "O O O O O _ _\n" +
          "_ _ O O O O _\n" +
          "    _ O O\n" +
          "    O O O\n" +
          "Score: 19\n" +
          "    _ _ _\n" +
          "    _ O _\n" +
          "_ _ O O O _ _\n" +
          "_ O O O O _ _\n" +
          "O _ O O O O _\n" +
          "    _ O O\n" +
          "    O O O\n" +
          "Score: 18\n" +
          "    _ _ _\n" +
          "    _ O _\n" +
          "_ _ O O O _ _\n" +
          "_ O O O O _ _\n" +
          "O O _ _ O O _\n" +
          "    _ O O\n" +
          "    O O O\n" +
          "Score: 17\n" +
          "    _ _ _\n" +
          "    _ O _\n" +
          "_ _ O O O _ _\n" +
          "_ O O O O _ _\n" +
          "O O _ O _ _ _\n" +
          "    _ O O\n" +
          "    O O O\n" +
          "Score: 16\n" +
          "    _ _ _\n" +
          "    _ O _\n" +
          "_ _ O O O _ _\n" +
          "_ O O O O _ _\n" +
          "O O _ O O _ _\n" +
          "    _ O _\n" +
          "    O O _\n" +
          "Score: 15\n" +
          "    _ _ _\n" +
          "    _ O _\n" +
          "_ _ O O O _ _\n" +
          "_ O O O O _ _\n" +
          "O O _ O O _ _\n" +
          "    _ O _\n" +
          "    _ _ O\n" +
          "Score: 14\n" +
          "    _ _ _\n" +
          "    _ O _\n" +
          "_ _ O O O _ _\n" +
          "_ O O O _ _ _\n" +
          "O O _ O _ _ _\n" +
          "    _ O O\n" +
          "    _ _ O\n" +
          "Score: 13\n" +
          "    _ _ _\n" +
          "    _ O _\n" +
          "_ _ O O O _ _\n" +
          "_ O O O _ _ _\n" +
          "O O _ O O _ _\n" +
          "    _ O _\n" +
          "    _ _ _\n" +
          "Score: 12\n" +
          "    _ _ _\n" +
          "    _ O _\n" +
          "_ _ O O O _ _\n" +
          "_ O O O _ _ _\n" +
          "_ _ O O O _ _\n" +
          "    _ O _\n" +
          "    _ _ _\n" +
          "Score: 11\n" +
          "    _ _ _\n" +
          "    O O _\n" +
          "_ _ _ O O _ _\n" +
          "_ O _ O _ _ _\n" +
          "_ _ O O O _ _\n" +
          "    _ O _\n" +
          "    _ _ _\n" +
          "Score: 10\n" +
          "    _ _ _\n" +
          "    _ _ O\n" +
          "_ _ _ O O _ _\n" +
          "_ O _ O _ _ _\n" +
          "_ _ O O O _ _\n" +
          "    _ O _\n" +
          "    _ _ _\n" +
          "Score: 9\n" +
          "    _ _ _\n" +
          "    _ _ _\n" +
          "_ _ _ O _ _ _\n" +
          "_ O _ O O _ _\n" +
          "_ _ O O O _ _\n" +
          "    _ O _\n" +
          "    _ _ _\n" +
          "Score: 8\n" +
          "    _ _ _\n" +
          "    _ _ _\n" +
          "_ _ _ O _ _ _\n" +
          "_ O _ O _ _ _\n" +
          "_ _ O O _ _ _\n" +
          "    _ O O\n" +
          "    _ _ _\n" +
          "Score: 7\n" +
          "    _ _ _\n" +
          "    _ _ _\n" +
          "_ _ _ O _ _ _\n" +
          "_ O _ O _ _ _\n" +
          "_ _ O O _ _ _\n" +
          "    O _ _\n" +
          "    _ _ _\n" +
          "Score: 6\n" +
          "    _ _ _\n" +
          "    _ _ _\n" +
          "_ _ _ O _ _ _\n" +
          "_ O O O _ _ _\n" +
          "_ _ _ O _ _ _\n" +
          "    _ _ _\n" +
          "    _ _ _\n" +
          "Score: 5\n" +
          "    _ _ _\n" +
          "    _ O _\n" +
          "_ _ _ _ _ _ _\n" +
          "_ O O _ _ _ _\n" +
          "_ _ _ O _ _ _\n" +
          "    _ _ _\n" +
          "    _ _ _\n" +
          "Score: 4\n" +
          "    _ _ _\n" +
          "    _ O _\n" +
          "_ _ _ _ _ _ _\n" +
          "_ _ _ O _ _ _\n" +
          "_ _ _ O _ _ _\n" +
          "    _ _ _\n" +
          "    _ _ _\n" +
          "Score: 3\n" +
          "    _ _ _\n" +
          "    _ O _\n" +
          "_ _ _ O _ _ _\n" +
          "_ _ _ _ _ _ _\n" +
          "_ _ _ _ _ _ _\n" +
          "    _ _ _\n" +
          "    _ _ _\n" +
          "Score: 2\n" +
          "    _ _ _\n" +
          "    _ _ _\n" +
          "_ _ _ _ _ _ _\n" +
          "_ _ _ O _ _ _\n" +
          "_ _ _ _ _ _ _\n" +
          "    _ _ _\n" +
          "    _ _ _\n" +
          "Score: 1\n" +
          "Game over!\n" +
          "    _ _ _\n" +
          "    _ _ _\n" +
          "_ _ _ _ _ _ _\n" +
          "_ _ _ O _ _ _\n" +
          "_ _ _ _ _ _ _\n" +
          "    _ _ _\n" +
          "    _ _ _\n" +
          "Score: 1\n";

  static String outputGameQuitAfter1234 = "    O O O\n" +
          "    O O O\n" +
          "O O O O O O O\n" +
          "O O O _ O O O\n" +
          "O O O O O O O\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 32\n" +
          "Invalid move. Play Again. Invalid move (row, col): (1, 2) to (3, 4). \n" +
          "    O O O\n" +
          "    O O O\n" +
          "O O O O O O O\n" +
          "O O O _ O O O\n" +
          "O O O O O O O\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 32\n" +
          "Game quit!\n" +
          "State of game when quit:\n" +
          "    O O O\n" +
          "    O O O\n" +
          "O O O O O O O\n" +
          "O O O _ O O O\n" +
          "O O O O O O O\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 32\n";

  static String outputInputOutOfBound = "    O O O\n" +
          "    O O O\n" +
          "O O O O O O O\n" +
          "O O O _ O O O\n" +
          "O O O O O O O\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 32\n" +
          "Invalid move. Play Again. Indicated slot out of bound (row, col): (999,1)\n" +
          "    O O O\n" +
          "    O O O\n" +
          "O O O O O O O\n" +
          "O O O _ O O O\n" +
          "O O O O O O O\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 32\n" +
          "Game quit!\n" +
          "State of game when quit:\n" +
          "    O O O\n" +
          "    O O O\n" +
          "O O O O O O O\n" +
          "O O O _ O O O\n" +
          "O O O O O O O\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 32\n";

  static String outputGameQuit1 = "    O O O\n" +
          "    O O O\n" +
          "O O O O O O O\n" +
          "O O O _ O O O\n" +
          "O O O O O O O\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 32\n" +
          "Game quit!\n" +
          "State of game when quit:\n" +
          "    O O O\n" +
          "    O O O\n" +
          "O O O O O O O\n" +
          "O O O _ O O O\n" +
          "O O O O O O O\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 32\n";

  static String outputGameQuit2 = "    O O O\n" +
          "    O O O\n" +
          "O O O O O O O\n" +
          "O O O _ O O O\n" +
          "O O O O O O O\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 32\n" +
          "    O O O\n" +
          "    O O O\n" +
          "O O O O O O O\n" +
          "O _ _ O O O O\n" +
          "O O O O O O O\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 31\n" +
          "Game quit!\n" +
          "State of game when quit:\n" +
          "    O O O\n" +
          "    O O O\n" +
          "O O O O O O O\n" +
          "O _ _ O O O O\n" +
          "O O O O O O O\n" +
          "    O O O\n" +
          "    O O O\n" +
          "Score: 31\n";
}
