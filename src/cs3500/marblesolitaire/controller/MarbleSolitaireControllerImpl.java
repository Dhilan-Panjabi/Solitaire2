package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;


/**
 * Marble Solitaire Controller - controls a game of marble solitaire.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  private final Readable rd;

  private final MarbleSolitaireModel model;

  private final MarbleSolitaireView view;

  /**
   * Takes in a model, view and readable and throws an illegal argument exception if any are null.
   * They then intialize each of the fields.
   *
   * @param model an English solitaire model
   * @param view  a marble solitaire text view
   * @param rd    a readable
   * @throws IllegalArgumentException if any of them are null it throws an exception
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable rd) throws IllegalArgumentException {
    if (model == null || rd == null || view == null) {
      throw new IllegalArgumentException("cannot be null");
    }
    this.model = model;
    this.view = view;
    this.rd = rd;
  }

  /**
   * try and append for a given list of strings and catch any exceptions.
   *
   * @param append a list of strings that the render board renders
   * @throws IllegalStateException if no appendable recieved
   */
  private void appendRender(List<String> append) throws IllegalStateException {
    for (String s : append) {
      try {
        if (s.equals("")) {
          view.renderBoard();
        } else {
          view.renderMessage(s);
        }
      } catch (IOException e) {
        throw new IllegalStateException("appendable broke");
      }
    }


  }

  /**
   * checks the co-ordinates given if they are valid and if there are more inputs, it checks the
   * given and returns an int.
   *
   * @param scan checks the given integers to the readable
   * @return int for the move
   * @throws IllegalArgumentException q meaning game quit
   * @throws IllegalStateException    no more inputs
   */
  private int checkInt(Scanner scan) throws IllegalArgumentException, IllegalStateException {
    int i = 0;
    while (i == 0) {
      if (!scan.hasNext()) {
        throw new IllegalStateException("no more inputs");
      }
      if (scan.hasNextInt()) {
        int number = scan.nextInt();
        if (number < 1 || number > model.getBoardSize()) {
          appendRender(new ArrayList<String>(Arrays.asList("int must be between 1 and " +
                  model.getBoardSize(), "\n")));

        } else {
          i = number;
        }
      } else {
        String s = scan.next();
        if (s.equalsIgnoreCase("Q")) {
          throw new IllegalArgumentException();
        }
      }

    }
    return i;
  }

  /**
   * Loops for the play game.
   *
   * @param scan for playgame
   * @throws IllegalStateException if the game has been quit.
   */
  private void initRound(Scanner scan) throws IllegalStateException {
    int fromRow;
    int fromCol;
    int toRow;
    int toCol;

    String gameScore = "Score: " + model.getScore();
    List<String> arr = new ArrayList<String>(Arrays.asList("", "\n", gameScore, "\n"));

    appendRender(arr);

    fromRow = checkInt(scan) - 1;
    fromCol = checkInt(scan) - 1;
    toRow = checkInt(scan) - 1;
    toCol = checkInt(scan) - 1;

    try {
      model.move(fromRow, fromCol, toRow, toCol);
    } catch (IllegalArgumentException iae) {
      appendRender(new ArrayList<String>(Arrays.asList("invalid move", "\n")));
    }
    if (model.isGameOver()) {
      gameScore = "Score: " + model.getScore();
      appendRender(new ArrayList<String>(Arrays.asList("Game over!", "\n", "", "\n",
              gameScore, "\n")));
    }

  }

  @Override
  public void playGame() {
    Scanner scan = new Scanner(rd);
    while (!(model.isGameOver())) {
      try {
        this.initRound(scan);
      } catch (IllegalArgumentException e) {
        appendRender(new ArrayList<String>(Arrays.asList("Game quit!\n",
                "State of game when quit:", "\n", "", "\n", "Score: " + model.getScore(), "\n")));
        return;
      }
    }
  }
}


