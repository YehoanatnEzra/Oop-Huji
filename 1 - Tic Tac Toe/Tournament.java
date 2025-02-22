/**
 * The Tournament class represents a series of Tic-Tac-Toe games between two players.
 * It tracks the number of rounds, the players, and the renderer for displaying the results.
 */
class Tournament {
    // Error messages for invalid player names and renderer types
    private static final String PLAYER_NAME_INVLAID = "Choose a player, and start again.\n" +
            "The players: [human, clever, whatever, genius] ";
    private static final String RENDERER_INVLAID = "Choose a renderer, and start again.\n" +
            "Please choose one of the following [console, none] ";
    // Tournament attributes
    private int rounds;
    private Renderer renderer;
    private Player player1;
    private Player player2;

    /**
     * Constructs a Tournament object with the specified number of rounds,renderer,and players.
     *
     * @param rounds   The number of rounds in the tournament.
     * @param renderer The renderer for displaying the results.
     * @param player1  The first player participating in the tournament.
     * @param player2  The second player participating in the tournament.
     */
    public Tournament(int rounds, Renderer renderer, Player player1, Player player2){
        this.rounds=rounds;
        this.renderer=renderer;
        this.player1=player1;
        this.player2=player2;
    }

    /**
     * Plays the tournament by running multiple rounds of Tic-Tac-Toe games.
     *
     * @param size        The size of the game board.
     * @param winStreak   The number of consecutive marks required to win.
     * @param playerName1 The name of the first player.
     * @param playerName2 The name of the second player.
     */
public void playTournament(int size, int winStreak, String playerName1, String playerName2) {
    Player playerX, playerY;
    int player1WinsCount=0;
    int player2WinsCount=0;
    int drawCount=0;

    for( int round=0; round<this.rounds; round++) {
        if(round%2==0) {
            playerX=this.player1;
            playerY=this.player2;
        }
        else{
            playerX=this.player2;
            playerY=this.player1;
        }
        Game game = new Game(playerX, playerY, size, winStreak, this.renderer);
        Mark winnerMark = game.run();
        if(winnerMark==Mark.BLANK){
            drawCount++;
        }
        else{
            if((winnerMark==Mark.X && round%2==0) ||(winnerMark==Mark.O && round%2==1)) {
                player1WinsCount++;
            }
            else {
                player2WinsCount++;
            }
        }
    }

    printResult(playerName1, playerName2, player1WinsCount, player2WinsCount, drawCount);
}
    /**
     * Prints the results of the tournament, including the number of wins for each player and draws.
     *
     * @param playerName1     The name of the first player.
     * @param playerName2     The name of the second player.
     * @param player1WinsCount The number of rounds won by the first player.
     * @param player2WinsCount The number of rounds won by the second player.
     * @param drawCount        The number of drawn rounds.
     */
    private void printResult(String playerName1, String playerName2, int player1WinsCount,
                        int player2WinsCount, int drawCount){
        System.out.println("######### Results #########");
        System.out.println("Player 1, " + playerName1 + " won: " + player1WinsCount+ " rounds");
        System.out.println("Player 2, " + playerName2 + " won: " + player2WinsCount+ " rounds");
        System.out.println("Ties: " + drawCount);
    }

    /**
     * The main method of the program that initializes and runs the tournament based
     * on command-line arguments.
     *
     * @param args Command-line arguments containing the number of rounds,
     * board size, win streak,  renderer type, and player names.
     */
    public static void main(String[] args) {

        int round = Integer.parseInt(args[0]);
        int size = Integer.parseInt(args[1]);
        int winStreak = Integer.parseInt(args[2]);
        if(size<winStreak || winStreak<2) {
            winStreak=size;
        }
        RendererFactory rendererFactory = new RendererFactory();
        PlayerFactory playerFactory = new PlayerFactory();

        Renderer renderer= rendererFactory.buildRenderer(args[3],size);
        if(renderer==null) {
            System.out.println(RENDERER_INVLAID);
            return;
        }
        Player player1 =playerFactory.buildPlayer(args[4]);
        Player player2 =playerFactory.buildPlayer(args[5]);
        if(player1==null || player2==null) {
            System.out.println(PLAYER_NAME_INVLAID);
            return;
        }
        Tournament tournament = new Tournament(round, renderer, player1,player2);
        tournament.playTournament(size,winStreak, args[4], args[5]);
        }
    }

