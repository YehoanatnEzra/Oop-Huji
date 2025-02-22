package brick_strategies;

import bricker.brick_strategies.CollisionStrategy;

import bricker.brick_strategies.StrategyDecorator;
import bricker.main.Constant;
import danogl.GameObject;

/**
 * Represents a collision strategy that combines two or three
 * different collision strategies to handle collisions.
 */
public class DualBehaviorCollisionStrategy implements StrategyDecorator {

    private boolean isMainDuel;
    private StrategiesFactory strategiesFactory;
    private CollisionStrategy collisionStrategy1;
    private CollisionStrategy collisionStrategy2;
    private CollisionStrategy collisionStrategy3;
    private BasicCollisionStrategy basicCollisionStrategy;


    /**
     * Constructs a DualBehaviorCollisionStrategy object with the given basic collision strategy and strategy
     * factory.
     *
     * @param basicCollisionStrategy The basic collision strategy to use as part of the dual behavior.
     * @param strategiesFactory      The factory to create additional collision strategies if needed.
     */
    public DualBehaviorCollisionStrategy(BasicCollisionStrategy basicCollisionStrategy,
                                         StrategiesFactory strategiesFactory) {

        this.basicCollisionStrategy = basicCollisionStrategy;
        this.strategiesFactory = strategiesFactory;
        boolean foundDuel = false;
        this.isMainDuel= false;
        if (this.strategiesFactory.getLotteryNum() == Constant.FIRST_LOTTERY){
            this.isMainDuel = true;

        }


        this.collisionStrategy1 = null;
        this.collisionStrategy2 = null;
        this.collisionStrategy3 = null;
        this.strategiesFactory.setRandLimit(Constant.RAND_LIMIT_OF_DUEL_LOTTERY);

        if (this.isMainDuel) {
            this.collisionStrategy1 = this.strategiesFactory.buildStrategies();
            if (this.collisionStrategy1 instanceof DualBehaviorCollisionStrategy) {
                this.strategiesFactory.lotteryNumDec();
                foundDuel = true;
                this.collisionStrategy1 = this.strategiesFactory.buildStrategies();
            }
            this.collisionStrategy2 = this.strategiesFactory.buildStrategies();

            if (collisionStrategy2 instanceof DualBehaviorCollisionStrategy && foundDuel) {
                this.strategiesFactory.lotteryNumDec();
                foundDuel = true;
                this.collisionStrategy2 = this.strategiesFactory.buildStrategies();
            }
            if (foundDuel)
                this.collisionStrategy3 = this.strategiesFactory.buildStrategies();
        }
    }

    /**
     * Handles the collision event between two game objects by
     * delegating to the combined collision strategies.
     *
     * @param thisObj  The first game object involved in the collision.
     * @param otherObj The second game object involved in the collision.
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        if (this.collisionStrategy1!=null)
            this.collisionStrategy1.onCollision(thisObj , otherObj);
        if(this.collisionStrategy2!=null)
            this.collisionStrategy2.onCollision(thisObj , otherObj);
        if (this.collisionStrategy3!=null)
            this.collisionStrategy3.onCollision(thisObj , otherObj);

        this.basicCollisionStrategy.onCollision(thisObj, otherObj);
    }
}

