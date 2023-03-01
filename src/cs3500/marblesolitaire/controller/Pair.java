package cs3500.marblesolitaire.controller;

/**
 * This class represents a Pair.
 */
public class Pair {

  public final ReadState state;
  public final String value;

  /**
   * Constructs a pair.
   *
   * @param state the reading state of the value
   * @param value the value which is assessed with reading state
   */
  public Pair(ReadState state, String value) {
    if (state == null || value == null) {
      throw new IllegalArgumentException("Both fields cannot be null. ");
    } else {
      this.state = state;
      this.value = value;
    }
  }

  @Override
  public int hashCode() {
    return this.state.hashCode() + this.value.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Pair)) {
      return false;
    } else {
      Pair pair = (Pair) obj;
      return this.state.equals(pair.state) && this.value.equals(pair.value);
    }
  }

  /**
   * This enum represents the reading state of a value from the input. Every read can either be
   * invalid, valid or quit.
   */

  public enum ReadState { Invalid, Valid, Quit }
}
