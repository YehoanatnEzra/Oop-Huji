package bricker.gameobjects;

import bricker.main.Constant;
import danogl.GameObject;
import danogl.gui.Sound;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.awt.event.KeyEvent;
/**
 * Represents a user-controlled paddle GameObject in the game.
 */
public class Paddle extends GameObject {

    private float  movementSpeed;
    private UserInputListener inputListener;
    /**
     * Constructs a new Paddle instance.
     *
     * @param topLeftCorner Position of the paddle, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height of the paddle in window coordinates.
     * @param renderable    The renderable representing the paddle. Can be null, in which case
     *                      the paddle will not be rendered.
     * @param inputListener The user input listener for the paddle.
     * @param movementSpeed The movement speed of the paddle.
     */
    public Paddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                      UserInputListener inputListener, float movementSpeed) {
        super(topLeftCorner, dimensions, renderable);
        this.inputListener=inputListener;
        this.movementSpeed = movementSpeed;
        if(this.movementSpeed==0) {
            this.movementSpeed= Constant.USER_PADDLE_MOVEMENT_DEFAULT;
        }
    }

    @Override
    public String getTag() {
        return "userPaddle";
    }
    /**
     * Updates the Paddle instance.
     *
     * This method should be called once per frame.
     *
     * @param deltaTime The time elapsed since the last frame, in seconds.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Vector2 movementDir = Vector2.ZERO;
        if (this.inputListener.isKeyPressed(KeyEvent.VK_LEFT)) {
            movementDir = movementDir.add(Vector2.LEFT.mult(movementSpeed));
        }
        if (this.inputListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
            movementDir = movementDir.add(Vector2.RIGHT.mult(movementSpeed));
        }
        setVelocity(movementDir);
    }
}
