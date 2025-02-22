/**
 * The Renderer interface defines the contract for rendering the state of a Tic-Tac-Toe game board.
 * Implementing classes must provide their own logic for rendering the game board.
 */

interface Renderer {
     /**
      * Renders the state of the game board, displaying the current marks and structure.
      *
      * @param board The game board to be rendered.
      */
     public void renderBoard (Board board);

}