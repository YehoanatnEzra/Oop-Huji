package brick_strategies;

import bricker.brick_strategies.StrategyDecorator;
import bricker.gameobjects.PerkPuddle;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.util.Counter;
/**
 * Represents a collision strategy for handling collisions involving a
 * PerkPuddle in the game.
 * This strategy adds the PerkPuddle to the game object collection if no
 * perk paddle exists,
 * informs the BrickerGameManager about the creation of the perk paddle,
 * and removes the collided object.
 */
public class PerkPuddleCollisionStrategy implements StrategyDecorator {
    private static final int NO_PERK_PUDDLE = 0;
    private GameObjectCollection gameObjectCollection;
    private  BasicCollisionStrategy basicCollisionStrategy;
    private PerkPuddle perkPuddle;
    private Counter perkPaddleState;

    /**
     * Constructs a new PerkPuddleCollisionStrategy instance.
     *
     * @param gameObjectCollection The collection of game objects.
     * @param basicCollisionStrategy The basic collision strategy to use.
     * @param perkPuddle           The PerkPuddle object involved in the collision.
     * @param perkPaddleState      The counter representing the state of the perk paddle.
     */
    public PerkPuddleCollisionStrategy(GameObjectCollection gameObjectCollection
            ,BasicCollisionStrategy
            basicCollisionStrategy,PerkPuddle perkPuddle, Counter perkPaddleState) {
        this.gameObjectCollection = gameObjectCollection;
        this.basicCollisionStrategy = basicCollisionStrategy;
        this.perkPuddle = perkPuddle;
        this.perkPaddleState = perkPaddleState;
    }

    /**
     * Handles a collision involving a PerkPuddle by adding it to the game object
     * collection if no perk paddle exists,
     * informing the BrickerGameManager about the creation of the perk paddle,
     * and removing the collided object.
     *
     * @param thisObj  The game object on which the collision occurs.
     * @param otherObj The other game object involved in the collision.
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        if(perkPaddleState.value() == NO_PERK_PUDDLE){
            this.gameObjectCollection.addGameObject(this.perkPuddle);
            this.perkPuddle.paddleCreated(); // informs the BrickerGameManager a
            // perk paddle was created.
        }
        this.basicCollisionStrategy.onCollision(thisObj, otherObj);
    }
}
