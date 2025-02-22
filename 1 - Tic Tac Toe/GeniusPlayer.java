/**
 * The GeniusPlayer class represents a player in a board game
 * that makes moves in a systematic,sequential manner.
 * The player starts from the top board and second coland fills
 * each cell sequentially until the board is full or
 * a winning condition is met. the player win more than 55%
 * the clever and whatever players.
 * Implements the Player interface.
 */
public class GeniusPlayer implements Player {
    private static final int FIRST_IDX_ROW = 1;

    private static final int FIRST_IDX_COL = 0;

    /**
     * Constructs a new GeniusPlayer object.
     * Initializes the player with default settings.
     */
    public GeniusPlayer() {
    }

    /**
     * Plays a turn by systematically filling the cells of the
     * game board in a sequential manner.
     *
     * @param board The game board on which the player makes a move.
     * @param mark The Mark (X or O) assigned to the player.
     */
    public void playTurn(Board board, Mark mark) {
        int row = FIRST_IDX_ROW;
        int col = FIRST_IDX_COL;
        boolean stopFlag=false;
        while(!stopFlag) {
            stopFlag= board.putMark(mark, row, col);
            col++;
            if(col>board.getSize()-1)
            {
                col=FIRST_IDX_COL;
                row++;
                if(row>board.getSize()-1) {
                    row=0;
                }
            }
        }
    }
}