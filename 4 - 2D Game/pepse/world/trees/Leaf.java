package world.trees;

import danogl.GameObject;
import danogl.components.ScheduledTask;
import danogl.components.Transition;
import danogl.gui.rendering.RectangleRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import util.Constant;
import world.Block;

import java.util.function.BooleanSupplier;

/**
 * Represents a leaf GameObject in the game world.
 * This class extends the GameObject class and adds functionality specific to a leaf object.
 */
public class Leaf extends GameObject {
    private static float delay = 0;
    private  BooleanSupplier avatarState;
    private boolean avatarJumpState;

    /**
     * Constructs a new Leaf object with the specified position.
     *
     * @param topLeftCorner The position of the top-left corner of the leaf,
     *                     in window coordinates (pixels).
     * @param avatarState   Supplier for avatar state
     */
    public Leaf(Vector2 topLeftCorner,  BooleanSupplier avatarState) {
        super(topLeftCorner, Constant.LEAF_AND_FRUIT_SIZE,
                new RectangleRenderable(Constant.LEAVES_COLOR));
        this.avatarState = avatarState;
        this.avatarJumpState = false;
        this.setTag("Leaf");
        new ScheduledTask(this,
                delay, false, this::movingLeaf);
        delay += Constant.DELAY_INTERVA;
    }

    /**
     * Initiates transitions for the leaf's movement and appearance.
     * This method is responsible for animating the leaf's behavior, including rotation and size changes.
     * It sets up transitions for rotating the leaf back and forth by 360 degrees and for reducing its size.
     * The transitions create an animated effect for the leaf's movement and appearance.
     */
    private void movingLeaf() {
        new Transition<Float>(
                this,
                (Float angle) -> this.renderer().setRenderableAngle(angle),
                Constant.INITIAL_ANGLE_MOVING_LEAF,
                Constant.FINAL_ANGLE_MOVING_LEAF,
                Transition.LINEAR_INTERPOLATOR_FLOAT,
                Constant.TRANSITION_MOVING_LEAF_TIME,
                Transition.TransitionType.TRANSITION_BACK_AND_FORTH,
                null);
    }

    /**
     * Updates the state of the leaf based on the avatar's state.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        checkAvatarState();
    }

    /**
     * Checks the state of the avatar and triggers leaf animations accordingly.
     * If the avatar state indicates jumping, triggers a leaf rotation animation.
     */
    private  void checkAvatarState(){
        if(this.avatarState.getAsBoolean()){
            if(!this.avatarJumpState){
                this.avatarJumpState=true;
                new Transition<Float>(
                        this,
                        (Float angle) -> this.renderer().setRenderableAngle(angle),
                        0f,
                        90f,
                        Transition.LINEAR_INTERPOLATOR_FLOAT,
                        Constant.TRANSITION_MOVING_LEAF_TIME,
                        Transition.TransitionType.TRANSITION_ONCE,
                        null);
            }
        }
        else {
            this.avatarJumpState = false;
        }
    }
}



