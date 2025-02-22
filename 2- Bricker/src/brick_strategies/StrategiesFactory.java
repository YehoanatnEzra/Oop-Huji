package brick_strategies;

import brick_strategies.*;
import bricker.gameobjects.Ball;
import bricker.gameobjects.MovingGraphicStrike;
import bricker.gameobjects.PerkPuddle;
import bricker.main.Constant;
import danogl.collisions.GameObjectCollection;
import danogl.util.Counter;

import java.util.Random;

/**
 * Factory class for creating collision strategies based on random selection.
 */
public class StrategiesFactory {
    private Ball userBall;
    private Ball puck1, puck2, puck3, puck4, puck5, puck6;
    private MovingGraphicStrike movingStrike1, movingStrike2, movingStrike3;
    private PerkPuddle perkPuddle;
    private Counter perkPaddleState;
    private Counter cameraSwitch;
    private int randLimit;
    private GameObjectCollection gameObjectCollection;
    private int lotteryNum;

    /**
     * Constructs a new StrategiesFactory object with the specified parameters.
     *
     * @param gameObjectCollection The collection of game objects.
     * @param userBall             The user's ball.
     * @param puck1                The first puck.
     * @param puck2                The second puck.
     * @param puck3                The third puck.
     * @param puck4                The fourth puck.
     * @param puck5                The fifth puck.
     * @param puck6                The sixth puck.
     * @param movingStrike1        The first moving strike.
     * @param movingStrike2        The second moving strike.
     * @param movingStrike3        The third moving strike.
     * @param perkPuddle           The perk puddle.
     * @param perkPaddleState      The state of the perk paddle.
     * @param cameraSwitch         The camera switch counter.
     * @param randLimit            The upper limit for random number generation.
     */
    public StrategiesFactory(GameObjectCollection gameObjectCollection, Ball userBall, Ball puck1,
                             Ball puck2, Ball puck3, Ball puck4, Ball puck5, Ball puck6,
                             MovingGraphicStrike
                                     movingStrike1, MovingGraphicStrike movingStrike2,
                             MovingGraphicStrike movingStrike3,
                             PerkPuddle perkPuddle, Counter perkPaddleState,
                             Counter cameraSwitch, int randLimit) {
        this.gameObjectCollection = gameObjectCollection;
        this.userBall = userBall;
        this.puck1 = puck1;
        this.puck2 = puck2;
        this.puck3 = puck3;
        this.puck4 = puck4;
        this.puck5 = puck5;
        this.puck6 = puck6;
        this.movingStrike1 = movingStrike1;
        this.movingStrike2 = movingStrike2;
        this.movingStrike3 = movingStrike3;
        this.perkPuddle = perkPuddle;
        this.perkPaddleState = perkPaddleState;
        this.cameraSwitch = cameraSwitch;
        this.randLimit = randLimit;
        this.lotteryNum = Constant.REGULAR_LOTTERY;
    }
    /**
     * Gets the lottery number.
     *
     * @return The lottery number associated with this instance.
     */
    public int getLotteryNum() {
        return this.lotteryNum;
    }

    /**
     * Decreases the value of the lottery number by 1.
     * This method reduces the current value of the lottery number by 1.
     * If the lottery number is initially set to a value, calling this method
     * will decrement that value by 1.
     */
    public void lotteryNumDec() {
        this.lotteryNum--;
    }

    /**
     * Sets the new random limit.
     *
     * @param newRandLimit The new random limit.
     */
    public void setRandLimit(int newRandLimit) {
        this.randLimit = newRandLimit;
    }

    /**
     * Builds and returns a collision strategy based on random selection.
     *
     * @return The collision strategy.
     */
    public bricker.brick_strategies.CollisionStrategy buildStrategies() {
        Ball firstPuck, secondPuck;
        MovingGraphicStrike movingStrike;
        switch (this.lotteryNum) {
            case Constant.REGULAR_LOTTERY:
            case Constant.FIRST_LOTTERY:
                firstPuck = this.puck1;
                secondPuck = this.puck2;
                movingStrike = this.movingStrike1;
                break;
            case Constant.SECOND_LOTTERY:
                firstPuck = this.puck3;
                secondPuck = this.puck4;
                movingStrike = this.movingStrike2;
                break;
            case Constant.THIRD_LOTTERY:
                firstPuck = this.puck5;
                secondPuck = this.puck6;
                movingStrike = this.movingStrike3;
                break;
            default:
                return null;
        }
        this.lotteryNum++;
        Random rand = new Random();
        BasicCollisionStrategy basicCollisionStrategy = new
                BasicCollisionStrategy(gameObjectCollection);
        bricker.brick_strategies.CollisionStrategy collisionStrategy;
        int randomNumber = rand.nextInt(this.randLimit);
        switch (randomNumber) {
            case 0:
                collisionStrategy = new NewPucksCollisionStrategy(gameObjectCollection,
                        basicCollisionStrategy,
                        firstPuck, secondPuck);
                break;
            case 1:
                collisionStrategy = new PerkPuddleCollisionStrategy(gameObjectCollection,
                        basicCollisionStrategy,
                        perkPuddle, perkPaddleState);
                break;
            case 2:
                collisionStrategy = new ChangeCameraCollisionStrategy(gameObjectCollection,
                        basicCollisionStrategy,
                        cameraSwitch, userBall);
                break;
            case 3:
                collisionStrategy = new AddStrikeCollisionStrategy(gameObjectCollection,
                        basicCollisionStrategy,
                        movingStrike);
                break;
            case 4:
                collisionStrategy = new DualBehaviorCollisionStrategy(basicCollisionStrategy,
                        this);
                break;
            default:
                collisionStrategy = basicCollisionStrategy;
        }
        return collisionStrategy;
    }
}
