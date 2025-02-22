package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.gui.UserInputListener;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.awt.event.KeyEvent;
import java.util.Objects;

/**
 * Represents a paddle with a special perk in the game.
 */
public class PerkPuddle extends Paddle{
    private Counter perkPaddleState;
    /**
     * Constructs a new PerkPuddle instance.
     *
     * @param topLeftCorner    Position of the perk paddle, in window coordinates (pixels).
     *                         Note that (0,0) is the top-left corner of the window.
     * @param dimensions       Width and height of the perk paddle in window coordinates.
     * @param renderable       The renderable representing the perk paddle. Can be null, in which case
     *                         the perk paddle will not be rendered.
     * @param inputListener    The user input listener for the perk paddle.
     * @param movementSpeed    The movement speed of the perk paddle.
     * @param perkPaddleState  The counter to track the state of the perk paddle.
     */
    public PerkPuddle(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                      UserInputListener inputListener, float movementSpeed, Counter perkPaddleState) {
        super(topLeftCorner, dimensions, renderable, inputListener, movementSpeed);
        this.perkPaddleState = perkPaddleState;
    }

    /**
     * Increments the perk paddle state counter when the paddle is created.
     */
    public void paddleCreated(){
        this.perkPaddleState.increment();
    }

    /**
     * Returns the tag associated with this game object.
     * This method is used to identify the type of the game object.
     *
     * @return The tag associated with this game object.
     */
    public String getTag() {
        return "perkPaddle";
    }

    /**
     * Handles the behavior of the perk paddle upon colliding with another game object.
     * Increments the perk paddle state counter if colliding with a ball.
     *
     * @param other     The other game object involved in the collision.
     * @param collision Information about the collision.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        if(Objects.equals(other.getTag(), "Ball")){
            super.onCollisionEnter(other, collision);
            this.perkPaddleState.increment();
        }
    }
}
