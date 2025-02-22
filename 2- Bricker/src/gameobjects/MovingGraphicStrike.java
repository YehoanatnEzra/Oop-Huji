package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.util.Objects;

/**
 * Represents a moving graphic strike in the game.
 * Extends the GraphicStrike class and provides additional functionality
 * for collision handling and strike counting.
 */
public class MovingGraphicStrike extends GraphicStrike {
    private Counter strikesCounter;
    private int maximumStrikes;
    /**
     * Constructs a new MovingGraphicStrike object with the given parameters.
     *
     * @param topLeftCorner   The top-left corner of the strike.
     * @param dimensions      The dimensions of the strike.
     * @param renderable      The renderable object for the strike.
     * @param strikesCounter  The counter for strikes.
     * @param maximumStrikes  The maximum number of strikes allowed.
     */
    public MovingGraphicStrike(Vector2 topLeftCorner, Vector2 dimensions,
                               Renderable renderable, Counter strikesCounter,
                               int maximumStrikes) {
        super(topLeftCorner, dimensions, renderable);
        this.strikesCounter = strikesCounter;
        this.maximumStrikes = maximumStrikes;
    }

    /**
     * Returns the tag associated with this game object.
     * This method is used to identify the type of the game object.
     *
     * @return The tag associated with this game object.
     */
    @Override
    public String getTag() {
        return "movingGraphicStrike";
    }

    /**
     * Handles the collision event when this game object collides with another game object.
     * If the other game object is a user paddle, this method increments the strikes counter
     * if the maximum number of strikes has not been reached.
     *
     * @param other     The other game object involved in the collision.
     * @param collision The collision information.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        if(Objects.equals(other.getTag(), "userPaddle")){
            if(this.strikesCounter.value() < maximumStrikes){
                this.strikesCounter.increment();
            }
        }
    }
}
