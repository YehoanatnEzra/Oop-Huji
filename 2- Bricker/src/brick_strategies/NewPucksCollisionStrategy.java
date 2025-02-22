package brick_strategies;

import bricker.brick_strategies.StrategyDecorator;
import bricker.gameobjects.Ball;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;

/**
 * Represents a collision strategy for handling collisions between two pucks in the game.
 * This strategy adds both pucks back to the game object collection and removes the collided object.
 */
public class NewPucksCollisionStrategy implements StrategyDecorator {
    private GameObjectCollection gameObjectCollection;
    private  BasicCollisionStrategy basicCollisionStrategy;
    private Ball puck1;
    private Ball puck2;

    /**
     * Constructs a new NewPucksCollisionStrategy instance.
     *
     * @param gameObjectCollection    The collection of game objects.
     * @param basicCollisionStrategy  The basic collision strategy to use.
     * @param puck1                   The first puck involved in the collision.
     * @param puck2                   The second puck involved in the collision.
     */
    public NewPucksCollisionStrategy(GameObjectCollection gameObjectCollection,
                                     BasicCollisionStrategy basicCollisionStrategy  ,Ball puck1, Ball puck2){
        this.gameObjectCollection = gameObjectCollection;
        this.basicCollisionStrategy = basicCollisionStrategy;
        this.puck1 = puck1;
        this.puck2 = puck2;
    }

    /**
     * Handles a collision between two game objects by adding both pucks back to the game object collection
     * and removing the collided object.
     *
     * @param thisObj  The game object on which the collision occurs.
     * @param otherObj The other game object involved in the collision.
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        this.gameObjectCollection.addGameObject(this.puck1);
        this.gameObjectCollection.addGameObject(this.puck2);
        this.basicCollisionStrategy.onCollision(thisObj, otherObj);
    }
}
