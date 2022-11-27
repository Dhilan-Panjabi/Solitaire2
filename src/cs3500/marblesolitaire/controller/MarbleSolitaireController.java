package cs3500.marblesolitaire.controller;



/**
 * To represent the controller of a marble solitaire game.
 */
public interface MarbleSolitaireController {
  /**
   *The method should play a new game of Marble solitaire given a model. The given input from the
   * user will be parsed and display the game and current score. If the case is inputs are invalid
   * the user will need to re-enter the values.
   * This method will then check the users input against the model to determine the validity of a
   * move. In the case of an invalid move, the user will need to prompt the user to correct their
   * input.
   *
   */
  void playGame();
}
