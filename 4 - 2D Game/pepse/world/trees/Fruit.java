package world.trees;

import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Collision;
import danogl.components.ScheduledTask;
import danogl.gui.rendering.OvalRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import util.Constant;
import pepse.util.ColorSupplier;
import world.Avatar;

import java.awt.*;
import java.util.concurrent.Callable;
import java.util.function.BooleanSupplier;
import java.util.function.Function;

/**
 * Represents a fruit GameObject in the game world.
 * This class extends the GameObject class and adds
 * functionality specific to a fruit object.
 */
public class Fruit extends GameObject {
    private  fruitColor curColor;
    private Runnable appleEaten;
    private  BooleanSupplier avatarState;
    private enum fruitColor {FIRST_COLOR, SECOND_FOLOR};
    private boolean jumpStateOn;

    /**
     * Constructs a new Fruit object with the specified position, dimensions,
     * and color.
     *
     * @param topLeftCorner The position of the top-left corner of the fruit,
     *                     in window coordinates (pixels).
     * @param dimensions    The width and height of the fruit,
     *                      in window coordinates.
     * @param color         The initial color of the fruit.
     * @param appleEaten    The action to perform when the fruit is eaten.
     * @param avatarState   Supplier for avatar state.
     */
    public Fruit(Vector2 topLeftCorner, Vector2 dimensions,
                 Color color, Runnable appleEaten,
                 BooleanSupplier avatarState) {
        super(topLeftCorner, dimensions,
                new OvalRenderable(ColorSupplier.approximateColor(color)));
        this.appleEaten = appleEaten;
        this.avatarState = avatarState;
        this.setTag("Fruit");
        this.curColor = fruitColor.FIRST_COLOR;
        this.jumpStateOn = false;

    }

    /**
     * Handles collision events with other game objects.
     * Triggers actions when the fruit collides with the avatar.
     *
     * @param other     The other game object involved in the collision.
     * @param collision The collision details.
     */
    public void onCollisionEnter(GameObject other, Collision collision) {
        super.onCollisionEnter(other, collision);
        if (other.getTag().equals("Avatar")) {
            if (this.renderer().getOpaqueness() > 0) {
                appleEaten.run();
                this.renderer().setOpaqueness(0f);
                new ScheduledTask(this,
                        Constant.CYCLE_LENGTH, false, this::fadeIn);
            }
        }
    }


    /**
     * Updates the state of the fruit based on the avatar's state.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        checkAvatarState();
    }

    /**
     * Checks the state of the avatar and triggers fruit color change accordingly.
     * If the avatar state indicates jumping, changes the color of the fruit.
     */
    private void checkAvatarState(){
        if(this.avatarState.getAsBoolean()){
            if(this.jumpStateOn ==false){
                this.jumpStateOn = true;
                OvalRenderable renderable;
                if(this.curColor == fruitColor.FIRST_COLOR){
                    renderable =
                            new OvalRenderable(ColorSupplier.approximateColor
                            (Constant.SECOND_FRUIT_COLOR));
                    this.curColor = fruitColor.SECOND_FOLOR;
                }
                else {
                    renderable = new
                            OvalRenderable(ColorSupplier.approximateColor
                            (Constant.FRUIT_COLOR));
                    this.curColor = fruitColor.FIRST_COLOR;
                }
                renderer().setRenderable(renderable);
            }
        }
        else {
            this.jumpStateOn = false;
        }
    }

    /**
     * Fades in the visibility of the fruit.
     */
    public void fadeIn() {
        this.renderer().setOpaqueness(1f);
    }
}


