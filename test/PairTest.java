import org.junit.Test;

import cs3500.marblesolitaire.controller.Pair;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * This class represents tests for Pair.
 */
public class PairTest {

  @Test
  public void testHashCode() {
    Pair pair = new Pair(Pair.ReadState.Valid, "12");
    Pair pair1 = new Pair(Pair.ReadState.Invalid, "potato");
    Pair pair2 = new Pair(Pair.ReadState.Quit, "q");
    assertEquals(Pair.ReadState.Valid.hashCode() + "12".hashCode(), pair.hashCode());
    assertEquals(Pair.ReadState.Invalid.hashCode() + "potato".hashCode(), pair1.hashCode());
    assertEquals(Pair.ReadState.Quit.hashCode() + "q".hashCode(), pair2.hashCode());
  }

  @Test
  public void testEquals() {
    Pair pair = new Pair(Pair.ReadState.Valid, "12");
    Pair pair1 = new Pair(Pair.ReadState.Invalid, "potato");
    Pair pair2 = new Pair(Pair.ReadState.Quit, "q");

    assertEquals(pair, pair);
    assertEquals(pair, new Pair(Pair.ReadState.Valid, "12"));

    assertNotEquals(pair, pair1);
    assertNotEquals(pair, pair2);
    assertNotEquals("12", pair);
  }
}