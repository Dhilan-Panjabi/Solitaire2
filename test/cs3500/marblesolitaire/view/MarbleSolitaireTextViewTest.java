package cs3500.marblesolitaire.view;

import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;




import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;


/**
 * Testing public methods.
 */
public class MarbleSolitaireTextViewTest {

  EnglishSolitaireModel board1;
  EnglishSolitaireModel board2;
  EnglishSolitaireModel board3;
  EnglishSolitaireModel board4;
  EnglishSolitaireModel board5;

  MarbleSolitaireTextView m1;

  MarbleSolitaireTextView m2;

  MarbleSolitaireTextView m3;


  @Before
  public void init() {
    this.board1 = new EnglishSolitaireModel();
    this.board2 = new EnglishSolitaireModel(4, 5);
    this.board3 = new EnglishSolitaireModel(3);
    this.board4 = new EnglishSolitaireModel(3, 5, 4);
    this.board5 = new EnglishSolitaireModel(5, 5, 8);

    this.m1 = new MarbleSolitaireTextView(board3);
    this.m2 = new MarbleSolitaireTextView(board4);
    this.m3 = new MarbleSolitaireTextView(board5);

  }


  @Test
  public void toStringTest() {
    assertEquals(
            "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O _ O O O\n" +
                    "O O O O O O O\n" +
                    "    O O O\n" +
                    "    O O O", m1.toString());
    assertEquals(
            "    O O O\n" +
                    "    O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "O O O O O O O\n" +
                    "    O O _\n" +
                    "    O O O", m2.toString());
    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O _ O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", m3.toString());

  }
}
