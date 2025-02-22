/**
 * A CleverPlayer class that implements the Player interface.
 * This player places marks on the board in a systematic way,
 * starting from the top-left corner and filling each row before moving to the next one.
 */
class CleverPlayer implements Player {
    private static final int FIRST_IDX_ROW = 0;

    private static final int FIRST_IDX_COL = 0;

    /**
     * Constructs a CleverPlayer object.
     * This constructor initializes the CleverPlayer with default values.
     */
    public CleverPlayer() {
    }

    /**
     * Plays a turn by placing the player's mark on the board.
     * The CleverPlayer places marks in a systematic way, starting from the top-left corner
     * and filling each row before moving to the next one.
     *
     * @param board The game board on which the player will place the mark.
     * @param mark  The mark (X or O) that the player will place on the board.
     */
    public void playTurn(Board board, Mark mark) {
        int row = FIRST_IDX_ROW;
        int col = FIRST_IDX_COL;
        boolean stopFlag=false;
        while(!stopFlag) {
            stopFlag= board.putMark(mark, row, col);
            row++;
            if(row>=board.getSize())
            {
                row=FIRST_IDX_ROW;
                col++;
            }
        }
    }
}
