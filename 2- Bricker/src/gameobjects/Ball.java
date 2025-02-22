package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.Sound;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

import java.util.Objects;

/**
 * Represents a ball GameObject in the game.
 */
public class Ball extends GameObject {

    private Sound collisionSound; // Sound played upon collision
    private int collisionCounter = 0; // Counter to track collisions with bricks

    /**
     * Constructs a new Ball instance.
     *
     * @param topLeftCorner  Position of the ball, in window coordinates (pixels).
     *                       Note that (0,0) is the top-left corner of the window.
     * @param dimensions     Width and height of the ball in window coordinates.
     * @param renderable     The renderable representing the ball. Can be null, in which case
     *                       the ball will not be rendered.
     * @param collisionSound The sound to be played upon collision.
     */
    public Ball(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable, Sound collisionSound) {
        super(topLeftCorner, dimensions, renderable);
        this.collisionSound = collisionSound;
    }

    /**
     * Handles the behavior of the ball upon colliding with another game object.
     *
     * @param other The other game object involved in the collision.
     * @param collision Information about the collision.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        if(Objects.equals(other.getTag(), "movingGraphicStrike")){
            return;
        }
        this.collisionSound.play();
        Vector2 newVel = getVelocity().flipped(collision.getNormal());
        this.setVelocity(newVel);
        if(other instanceof Brick) {
            this.collisionCounter++;
        }
    }

    /**
     * Returns the tag of the ball.
     *
     * @return The tag of the ball.
     */
    @Override
    public String getTag(){
        return "Ball";
    }

    /**
     * Gets the collision counter, which tracks the number of collisions with bricks.
     *
     * @return The collision counter.
     */
    public int getCollisionCounter() {
        return collisionCounter;
    }
}
