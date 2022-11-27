package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * to view the state of the board.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  private Appendable appendable;
  private MarbleSolitaireModelState arg;

  /**
   * Takes in the state.
   *
   * @param arg state
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState arg) {
    if (arg == null) {
      throw new IllegalArgumentException("cannot be null");
    }
    this.arg = arg;
    this.appendable = System.out;
  }

  /**
   * MarbleSolitaireTextView of a model and an appendable.
   *
   * @param a  given marble solitaire model state
   * @param ap the given appendable
   * @throws IllegalArgumentException if any of the paramters are null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState a, Appendable ap) throws
          IllegalArgumentException {
    if (a == null || ap == null) {
      throw new IllegalArgumentException("null paramters");
    }
    this.arg = a;
    this.appendable = ap;
  }

  /**
   * loops through the positions on the board and checks the state to append a different position.
   * depending on the slotstate.
   *
   * @return toString of string builder
   */
  @Override
  public String toString() {
    String base = "";
    int bSize = arg.getBoardSize();
    int row = 0;
    while (row <= bSize - 1) {
      int column = 0;
      while (column <= bSize - 1) {
        MarbleSolitaireModelState.SlotState s = arg.getSlotAt(row, column);
        if (s.equals(MarbleSolitaireModelState.SlotState.Empty)) {
          if (column == bSize - 1 || arg.getSlotAt(row, column + 1).equals(
                  MarbleSolitaireModelState.SlotState.Invalid)) {
            base = base + "_";
            column = bSize;
          } else {
            base = base + "_ ";
          }
        } else if (s.equals(MarbleSolitaireModelState.SlotState.Invalid)) {
          base = base + "  ";
        } else if (s.equals(MarbleSolitaireModelState.SlotState.Marble)) {
          if (column == bSize - 1) {
            base = base + "O";
            column = bSize;
          } else if (arg.getSlotAt(row, column + 1).equals(
                  MarbleSolitaireModelState.SlotState.Invalid)) {
            base = base + "O";
            column = bSize;
          } else {
            base = base + "O ";
          }
        }
        column = column + 1;
      }
      if (row < bSize - 1) {
        base = base + "\n";
      } else {
        base = base + "";
      }
      row = row + 1;
    }
    return base;
  }

  @Override
  public void renderBoard() throws IOException {
    this.renderMessage(this.toString());
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.appendable.append(message);
  }
}



