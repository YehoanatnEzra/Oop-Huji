/**
 * Represents a game board for storing marks (X,O, BLANK) in a 2D array.
 */

class  Board {
    private static final int DEFULT_SIZE = 4;
    private int size;
    private Mark[][] boardArray;


    /**
     * Constructs a default game board with the default size and initializes it with blank marks.
     */
    public Board() {
        this(DEFULT_SIZE);
    }

    /**
     * Constructs a game board with the specified size and initializes it with blank marks.
     *
     * @param size The size of the game board.
     */
    public Board(int size) {
        this.size=size;
        this.boardArray=new Mark[size][size];
        for(int row=0; row < size; row++) {
            for(int col=0; col < size; col++) {
                boardArray[row][col] = Mark.BLANK;
            }
        }
    }



    /**
     * Gets the size of the game board.
     *
     * @return The size of the game board.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Places the specified mark at the given location on the board.
     * if the given location is not BLANK, the function return false. otherwise true.
     *
     * @param mark The mark to be placed.
     * @param row  The row index.
     * @param col  The column index.
     * @return {@code true} if the mark is successfully placed,
     * {@code false} if the location is already occupied.
     */
    public boolean putMark(Mark mark, int row, int col) {
        if(row<0 || col<0 || row>this.size-1 || col>this.size-1) {
            return false;
        }
        if(this.boardArray[row][col]!=Mark.BLANK){
            return false;
        }
        this.boardArray[row][col]=mark;
        return true;
    }

    /**
     * Retrieves the mark at the specified location on the board.
     *
     * @param row The row index.
     * @param col The column index.
     * @return The mark at the specified location, or BLANK if the
     * indices are out of bounds.
     */
    public Mark getMark(int row, int col) {
        if(row<0 || row>this.size-1 || col<0 || col>this.size-1) {
            return Mark.BLANK;
        }
        return this.boardArray[row][col];
    }
}

