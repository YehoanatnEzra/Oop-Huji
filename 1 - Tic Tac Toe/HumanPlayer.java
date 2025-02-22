import java.util.Scanner;

/**
 * The HumanPlayer class implements the Player interface and represents a player controlled by human input.
 * This player prompts the user to input coordinates to place their mark on the game board.
 */
class HumanPlayer implements Player {
    private static final String INVALID_MARK_POSITION = "Invalid mark position," +
      " please choose a different position.\n" + "Invalid coordinates, type again: ";
    private static final String POSITION_OCCUPIED = "Mark position is already occupied.\n" +
            "Invalid coordinates, type again: ";
    private Scanner scanner;
    /**
     * Constructs a HumanPlayer object with a default constructor.
     */
    public HumanPlayer() {
    }

    /**
     * Plays a turn by prompting the user to input coordinates for placing their mark on the game board.
     *
     * @param board The game board.
     * @param mark  The mark (X or O) of the player.
     */
    public void playTurn(Board board, Mark mark) {
        boolean stopFlag = false;
        System.out.println("Player " + mark + ", type coordinates: ");
        while (!stopFlag) {
            int input = KeyboardInput.readInt();
            int row = input / 10;
            int col = input % 10;

            if(board.getMark(row, col) != Mark.BLANK){
                System.out.println(POSITION_OCCUPIED);

            }
            stopFlag = board.putMark(mark, row, col);
            if(!stopFlag) {
                System.out.println(INVALID_MARK_POSITION);
            }
        }
    }
}