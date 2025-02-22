package brick_strategies;

import bricker.brick_strategies.StrategyDecorator;
import bricker.gameobjects.PerkPuddle;
import bricker.main.Constant;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.util.Counter;

/**
 * This class represents a collision strategy for changing the camera state upon
 * collision with a specific object.
 * When the specified triggering object collides with the object that holds this strategy,
 * it triggers a camera switch if the camera
 * switch counter is currently off.
 */
public class ChangeCameraCollisionStrategy implements StrategyDecorator {

    private GameObjectCollection gameObjectCollection;
    private  Counter cameraSwitch;
    private  GameObject triggeringObject;
    private BasicCollisionStrategy basicCollisionStrategy;

    /**
     * Constructs a ChangeCameraCollisionStrategy with the given parameters.
     *
     * @param gameObjectCollection The collection of game objects.
     * @param basicCollisionStrategy The basic collision strategy to use.
     * @param cameraSwitch The counter for camera switches.
     * @param triggeringObject The object that triggers the camera switch upon collision.
     */
    public ChangeCameraCollisionStrategy(GameObjectCollection gameObjectCollection,
                                         BasicCollisionStrategy
            basicCollisionStrategy, Counter cameraSwitch, GameObject triggeringObject) {
        this.gameObjectCollection = gameObjectCollection;
        this.basicCollisionStrategy = basicCollisionStrategy;
        this.cameraSwitch = cameraSwitch;
        this.triggeringObject=triggeringObject;

    }

    /**
     * Handles the collision between two game objects.
     *
     * @param thisObj The first game object involved in the collision.
     * @param otherObj The second game object involved in the collision.
     */
    @Override
    public void onCollision(GameObject thisObj, GameObject otherObj) {
        if(otherObj.equals(triggeringObject) && this.cameraSwitch.value()== Constant.OFF)  {
            this.cameraSwitch.increment(); //switch cameraSwitch to ON
        }
        this.basicCollisionStrategy.onCollision(thisObj, otherObj);
    }
}

