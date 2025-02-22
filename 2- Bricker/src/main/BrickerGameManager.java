package main;

import brick_strategies.StrategiesFactory;
import bricker.brick_strategies.CollisionStrategy;
import bricker.gameobjects.Ball;
import bricker.gameobjects.Brick;
import bricker.gameobjects.MovingGraphicStrike;
import bricker.gameobjects.Paddle;
import bricker.gameobjects.PerkPuddle;
import bricker.gameobjects.StrikesGraphic;
import bricker.gameobjects.StrikesNumeric;
import bricker.main.Constant;
import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.components.CoordinateSpace;
import danogl.gui.*;
import danogl.gui.rendering.Camera;
import danogl.gui.rendering.RectangleRenderable;
import danogl.gui.rendering.Renderable;
import danogl.gui.rendering.TextRenderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;



/**
 * Manages the game logic and objects for the Joni&Lior bricker.IO game.
 */
public class BrickerGameManager extends GameManager {
    private Vector2 windowDimensions;
    private int bricksInRow;
    private int rowsOfBricks;
    private WindowController windowController;
    private UserInputListener inputListener;
    private Ball ball;
    private GameObject backGround;
    private Counter strikesCount;
    private Counter cameraSwitch;
    private Counter bricksCount;
    private Vector2 graphicStrikeDim;
    private Vector2 graphicsStrikeDim;
    private int cameraCount;
    private Counter perkPaddleState; //0: perk paddle doesn't exit. 1+i: exists with i collisions. (0<i<=4)

    /**
     * Constructs a BrickerGameManager instance with the specified window title, window dimensions,
     * number of bricks in a row, and number of rows of bricks.
     *
     * @param windowTitle      The title of the game window.
     * @param windowDimensions The dimensions of the game window.
     * @param bricksInRow      The number of bricks in a row.
     * @param rowsOfBricks     The number of rows of bricks.
     */
    public BrickerGameManager(String windowTitle, Vector2 windowDimensions, int bricksInRow,
                              int rowsOfBricks) {
        super(windowTitle, windowDimensions);
        this.windowDimensions = windowDimensions;
        this.bricksInRow = bricksInRow;
        this.rowsOfBricks = rowsOfBricks;
        int graphicStrikeWidth = (int) ((windowDimensions.x() /
                bricker.main.Constant.GRAPHIC_STRIKE_TO_WINDOW_RATIO) / Constant.STRIKES);
        int graphicStrikeHeight = (int) ((windowDimensions.y() /
                bricker.main.Constant.GRAPHIC_STRIKE_TO_WINDOW_RATIO) / Constant.STRIKES);
        this.graphicStrikeDim = new Vector2(graphicStrikeWidth, graphicStrikeHeight);
        int graphicsStrikeWidth = (int) ((this.windowDimensions.x() /
                Constant.GRAPHIC_STRIKE_TO_WINDOW_RATIO));
        this.graphicsStrikeDim = new Vector2(graphicsStrikeWidth, graphicStrikeHeight);

    }

    private MovingGraphicStrike createMovingStrike(Vector2 middleOfBrickLocation,
                                                   ImageReader imageReader) {
        Renderable strikeImage = imageReader.readImage(Constant.MOVING_STRIKE_PATH,
                true);
        MovingGraphicStrike generated = new MovingGraphicStrike(middleOfBrickLocation,
                this.graphicStrikeDim, strikeImage, this.strikesCount,
                Constant.MAXIMUM_STRIKES);
        generated.setVelocity(Vector2.DOWN.mult(Constant.MOVING_STRIKE_VELOCITY));

        return generated;
    }
    private PerkPuddle createPerkPuddle(ImageReader imageReader,
                                        UserInputListener inputListener) {
        Renderable paddle = imageReader.readImage(bricker.main.Constant.PADDLE_IMAGE_PATH,
                true);
        PerkPuddle perkPaddle = new PerkPuddle(Vector2.ZERO, Constant.PADDLE_SIZE_VEC,
                paddle, inputListener, bricker.main.Constant.PADDLE_SPEED, this.perkPaddleState);
        Vector2 location = new Vector2(this.windowDimensions.x() / 2,
                windowDimensions.y() / 2);
        perkPaddle.setCenter(location);
        return perkPaddle;
    }
    private Ball createPuck(ImageReader imageReader, SoundReader soundReader) {
        Renderable puckImage = imageReader.readImage(Constant.PUCK_FILE_PATH,
                true);
        Sound collisionSound = soundReader.readSound(Constant.BALL_SOUND_PATH);
        Ball puck = new Ball(Vector2.ZERO, Constant.BALL_SIZE_VEC.mult(0.75f),
                puckImage, collisionSound);
        placeBall(windowDimensions.mult(0.5f), puck);
        return puck;
    }

    private void addBall(ImageReader imageReader, SoundReader soundReader) {
        Renderable ballImage = imageReader.readImage(Constant.BALL_IMAGE_PATH,
                true);
        Sound collisionSound = soundReader.readSound(Constant.BALL_SOUND_PATH);
        this.ball = new Ball(Vector2.ZERO, Constant.BALL_SIZE_VEC, ballImage,
                collisionSound);
        placeBall(windowDimensions.mult(0.5f), this.ball);
        gameObjects().addGameObject(this.ball);
    }

    private void addPaddle(ImageReader imageReader, UserInputListener inputListener) {
        Renderable paddleImage = imageReader.readImage(Constant.PADDLE_IMAGE_PATH,
                true);
        GameObject userPaddle = new Paddle(Vector2.ZERO, Constant.PADDLE_SIZE_VEC,
                paddleImage, inputListener,
                Constant.PADDLE_SPEED);
        Vector2 initialPosPaddle = new Vector2(this.windowDimensions.x() / 2,
                windowDimensions.y() -
                (Constant.THICKNESS_WALL + Constant.PADDLE_SIZE_VEC.y()));
        userPaddle.setCenter(initialPosPaddle);
        gameObjects().addGameObject(userPaddle);
    }

    private void addBricks(ImageReader imageReader, float bricksInRow, float rowsOfBricks,
                           SoundReader soundReader,
                           UserInputListener inputListener) {
        Renderable brickImage = imageReader.readImage( Constant.BRICK_IMAGE_PATH, true);
        float sizeBrickVecX = (int) ((windowDimensions.x() - 2 * (Constant.THICKNESS_WALL)) / bricksInRow);
        float sizeBrickVecY = Constant.THICKNESS_BRICK;
        Vector2 sizeBrickVec = new Vector2(sizeBrickVecX, sizeBrickVecY);

        for (int i = 0; i < rowsOfBricks; i++) {
            for (int j = 0; j < bricksInRow; j++) {
                float locationX = Constant.THICKNESS_WALL + j * sizeBrickVec.x();
                // Calculate x-coordinate
            float locationY = Constant.THICKNESS_WALL + i * Constant.THICKNESS_BRICK;
            // Calculate y-coordinate
                Vector2 middleOfBrickLocation = new Vector2((float) (locationX +
                        (0.5 * sizeBrickVecX)),
                        (float) (locationY + (0.5 * sizeBrickVecY)));
                CollisionStrategy collisionStrategy = chooseStrategy(imageReader,
                        soundReader, inputListener, middleOfBrickLocation);
                GameObject brick = new Brick(new Vector2(locationX, locationY),
                        sizeBrickVec, brickImage,
                        collisionStrategy, bricksCount);
                gameObjects().addGameObject(brick);
            }
        }
    }
    private GameObject addBackground(ImageReader imageReader) {
        // Full background
        Renderable backgroundImage = imageReader.readImage(Constant.BACKGROUND_IMAGE_PATH,
                false);
        GameObject background = new GameObject(Vector2.ZERO, windowDimensions,
                backgroundImage);
        gameObjects().addGameObject(background, Layer.BACKGROUND);
        return background;
    }

    private void addWalls(float windowDimensionsX, float windowDimensionsY,
                          float thickness, Color wallColor) {
        RectangleRenderable color = new RectangleRenderable(wallColor);
        // top wall
        GameObject topWall = new GameObject(Vector2.ZERO, new Vector2(windowDimensionsX,
                thickness), color);
        topWall.setCenter(new Vector2(windowDimensionsX / 2, 0));
        gameObjects().addGameObject(topWall);
        // left wall
        GameObject leftWall = new GameObject(Vector2.ZERO, new Vector2(thickness,
                windowDimensionsY), color);
        leftWall.setCenter(new Vector2(0, windowDimensionsY / 2));
        gameObjects().addGameObject(leftWall);
        // right wall
        GameObject rightWall = new GameObject(Vector2.ZERO, new Vector2(thickness,
                windowDimensionsY), color);
        rightWall.setCenter(new Vector2(windowDimensionsX, windowDimensionsY / 2));
        gameObjects().addGameObject(rightWall);
        // bottom wall background
        GameObject botWall = new GameObject(Vector2.ZERO, new Vector2(windowDimensionsX,
                thickness), color);
        botWall.setCenter(new Vector2(windowDimensionsX / 2, windowDimensionsY));
        gameObjects().addGameObject(botWall, Layer.BACKGROUND);
    }

    private void placeBall(Vector2 centerVec, Ball ballObject) {
        /*
         * Places the ball in the middle of the screen with a random direction velocity
         */
        ballObject.setVelocity(Vector2.DOWN.mult(Constant.BALL_VELOCITY));
        ballObject.setCenter(centerVec);

        //Generates random initial velocity direction for a ball
        Random rand = new Random();
        double angle = rand.nextDouble() * Math.PI;
        float ballVelX = (float) Math.cos(angle) * Constant.BALL_VELOCITY;
        float ballVelY = (float) Math.sin(angle) * Constant.BALL_VELOCITY;
        ballObject.setVelocity(new Vector2(ballVelX, ballVelY));
    }

    private void checkBounds() {
        /*
         * Keeps the userPaddle in bounds.
         */
        float leftBounds = this.windowDimensions.x() - Constant.THICKNESS_WALL;
        float rightBounds = Constant.THICKNESS_BRICK;
        float bottomBounds = windowDimensions.y() - Constant.THICKNESS_WALL;

        for (GameObject gameObject : gameObjects()) {
            switch (gameObject.getTag()) {
                case "userPaddle":
                case "perkPaddle":
                    if (gameObject.getTopLeftCorner().x() < rightBounds) {
                        Vector2 movementBackLeft = new Vector2(Constant.PADDLE_SPEED / 100, 0);
                        gameObject.setTopLeftCorner(gameObject.getTopLeftCorner()
                                .add(movementBackLeft));
                    }
                    if (gameObject.getTopLeftCorner().x() + Constant.PADDLE_SIZE_VEC.x()
                            > leftBounds) {
                        Vector2 movementBackRight = new Vector2(-1 * Constant.PADDLE_SPEED
                                / 100, 0);
                        gameObject.setTopLeftCorner(gameObject.getTopLeftCorner()
                                .add(movementBackRight));
                    }
                    break;
                case "Ball":
                    if (!gameObject.equals(this.ball) && gameObject.getTopLeftCorner().y()
                            > bottomBounds) {
                        gameObjects().removeGameObject(gameObject);
                    }
                    break;
                case "movingGraphicStrike":
                    if(gameObject.getTopLeftCorner().y() >bottomBounds) {
                        gameObjects().removeGameObject(gameObject);
                    }
                    break;
            }
        }
    }

    private void checkCameraState() {
        if (this.cameraSwitch.value() == Constant.ON && this.cameraCount ==
                Constant.NO_COLLISION_WITH_CAMERA) {
            setCamera(new Camera(this.ball, Vector2.ZERO, this.windowDimensions.mult(1.2f),
                    this.windowDimensions));
            this.cameraCount = this.ball.getCollisionCounter();
            this.backGround.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
            return;
        }

        if (this.ball.getCollisionCounter() - cameraCount == Constant.NUM_COLLISION_TO_CHANGE_CAMERA) {
            setCamera(null);
            this.cameraCount = Constant.NO_COLLISION_WITH_CAMERA;
            this.cameraSwitch.decrement();
        }
    }

    private void checkPerkPaddleState() {
        if (perkPaddleState.value() >= Constant.PERK_PUDDLE_EXPIRATION) {
            for (GameObject gameObject : gameObjects()) {
                if (gameObject.getTag().equals("perkPaddle") ) {
                    this.gameObjects().removeGameObject(gameObject);
                    this.perkPaddleState.reset();
                }
            }
        }
    }

    private void checkForGameEnd() {
        double ballHeight = this.ball.getCenter().y();

        // Ball reached beneath user's paddle
        if (ballHeight > windowDimensions.y() - Constant.THICKNESS_WALL) {
            this.strikesCount.decrement(); // -1 life
            if (this.strikesCount.value() == Constant.RESET) {
                endGame(Constant.LOST_MESSAGE);
            } else
                placeBall(windowDimensions.mult(0.5f), this.ball);
        }
        // Broke all Bricks == win
        if (bricksCount.value() == Constant.RESET|| this.inputListener.isKeyPressed(KeyEvent.VK_W)) {
            endGame(Constant.WIN_MESSAGE);
        }
    }

    private void endGame(String prompt) {
        /*
         * Ends the game in win or lose states
         */
        prompt += Constant.PLAY_AGAIN_MESSAGE;
        if (windowController.openYesNoDialog(prompt)) {
            windowController.resetGame();
        }
        else
            windowController.closeWindow();
    }

    private CollisionStrategy chooseStrategy(ImageReader imageReader, SoundReader soundReader,
                                         UserInputListener inputListener, Vector2 middleOfBrickLocation) {

        Ball puck1 = createPuck(imageReader, soundReader);
        Ball puck2 = createPuck(imageReader, soundReader);
        Ball puck3 = createPuck(imageReader, soundReader);
        Ball puck4 = createPuck(imageReader, soundReader);
        Ball puck5 = createPuck(imageReader, soundReader);
        Ball puck6 = createPuck(imageReader, soundReader);
        PerkPuddle perkPuddle = createPerkPuddle(imageReader, inputListener);
        MovingGraphicStrike movingStrike1 = createMovingStrike(middleOfBrickLocation, imageReader);
        MovingGraphicStrike movingStrike2 = createMovingStrike(middleOfBrickLocation, imageReader);
        MovingGraphicStrike movingStrike3 = createMovingStrike(middleOfBrickLocation, imageReader);

        StrategiesFactory strategiesFactory = new StrategiesFactory(gameObjects(), this.ball, puck1,
                puck2, puck3, puck4, puck5, puck6,
                movingStrike1, movingStrike2, movingStrike3,
                perkPuddle, perkPaddleState, cameraSwitch,
                Constant.RAND_LIMIT);

        return strategiesFactory.buildStrategies();
    }
    private void generateGraphicStrikesDisplay(ImageReader imageReader) {
        Renderable heartImage = imageReader.readImage(Constant.GRAPHIC_STRIKE_IMAGE, true);
        // calculation the dimensions of the heart
        StrikesGraphic strikesGraphic = new StrikesGraphic(this.graphicsStrikeDim, this.windowDimensions,
                this.graphicStrikeDim, heartImage, gameObjects(), this.strikesCount);
        gameObjects().addGameObject(strikesGraphic);
    }

    private void generateNumericStrikesDisplay() {
        TextRenderable displayNumber = new TextRenderable(String.format("Strikes left: %d",
                Constant.STRIKES));
        Vector2 strikesNumericDim = new Vector2(20, 20);
        Vector2 strikesNumericLocation = new Vector2(10, this.windowDimensions.y() - 100);
        StrikesNumeric strikesNumeric = new StrikesNumeric(strikesNumericLocation,
                strikesNumericDim,
                displayNumber, gameObjects(), this.strikesCount);
        gameObjects().addGameObject(strikesNumeric, Layer.UI);
    }


    /**
     * Initializes the game by creating game objects, counters, and setting up the game environment.
     *
     * @param imageReader      The image reader used to load images for game objects.
     * @param soundReader      The sound reader used to load sounds for game objects.
     * @param inputListener    The input listener used to handle user input.
     * @param windowController The window controller used to manage the game window.
     */
    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader,
                               UserInputListener inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        //Creates game objects:
        this.strikesCount = new Counter(bricker.main.Constant.STRIKES);
        this.bricksCount = new Counter(bricker.main.Constant.RESET);
        this.perkPaddleState = new Counter(bricker.main.Constant.RESET);
        this.cameraSwitch = new Counter(Constant.OFF);
        this.cameraCount = Constant.NO_COLLISION_WITH_CAMERA;
        this.windowController = windowController;
        this.inputListener = inputListener;
        addBall(imageReader, soundReader);
        addPaddle(imageReader, inputListener);
        addBricks(imageReader, this.bricksInRow, this.rowsOfBricks, soundReader, inputListener);
        this.backGround = addBackground(imageReader);
        generateGraphicStrikesDisplay(imageReader);
        generateNumericStrikesDisplay();
        addWalls(windowDimensions.x(), windowDimensions.y(), Constant.THICKNESS_WALL, Color.BLACK);
    }

    /**
     * Updates the game state for the current frame.
     *
     * @param deltaTime The time elapsed, in seconds, since the last frame.
     *                  Can be used to determine changes in game state based on time.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        checkBounds(); // Keeps userPaddle in bounds
        checkPerkPaddleState();
        checkCameraState();
        checkForGameEnd();


    }

    /**
     * The entry point of the application.
     *
     * @param args Command-line arguments. The first argument is the number of bricks in a row,
     *             and the second argument is the number of rows of bricks. If only one argument
     *             is provided, it's assumed to be the number of rows of bricks.
     */
    public static void main(String[] args) {
        int bricksInRow = Constant.DEFAULT_NUM_BRICK_IN_ROW;
        int rowsOfBricks = Constant.DEFAULT_NUM_ROWS_OF_BRICKS;
        if (args.length == Constant.ARGS_LEN) {
            bricksInRow = Integer.parseInt(args[0]);
            rowsOfBricks = Integer.parseInt(args[1]);
        }
        new BrickerGameManager(Constant.NAME_OF_GAME, new Vector2(Constant.SCREEN_VECTOR_X,
                Constant.SCREEN_VECTOR_y),
                bricksInRow, rowsOfBricks).run();
    }
}
