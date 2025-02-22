package bricker.brick_strategies;

import danogl.GameObject;

/**
 * Represents a strategy interface for handling collisions between game objects.
 */
public interface CollisionStrategy {
    /**
     * Handles a collision between two game objects.
     *
     * @param thisObj  The game object on which the collision occurs.
     * @param otherObj The other game object involved in the collision.
     */
    public void onCollision(GameObject thisObj, GameObject otherObj);
}
