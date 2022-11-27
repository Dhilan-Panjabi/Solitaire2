package cs3500.marblesolitaire.model.hw02;


/**
 * Represents English Solitaire Model.
 */
public class EnglishSolitaireModel implements MarbleSolitaireModel {
  private int armThickness;
  private int sRow;
  private int sCol;
  private SlotState[] loS;


  /**
   * Constructor with no Parameters.
   */
  public EnglishSolitaireModel() {
    this(3, 3, 3);
  }

  /**
   * rows and column of empty slot.
   * set armThickness to 3.
   * Row and Col start from (0, 0).
   *
   * @param row empty slot
   * @param col empty slot
   * @throw IllegalArgumentException if invalid cell pos
   */
  public EnglishSolitaireModel(int row, int col) {
    this(3, row, col);
  }

  /**
   * Takes in the armThickness.
   * Make an empty slot in the center given the armThickness.
   *
   * @param armThickness num of marbles in top row
   * @throws IllegalArgumentException if the armThickness is not a positive num
   */
  public EnglishSolitaireModel(int armThickness) {
    this(armThickness, (armThickness * 3 - 3) / 2, (armThickness * 3 - 3) / 2);
  }

  /**
   * Takes in the row and col of the empty slot and the arm thickness.
   *
   * @param armThickness top row marbles
   * @param row          empty slot
   * @param col          empty slot
   * @throws IllegalArgumentException if the arm thickness is not positive or if the rol and col
   *                                  are in an invalid pos for the empty condition
   */
  public EnglishSolitaireModel(int armThickness, int row, int col) {
    if (armThickness <= 0 || armThickness % 2 == 0) {
      throw new IllegalArgumentException("Invalid arm thickness");
    }
    int lb = armThickness - 1;
    int ub = 2 * armThickness - 2;
    if ((row < lb && col < lb) || (row > ub && col > ub) || (row < lb && col > ub)
            || (row > ub && col < lb)) {
      throw new IllegalArgumentException("Invalid cell position (" + row + ", " + col + ")");
    } else if (row < 0 || col < 0) {
      throw new IllegalArgumentException("Invalid cell position (" + row + ", " + col + ")");
    } else {
      this.armThickness = armThickness;
      this.sRow = row;
      this.sCol = col;
      this.loS = new SlotState[(3 * armThickness - 2) * (3 * armThickness - 2)];
      this.fullBoard();
    }
  }

  private boolean notValidParameters(int row, int col) {
    int lb = armThickness - 1;
    int ub = (2 * armThickness) - 2;
    return ((row < lb && col < lb) || (row > ub && col > ub) || (row < lb && col > ub) ||
            (row > ub && col < lb));
  }


  private void fullBoard() {
    int boardSize = this.getBoardSize();

    for (int i = 0; i < boardSize; i = i + 1) {
      for (int j = 0; j < boardSize; j = j + 1) {
        int ind = i * boardSize + j;
        SlotState slot;

        if (this.notValidParameters(i, j)) {
          slot = SlotState.Invalid;
        } else if (i == sRow && j == sCol) {
          slot = SlotState.Empty;
        } else {
          slot = SlotState.Marble;
        }
        loS[ind] = slot;
      }
    }
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    int boardSize = this.getBoardSize();
    if (fromRow < 0 || fromCol < 0 || toRow < 0 || toCol < 0 || fromRow >= boardSize
            || fromCol >= boardSize || toRow >= boardSize || toCol >= boardSize) {
      throw new IllegalArgumentException("Invalid");
    }
    SlotState beginPos = this.getSlotAt(fromRow, fromCol);
    int beginPosInd = fromRow * this.getBoardSize() + fromCol;

    SlotState endPos = this.getSlotAt(toRow, toCol);
    int endPosInd = toRow * this.getBoardSize() + toCol;

    if (beginPos.equals(SlotState.Marble) && endPos.equals(SlotState.Empty)) {

      if (Math.abs(fromRow - toRow) == 2 && fromCol == toCol &&
              this.getSlotAt((fromRow + toRow) / 2, fromCol).equals(SlotState.Marble)) {
        this.loS[beginPosInd] = SlotState.Empty;
        this.loS[endPosInd] = SlotState.Marble;
        this.loS[(beginPosInd + endPosInd) / 2] = SlotState.Empty;
      } else if (Math.abs(fromCol - toCol) == 2 && fromRow == toRow && this.getSlotAt(fromRow,
              (toCol + fromCol) / 2).equals(SlotState.Marble)) {
        this.loS[beginPosInd] = SlotState.Empty;
        this.loS[endPosInd] = SlotState.Marble;
        this.loS[(beginPosInd + endPosInd) / 2] = SlotState.Empty;
      } else {
        throw new IllegalArgumentException("Invalid move");
      }
    } else {
      throw new IllegalArgumentException("Invalid move");
    }
  }

  private boolean ableToMoveUp(int row, int col) {
    return row >= 2 && this.getSlotAt(row - 1, col).equals(SlotState.Marble) &&
            this.getSlotAt(row + 2, col).equals(SlotState.Empty);
  }

  private boolean ableToMoveRight(int row, int col) {
    return col < this.getBoardSize() - 2
            && this.getSlotAt(row, col + 1).equals(SlotState.Marble)
            && this.getSlotAt(row, col + 2).equals(SlotState.Empty);
  }

  private boolean ableToMoveDown(int row, int col) {
    return row < this.getBoardSize() - 2
            && this.getSlotAt(row + 1, col).equals(SlotState.Marble)
            && this.getSlotAt(row + 2, col).equals(SlotState.Empty);
  }

  private boolean ableToMoveLeft(int row, int col) {
    return col >= 2 && this.getSlotAt(row, col - 1).equals(SlotState.Marble)
            && this.getSlotAt(row, col - 2).equals(SlotState.Empty);
  }


  @Override
  public boolean isGameOver() {
    for (int i = 0; i < this.getBoardSize(); i = i + 1) {
      for (int j = 0; j < this.getBoardSize(); j = j + 1) {
        int ind = i * this.getBoardSize() + j;

        if (loS[ind].equals(SlotState.Marble)) {
          if (this.ableToMoveRight(i, j) || this.ableToMoveLeft(i, j)
                  || this.ableToMoveDown(i, j) || this.ableToMoveUp(i, j)) {
            return false;
          }

        }

      }

    }
    return true;
  }

  @Override
  public int getBoardSize() {
    return this.armThickness + ((this.armThickness - 1) * 2);
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row > this.getBoardSize() - 1 || col > this.getBoardSize() - 1) {
      throw new IllegalArgumentException("row and col are invalid");
    } else {
      return loS[row * this.getBoardSize() + col];
    }
  }


  @Override
  public int getScore() {
    int marbles = 0;
    for (int i = 0; i < this.getBoardSize(); i = i + 1) {
      for (int j = 0; j < this.getBoardSize(); j = j + 1) {
        if (this.getSlotAt(i, j).equals(SlotState.Marble)) {
          marbles = marbles + 1;
        }
      }
    }
    return marbles;
  }

}


