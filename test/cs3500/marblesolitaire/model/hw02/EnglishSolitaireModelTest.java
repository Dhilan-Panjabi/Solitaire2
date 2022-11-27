package cs3500.marblesolitaire.model.hw02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * Testing all the public methods.
 */
public class EnglishSolitaireModelTest {

  EnglishSolitaireModel board1;
  EnglishSolitaireModel board2;
  EnglishSolitaireModel board3;
  EnglishSolitaireModel board4;
  EnglishSolitaireModel board5;


  @Before
  public void init() {
    this.board1 = new EnglishSolitaireModel();
    this.board2 = new EnglishSolitaireModel(4, 5);
    this.board3 = new EnglishSolitaireModel(3);
    this.board4 = new EnglishSolitaireModel(3, 5, 4);
    this.board5 = new EnglishSolitaireModel(5, 5, 8);

  }

  @org.junit.Test
  public void moveTest() {
    init();
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.board3.getSlotAt(3, 3));
    this.board3.move(1, 3, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.board3.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.board3.getSlotAt(2, 3));
    this.board3.move(4, 3, 2, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.board3.getSlotAt(2, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.board3.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.board3.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.board3.getSlotAt(5, 3));
    this.board3.move(3, 5, 3, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.board3.getSlotAt(3, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.board3.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.board3.getSlotAt(3, 1));
  }


  @org.junit.Test
  public void invalidMoveTest() {
    try {
      this.board3.move(1, 0, 4, 3);
      fail("Invalid Exception");
    } catch (IllegalArgumentException e) {
      //cannot move < 2 spaces
    }
    try {
      this.board3.move(1, 2, 3, 2);
      fail("invalid exception");
    } catch (IllegalArgumentException e) {
      //cannot move one above with marble
    }
    try {
      this.board3.move(6, 6, 8, 6);
      fail("invalid exception");

    } catch (IllegalArgumentException e) {
      //cannot move out of board
    }
  }

  @org.junit.Test
  public void isGameOverTest() {
    assertEquals(false, this.board3.isGameOver());
    this.board3.move(1, 3, 3, 3);
    assertEquals(false, this.board3.isGameOver());
  }

  @Test
  public void getBoardSizeTest() {
    init();
    assertEquals(7, this.board3.getBoardSize());
    assertEquals(7, this.board4.getBoardSize());
    assertEquals(13, this.board5.getBoardSize());
    assertEquals(7, this.board2.getBoardSize());
  }

  @Test
  public void getSlotAtTest() {
    init();
    assertEquals(MarbleSolitaireModelState.SlotState.Marble, this.board3.getSlotAt(4, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid, this.board4.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty, this.board3.getSlotAt(3, 3));
  }

  @Test
  public void getScoreTest() {
    init();
    assertEquals(32, this.board3.getScore());
    assertEquals(32, this.board4.getScore());
    assertEquals(104, this.board5.getScore());
    this.board3.move(1, 3, 3, 3);
    assertEquals(31, this.board3.getScore());
  }

  @Test
  public void invalidBoardParametersTest() {
    try {
      EnglishSolitaireModel board = new EnglishSolitaireModel(6);
      fail("Illegal Exception");
    } catch (IllegalArgumentException e) {
      //invalid arm thickness
    }
    try {
      EnglishSolitaireModel board = new EnglishSolitaireModel(4, 3, 0);
      fail("Illegal Exception");
    } catch (IllegalArgumentException e) {
      //invalid arm thickness and empty slot
    }
    try {
      EnglishSolitaireModel board = new EnglishSolitaireModel(8, 0, 1);
      fail("Illegal Exception");
    } catch (IllegalArgumentException e) {
      //invalid empty slot with arm thickness being valid
    }
  }
}






