package brick_strategies;

import bricker.gameobjects.Ball;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;

/**
 * Represents a basic collision strategy for handling collisions between game objects.
 * This strategy removes the collided object from the game object collection.
 */
public class BasicCollisionStrategy implements bricker.brick_strategies.CollisionStrategy {
    private GameObjectCollection gameObjectCollection;

    /**
     * Constructs a new BasicCollisionStrategy instance.
     *
     * @param gameObjectCollection The collection of game objects.
     */
    public BasicCollisionStrategy(GameObjectCollection gameObjectCollection) {
        this.gameObjectCollection = gameObjectCollection;
    }
    /**
     * Handles a collision between two game objects by removing the collided
     * object from the game object collection.
     *
     * @param thisObj  The game object on which the collision occurs.
     * @param otherObj The other game object involved in the collision.
     */
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        this.gameObjectCollection.removeGameObject(thisObj);
    }
}
