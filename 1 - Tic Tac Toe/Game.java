/**
 * The Game class represents a Tic-Tac-Toe game.
 * It manages the players, the game board, and the logic for determining the winner.
 */
class Game {
    // Constants for default values
    private static final int DEFAULT_WIN_STREAK=3;
    private static final int RESET=0;
    // Players and game components
    private Player playerX;
    private Player playerO;
    private Renderer renderer;
    private Board board;
    private int winStrak;

    /**
     * Constructs a Game object with default settings.
     *
     * @param playerX   The player representing X.
     * @param playerO   The player representing O.
     * @param renderer  The renderer for displaying the game.
     */
    public Game(Player playerX,Player playerO, Renderer renderer) {
        this.playerX=playerX;
        this.playerO=playerO;
        this.renderer=renderer;
        this.board=new Board();
        this.winStrak=DEFAULT_WIN_STREAK;

    }

    /**
     * Constructs a Game object with custom settings.
     *
     * @param playerX   The player representing X.
     * @param playerO   The player representing O.
     * @param size      The size of the game board.
     * @param winStreak The number of consecutive marks required to win.
     * @param renderer  The renderer for displaying the game.
     */
    public Game(Player playerX,Player playerO, int size, int winStreak,Renderer renderer){
        this.playerX=playerX;
        this.playerO=playerO;
        if(size<winStreak || winStreak<2) {
            winStreak=size;
        }
        this.board=new Board(size);
        this.winStrak=winStreak;
        this.renderer=renderer;
    }

    /**
     * Gets the configured win streak required to win the game.
     *
     * @return The win streak.
     */
   public int getWinStreak() {
        return this.winStrak;
    }

    /**
     * Gets the size of the game board.
     *
     * @return The size of the game board.
     */
     public int getBoardSize() {
        return this.board.getSize();
     }

    // Private helper methods for determining the winner and game state
    private boolean is_winner(Board board, Mark mark) {
        for (int row = 0; row < this.getBoardSize(); row++) {
            for (int col = 0; col < this.getBoardSize(); col++) {
                if (is_winner_from_cell(board, mark, row, col)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the specified player's mark forms a winning streak starting from the given cell.
     * This method considers all possible directions (horizontally, vertically,
     * and diagonally) from the starting cell.
     *
     * @param board     The game board to check for a winning streak.
     * @param mark      The player's mark (X or O).
     * @param startRow  The starting row index of the cell.
     * @param startCol  The starting column index of the cell.
     * @return True if a winning streak is formed from the specified cell; false otherwise.
     */
    private boolean is_winner_from_cell(Board board, Mark mark, int startRow, int startCol) {
        for (int rowIncrement = -1; rowIncrement <= 1; rowIncrement++) {
            for (int colIncrement = -1; colIncrement <= 1; colIncrement++) {
                if (rowIncrement == 0 && colIncrement == 0) {
                    continue; // Skip the case where both increments are 0
                }

                int row = startRow;
                int col = startCol;
                int streak_count = 0;

                while (row >= 0 && row < this.getBoardSize() && col >= 0 && col < this.getBoardSize()
                        && board.getMark(row, col) == mark) {
                    streak_count++;
                    row += rowIncrement;
                    col += colIncrement;
                }
                if (streak_count == this.getWinStreak()) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks if the game board is completely filled with marks.
     * This method is used to determine if the game has reached a draw.
     *
     * @return True if the board is completely filled; false otherwise.
     */
    private boolean is_full() {
        for( int row=0; row<this.getBoardSize(); row++){
            for( int col=0; col<this.getBoardSize(); col++){
                if(this.board.getMark(row,col)==Mark.BLANK) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Runs the game until a winner is determined or the game ends in a draw.
     *
     * @return The mark of the winner (X, O, or BLANK for a draw).
     */
     public Mark run() {
        while(!this.is_full()) {
            this.playerX.playTurn(this.board, Mark.X);
            this.renderer.renderBoard(board);
            if( is_winner( board, Mark.X)) {
                return Mark.X;
            }
            if(is_full()){
                return Mark.BLANK;
            }
            this.playerO.playTurn(this.board, Mark.O);
            this.renderer.renderBoard(board);
            if(is_winner(board,Mark.O)){
                return Mark.O;
            }
        }
        return Mark.BLANK;
     }
}