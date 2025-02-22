/**
 * The Player interface represents a player in a Tic-Tac-Toe game.
 * Implementing classes must define the behavior of playing a turn on the game board.
 */
interface Player {
    /**
     * Plays a turn on the game board, placing the player's mark.
     *
     * @param board The game board on which the player will place the mark.
     * @param mark  The mark (X or O) that the player will place on the board.
     */
    public void playTurn(Board board, Mark mark);

}