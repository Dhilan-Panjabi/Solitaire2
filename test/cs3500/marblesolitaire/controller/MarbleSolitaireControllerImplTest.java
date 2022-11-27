package cs3500.marblesolitaire.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.io.InputStreamReader;
import java.io.StringReader;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;


/**
 * Test cases for the marble solitaire controller. Verifying that outputs correspond to inputs, and
 * the controller conveys the correct inputs to the model.
 */
public class MarbleSolitaireControllerImplTest {

  private EnglishSolitaireModel m;
  EnglishSolitaireModel b;
  private StringBuilder gameLog;

  private MarbleSolitaireTextView view;


  @Before
  public void init() {
    m = new EnglishSolitaireModel();
    b = new EnglishSolitaireModel(0, 3);
    gameLog = new StringBuilder();
    view = new MarbleSolitaireTextView(this.m, this.gameLog);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAllNullConstructor() {
    new MarbleSolitaireControllerImpl(null, null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullReadable() {
    new MarbleSolitaireControllerImpl(new EnglishSolitaireModel(), new MarbleSolitaireTextView(
            new EnglishSolitaireModel()), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullView() {
    new MarbleSolitaireControllerImpl(new EnglishSolitaireModel(), null,
            new InputStreamReader(System.in));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    new MarbleSolitaireControllerImpl(null, new MarbleSolitaireTextView(
            new EnglishSolitaireModel()), new InputStreamReader(System.in));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testModelViewNull() {
    new MarbleSolitaireControllerImpl(null, null, new InputStreamReader(System.in));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testModelReadableNull() {
    new MarbleSolitaireControllerImpl(null, new MarbleSolitaireTextView(
            new EnglishSolitaireModel()), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testViewReadableNull() {
    new MarbleSolitaireControllerImpl(new EnglishSolitaireModel(), null, null);
  }

  @Test
  public void testQuit() {
    this.init();
    StringReader preQ = new StringReader("Q 2 4 4 4");
    StringReader readable = new StringReader("q 2 4 4");
    StringReader readable2 = new StringReader("2 q 4 4 ");
    StringReader readable3 = new StringReader("2 4 Q 4");
    StringReader readable4 = new StringReader("2 4 4 q");
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(
            this.m, this.view, readable);
    MarbleSolitaireController controller2 = new MarbleSolitaireControllerImpl(
            this.m, this.view, readable2);
    MarbleSolitaireController controller3 = new MarbleSolitaireControllerImpl(
            this.m, this.view, readable3);
    MarbleSolitaireController controller4 = new MarbleSolitaireControllerImpl(
            this.m, this.view, readable4);
    MarbleSolitaireController controller5 = new MarbleSolitaireControllerImpl(
            this.m, this.view, preQ);

    try {
      controller.playGame();
      assertEquals("    O O O\n" +
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
              "Score: 32\n", gameLog.toString());
    } catch (IllegalStateException e) {
      fail();
    }
    try {
      controller2.playGame();
      assertEquals("    O O O\n" +
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
              "Score: 32\n" +
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
              "Score: 32\n", gameLog.toString());
    } catch (IllegalStateException e) {
      fail();
    }
    try {
      controller3.playGame();
      assertEquals("    O O O\n" +
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
              "Score: 32\n" +
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
              "Score: 32\n" +
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
              "Score: 32\n", gameLog.toString());
    } catch (IllegalStateException e) {
      fail();
    }
    try {
      controller4.playGame();
      assertEquals("    O O O\n" +
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
              "Score: 32\n" +
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
              "Score: 32\n" +
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
              "Score: 32\n" +
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
              "Score: 32\n", gameLog.toString());
    } catch (IllegalStateException e) {
      fail();
    }
    try {
      controller5.playGame();
      assertEquals("    O O O\n" +
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
              "Score: 32\n" +
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
              "Score: 32\n" +
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
              "Score: 32\n" +
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
              "Score: 32\n" +
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
              "Score: 32\n", gameLog.toString());
    } catch (IllegalStateException e) {
      fail();
    }
  }

  @Test
  public void testGameComplete() {
    Readable input = new StringReader("4 2 4 4 6 3 4 3 5 1 5 3 5 4 5 2 5 6 " +
            "5 4 7 5 5 5 4 5 6 5 7 " +
            "3 7 5 7 5 5 5 3 3 5 3 1 3 3 3 2 5 4 5 4 5 6 5 6 5 6 3 6 3 4 3 4 3 2 3 3 1 " +
            "5 1 5 1 5 3 5 " +
            "3 5 5 3 7 3 5 3 4 3 6 5 7 3 7 3 7 3 5 1 5 1 3 1 3 3 3 3 2 3 4 3 4 3 6 3 6 5 6 5" +
            " 6 5 4 5 4 3 4 2 4 4 4");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, input);

    controller.playGame();
    assertEquals("    _ _ _\n" +
            "    _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "_ _ _ O _ _ _\n" +
            "_ _ _ _ _ _ _\n" +
            "    _ _ _\n" +
            "    _ _ _", view.toString());
    assertTrue(model.isGameOver());
  }

  @Test
  public void testOneValidMoveThenQuit() {
    Readable input = new StringReader("2 4 4 4 q");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, input);

    controller.playGame();
    assertEquals("    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", view.toString());
    assertFalse(model.isGameOver());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNoMoreMoves() {
    Readable input = new StringReader("2 4 4 4 5 4 3 4 7 4 5 4 4 6 4 4 4 3 4 5 4 1 4 3");

    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, input);

    controller.playGame();
    assertEquals("    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "_ _ O _ O _ O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O _ O", view.toString());
    assertTrue(model.isGameOver());
  }

  @Test(expected = IllegalStateException.class)
  public void testInvalidOutput() {
    Readable input = new StringReader("m, o, l, k, p, q, @");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, input);

    controller.playGame();
    assertEquals("", view.toString());
    assertTrue(model.isGameOver());
  }

  @Test(expected = IllegalStateException.class)
  public void testValidMoveFollowedByInvalidMove() {
    Readable input = new StringReader("4 2 4 4 6 3 4 3 5 1 5 3 5 4 5 1 5 6 5 4");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, input);
    controller.playGame();
    assertEquals("", view.toString());
    assertFalse(model.isGameOver());
  }

  class MockModel implements MarbleSolitaireModel {
    private StringBuilder gameLog;

    public MockModel(StringBuilder gameLog) {
      this.gameLog = gameLog;
    }

    @Override
    public void move(int fromRow, int fromCol, int toRow, int toCol)
            throws IllegalArgumentException {
      gameLog.append("Attempted move \n from (" + fromRow + "," + fromCol + ")\n to ("
              + toRow + "," + toCol + ")\n");
    }

    @Override
    public boolean isGameOver() {
      return false;
    }

    @Override
    public int getBoardSize() {
      return 0;
    }

    @Override
    public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
      return null;
    }

    @Override
    public int getScore() {
      return 0;
    }
  }

  @Test
  public void testReadable() {
    Readable s1 = new StringReader("2 ahndfgjkdfn asd");
    Readable s2 = new StringReader("2, 4");
    MarbleSolitaireModel model = new MockModel(new StringBuilder());
    MarbleSolitaireView mock = new MarbleSolitaireTextView(model);
    MarbleSolitaireController c = new MarbleSolitaireControllerImpl(model, mock, s1);
    MarbleSolitaireController c2 = new MarbleSolitaireControllerImpl(model, mock, s2);

    try {
      c.playGame();
    } catch (IllegalStateException e) {
      assertEquals("no more inputs", e.getMessage());
    }
    try {
      c2.playGame();
    } catch (IllegalStateException e) {
      assertEquals("no more inputs", e.getMessage());
    }
  }


}





