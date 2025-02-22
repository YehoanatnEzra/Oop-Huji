package brick_strategies;

import bricker.brick_strategies.StrategyDecorator;
import bricker.gameobjects.MovingGraphicStrike;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;

/**
 * Collision strategy for adding a moving graphic strike.
 * Implements the StrategyDecorator interface.
 */
public class AddStrikeCollisionStrategy implements StrategyDecorator {
    private GameObjectCollection gameObjectCollection;
    private MovingGraphicStrike movingGraphicStrike;
    private  BasicCollisionStrategy basicCollisionStrategy;
    /**
     * Constructs a new AddStrikeCollisionStrategy instance.
     *
     * @param gameObjectCollection   The collection of game objects.
     * @param basicCollisionStrategy The basic collision strategy to use.
     * @param movingGraphicStrike    The moving graphic strike object to add.
     */
    public AddStrikeCollisionStrategy(GameObjectCollection gameObjectCollection, BasicCollisionStrategy
            basicCollisionStrategy, MovingGraphicStrike movingGraphicStrike) {
        this.gameObjectCollection = gameObjectCollection;
        this.basicCollisionStrategy = basicCollisionStrategy;
        this.movingGraphicStrike = movingGraphicStrike;
    }
    /**
     * Handles a collision by adding the moving graphic strike to the game object collection.
     * Calls the onCollision method of the basic collision strategy.
     *
     * @param thisObj  The game object on which the collision occurs.
     * @param otherObj The other game object involved in the collision.
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        gameObjectCollection.addGameObject(this.movingGraphicStrike);
        this.basicCollisionStrategy.onCollision(thisObj, otherObj);
    }
}

