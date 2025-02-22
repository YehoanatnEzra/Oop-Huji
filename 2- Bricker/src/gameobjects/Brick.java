package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;
import bricker.brick_strategies.*;

/**
 * Represents a brick GameObject in the game.
 */
public class Brick extends GameObject {
    private  String COLLISION_MESSAGE = "collision with brick detected";
    private CollisionStrategy collisionStrategy;
    Counter totalBricks;
    /**
     * Constructs a new Brick instance with a counter for total bricks.
     *
     * @param topLeftCorner     Position of the brick, in window coordinates (pixels).
     *                          Note that (0,0) is the top-left corner of the window.
     * @param dimensions        Width and height of the brick in window coordinates.
     * @param renderable        The renderable representing the brick. Can be null, in which case
     *                          the brick will not be rendered.
     * @param collisionStrategy The collision strategy to be used by the brick.
     * @param totalBricks       The counter to track the total number of bricks.
     */
    public Brick(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                 CollisionStrategy collisionStrategy, Counter totalBricks) {
        super(topLeftCorner, dimensions, renderable);
        totalBricks.increment();
        this.totalBricks = totalBricks;
        this.collisionStrategy=collisionStrategy;
    }
    /**
     * Constructs a new Brick instance without a counter for total bricks.
     *
     * @param topLeftCorner     Position of the brick, in window coordinates (pixels).
     *                          Note that (0,0) is the top-left corner of the window.
     * @param dimensions        Width and height of the brick in window coordinates.
     * @param renderable        The renderable representing the brick. Can be null, in which case
     *                          the brick will not be rendered.
     * @param collisionStrategy The collision strategy to be used by the brick.
     */
    public Brick(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable,
                 CollisionStrategy collisionStrategy) {
        super(topLeftCorner, dimensions, renderable);
        this.collisionStrategy=collisionStrategy;
        this.totalBricks = null;
    }

    /**
     * Handles the behavior of the brick upon colliding with another game object.
     * Decrements the total bricks count and delegates collision handling to the assigned collision strategy.
     *
     * @param other     The other game object involved in the collision.
     * @param collision Information about the collision.
     */
    @Override
    public void onCollisionEnter(GameObject other, Collision collision) {
        this.totalBricks.decrement();
        super.onCollisionEnter(other, collision);
        this.collisionStrategy.onCollision(this, other);
    }
}
