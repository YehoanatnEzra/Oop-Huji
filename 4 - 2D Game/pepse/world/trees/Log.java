package world.trees;

import danogl.GameObject;
import danogl.components.GameObjectPhysics;
import danogl.gui.rendering.OvalRenderable;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import util.Constant;
import world.Block;

import java.util.function.BooleanSupplier;

/**
 * The Log class represents a type of GameObject.
 * Logs can change color based on the state of the avatar.
 */
public class Log extends GameObject {
    private BooleanSupplier avatarState;
    private boolean jumpStateOn;


    /**
     * Constructs a Log object at the specified position with the given
     * tree height and an avatar state supplier.
     *
     * @param topLeftCorner The position of the top-left corner of the log.
     * @param treeHigh      The height of the log, represented in tree blocks.
     * @param avatarState   A BooleanSupplier supplying the state of the avatar.
     */
    public Log(Vector2 topLeftCorner ,int treeHigh ,BooleanSupplier avatarState){
        super(topLeftCorner,
                new Vector2(Constant.BLOCK_SIZE, (float)Constant.BLOCK_SIZE*treeHigh),
                new RectangleRenderable(Constant.LOG_COLOR));
        physics().preventIntersectionsFromDirection(Vector2.ZERO);
        physics().setMass(GameObjectPhysics.IMMOVABLE_MASS);
        this.avatarState = avatarState;
        this.jumpStateOn =  false;
        setTag("Log");
    }

    /**
     * Updates the log based on the avatar state.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        checkAvatarState();
    }

    /**
     * Checks the state of the avatar and changes the log color accordingly.
     */
    private void checkAvatarState(){
        if(this.avatarState.getAsBoolean()){
            if(!this.jumpStateOn ){
                this.jumpStateOn = true;
                RectangleRenderable renderable = new RectangleRenderable
                    (pepse.util.ColorSupplier.approximateColor(Constant.LOG_COLOR));
                renderer().setRenderable(renderable);
            }
        }
        else {
            this.jumpStateOn = false;
        }
    }
}

