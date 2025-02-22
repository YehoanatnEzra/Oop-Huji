package bricker.main;

import danogl.util.Vector2;

/**
 * Contains constant values used throughout the application.
 */
public class Constant {
    /**
     * Default number of bricks in each row.
     */
    public static final int DEFAULT_NUM_BRICK_IN_ROW = 7;

    /**
     * Default number of rows of bricks.
     */
    public static final int DEFAULT_NUM_ROWS_OF_BRICKS = 8;

    /**
     * Constant representing the 'on' state.
     */
    public static final int ON = 1;

    /**
     * Constant representing the 'off' state.
     */
    public static final int OFF = 0;
    /**
     * The number of collisions required to trigger a camera change.
     * When the number of collisions between objects reaches this threshold,
     * the camera behavior is updated accordingly.
     */
    public static int NUM_COLLISION_TO_CHANGE_CAMERA = 4;

    /**
     * Represents the absence of collision with the camera.
     * This constant indicates that there is no collision detected between objects and the camera.
     */
    public static  int NO_COLLISION_WITH_CAMERA = 0;

    /**
     * Thickness of the wall.
     */
    public static final float THICKNESS_WALL = 20;

    /**
     * Thickness of each brick.
     */
    public static final float THICKNESS_BRICK = 15;

    /**
     * Size vector of the paddle.
     */
    public static final Vector2 PADDLE_SIZE_VEC = new Vector2(100, 15);

    /**
     * Size vector of the ball.
     */
    public static final Vector2 BALL_SIZE_VEC = new Vector2(20, 20);

    /**
     * Speed of the paddle.
     */
    public static final float PADDLE_SPEED = 250;

    /**
     * Velocity of the ball.
     */
    public static final float BALL_VELOCITY = 200;

    /**
     * Velocity of the moving strike.
     */
    public static final int MOVING_STRIKE_VELOCITY = 100;

    /**
     * Default movement speed of the user paddle.
     */
    public static final float USER_PADDLE_MOVEMENT_DEFAULT = 100;

    /**
     * Number of strikes.
     */
    public static final int STRIKES = 3;

    /**
     * Maximum number of strikes allowed.
     */
    public static final int MAXIMUM_STRIKES = 4;

    /**
     * Identifier for regular lottery.
     */
    public static final int REGULAR_LOTTERY = 0;

    /**
     * Identifier for the first lottery.
     */
    public static final int FIRST_LOTTERY = 1;

    /**
     * Identifier for the second lottery.
     */
    public static final int SECOND_LOTTERY = 2;

    /**
     * Identifier for the third lottery.
     */
    public static final int THIRD_LOTTERY = 3;

    /**
     * Random limit for the dual lottery.
     */
    public static final int RAND_LIMIT_OF_DUEL_LOTTERY = 5;

    /**
     * Random limit.
     */
    public static final int RAND_LIMIT = 10;

    /**
     * Expiration time for the perk puddle.
     */
    public static final int PERK_PUDDLE_EXPIRATION = 5;

    /**
     * Ratio of the graphic strike to the window.
     */
    public static final int GRAPHIC_STRIKE_TO_WINDOW_RATIO = 5;
    /**
     * Represents a reset state.
     */
    public  static final int RESET =0;

    /**
     * Path to the sound file for the ball.
     */
    public static final String BALL_SOUND_PATH = "assets/Bubble5_4.wav";
    /**
     * Path to the image file for the moving_strike.
     */
    public static  final String MOVING_STRIKE_PATH =  "assets/heart.png";


    /**
     * Path to the file for the puck.
     */
    public static final String PUCK_FILE_PATH = "assets/mockBall.png";

    /**
     * Path to the image file for the brick.
     */
    public static final String BRICK_IMAGE_PATH = "assets/brick.png";

    /**
     * Path to the background image.
     */
    public static final String BACKGROUND_IMAGE_PATH = "assets/DARK_BG2_small.jpeg";

    /**
     * Width of the screen vector.
     */
    public static final float SCREEN_VECTOR_X=700;
    /**
     * Height of the screen vector.
     */
    public static  final float SCREEN_VECTOR_y=500;


    /**
     * Path to the image file for the paddle.
     */
    public static final String PADDLE_IMAGE_PATH = "assets/paddle.png";

    /**
     * Path to the image file for the ball.
     */
    public static final String BALL_IMAGE_PATH = "assets/ball.png";
    /**
     * Path to the image file for the graphic strike.
     */
    public static final String GRAPHIC_STRIKE_IMAGE = "assets/heart.png";

    /**
     * Name of the game
     */
    public static final String NAME_OF_GAME = "Joni&Lior bricker.io";
    /**
     * Represents the message displayed when the game is lost.
     */
    public static final String LOST_MESSAGE = "You've lost ";

    /**
     * Represents the message displayed when the game is won.
     */
    public static final String WIN_MESSAGE = "You've won ";

    /**
     * Represents the message prompting the player to play again.
     */
    public static final String PLAY_AGAIN_MESSAGE = "Play again?";

    /**
     * Represents the length of command line arguments array.
     */
    public static  final int ARGS_LEN = 2;


    /**
     * Private constructor to prevent instantiation of the {@code Constant} class.
     * This class is intended only for accessing its static constants and should not
     * be instantiated.
     */
    public Constant(){}
}
