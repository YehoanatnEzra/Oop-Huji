import java.util.Random;
/**
 * The WhateverPlayer class implements the Player interface and represents
 * a player that makes random moves on the game board.
 * It chooses random positions until a valid move is made.
 */
class WhateverPlayer implements Player {
    private Random random;
    /**
     * Constructs a WhateverPlayer object with a default constructor.
     */
   public WhateverPlayer() {
        this.random=new Random();
    }
    /**
     * Plays a turn by making random moves on the game board until a valid move is made.
     *
     * @param board The game board.
     * @param mark  The mark (X or O) of the player.
     */
    public void playTurn(Board board, Mark mark) {
        boolean stopFlag=false;
        while(!stopFlag) {
            int row = this.random.nextInt(board.getSize());
            int col = this.random.nextInt(board.getSize());
            stopFlag= board.putMark(mark, row, col);
        }
     }
}
