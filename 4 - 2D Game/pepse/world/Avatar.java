package world;

import danogl.*;
import danogl.collisions.Collision;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.components.*;
import danogl.gui.*;
import danogl.gui.rendering.*;
import danogl.util.Counter;
import danogl.util.Vector2;
import display.Bar;
import util.Constant;

import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.Math.abs;

/**
 * The Avatar class represents the player's character in the game.
 * It manages the avatar's movement, energy level, animations, and interactions.
 */
public class Avatar extends GameObject {

    private int avatarState; // There are 4 state - IDLE RUN JUMP JUMP_AND_RUN
    private float energy;
    private UserInputListener inputListener;
    private AnimationRenderable idleAnimation;
    private AnimationRenderable runAnimation;
    private AnimationRenderable jumpAnimation;

    /**
     * Represents the player's avatar in the game world.
     * The avatar class extends the GameObject class and adds functionality
     * specific to the player's character.
     */
    public Avatar(Vector2 pos,
                  UserInputListener inputListener, ImageReader imageReader) {
        super(pos,
                Vector2.ONES.mult(50),
                imageReader.readImage(Constant.IDLE_0, false));
        physics().preventIntersectionsFromDirection(Vector2.ZERO);
        this.avatarState = Constant.STATE_IDLE;
        this.inputListener = inputListener;
        this.energy = Constant.ENERGY_FULL;
        animations(imageReader);
        this.setTag("Avatar");
    }


    /**
     * Creates a new instance of the Avatar class along with an energy
     * display bar and adds them to the provided GameObjectCollection.
     *
     * @param gameObjects The collection of game objects to which
     * the avatar and energy display bar will be added.
     * @param avatarPos The initial position of the avatar.
     * @param inputListener The user input listener for controlling the avatar.
     * @param imageReader The image reader for loading avatar images.
     * @return The newly created Avatar object.
     */
    public static Avatar create(GameObjectCollection gameObjects,
                                Vector2 avatarPos,
                                UserInputListener inputListener,
                                ImageReader imageReader) {
        Avatar avatar = new Avatar(avatarPos, inputListener, imageReader);
        TextRenderable renderable =
                new TextRenderable("Energy "+ avatar.getEnergy() + "%");
        renderable.setColor(Color.BLACK);
        Vector2 sizeOfBar = Vector2.ONES.mult(Constant.ENERGY_FONT_SIZE);
        GameObject bar = new Bar(avatar::getEnergy, Vector2.ZERO, sizeOfBar, renderable);
        gameObjects.addGameObject(bar, Layer.UI);
        return avatar;
    }

    /**
     * Updates the avatar's state, energy level, and animations based on user input and game logic.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    public void update(float deltaTime) {
        super.update(deltaTime);
        avatarEnergyHandler();
        userInputHandler();
        animationsHandler();
    }

    /**
     * Retrieves the current energy level of the avatar.
     *
     * @return The energy level of the avatar.
     */
    public float getEnergy() {
        return this.energy;
    }

    /**
     * Handles the action of the avatar eating an apple,
     * increasing its energy level.
     * If the current energy level is less than the maximum energy,
     * it increases it by the apple energy perk.
     * If the energy level exceeds the maximum energy after eating,
     * it sets the energy level to the maximum.
     */
    public void appleEaten(){
        if(this.energy < Constant.ENERGY_FULL){
            if(this.energy > Constant.ENERGY_FULL - Constant.APPLE_ENERGY_PERK){
                this.energy = Constant.ENERGY_FULL;
            }else{
                this.energy += Constant.APPLE_ENERGY_PERK;
            }
        }
    }

    /**
     * Checks if the avatar is currently in a jumping state.
     *
     * @return True if the avatar is jumping, otherwise false.
     */
    public boolean isJump(){
        if(this.avatarState==Constant.STATE_JUMP ||  this.avatarState==Constant.STATE_JUMP_AND_RUN){
            return true;
        }
        return false;
    }

    /**
     * Initializes all animations and sets the fields.
     *
     * @param imageReader
     */
    private void animations(ImageReader imageReader) {
        idleAnimation(imageReader);
        jumpAnimation(imageReader);
        runAnimation(imageReader);
    }

    private void idleAnimation(ImageReader imageReader) {
        Renderable[] idleAnimationRenderable = {
                imageReader.readImage(Constant.IDLE_0, true),
                imageReader.readImage(Constant.IDLE_1, true),
                imageReader.readImage(Constant.IDLE_2, true),
                imageReader.readImage(Constant.IDLE_3, true)
        };
        this.idleAnimation = new AnimationRenderable(idleAnimationRenderable,
                Constant.ANIMATION_FRAME_SPEED);
    }

    private void runAnimation(ImageReader imageReader) {
        Renderable[] runAnimationRenderable = {
                imageReader.readImage(Constant.RUN_0, true),
                imageReader.readImage(Constant.RUN_1, true),
                imageReader.readImage(Constant.RUN_2, true),
                imageReader.readImage(Constant.RUN_3, true),
                imageReader.readImage(Constant.RUN_4, true),
                imageReader.readImage(Constant.RUN_5, true)
        };

        this.runAnimation = new AnimationRenderable(runAnimationRenderable, Constant.ANIMATION_FRAME_SPEED);
    }

    private void jumpAnimation(ImageReader imageReader) {
        Renderable[] jumpAnimationRenderable = {
                imageReader.readImage(Constant.JUMP_0, true),
                imageReader.readImage(Constant.JUMP_1, true),
                imageReader.readImage(Constant.JUMP_2, true),
                imageReader.readImage(Constant.JUMP_3, true)
        };
        this.jumpAnimation = new AnimationRenderable(jumpAnimationRenderable,
                Constant.ANIMATION_FRAME_SPEED);
    }
    /**
     * Updates the avatar speed(x/y) based on user's input
     * Updates avatar's state as well
     */
    private void userInputHandler() {
        float xVel = 0;
        float yVel = Constant.GRAVITY;
        if (this.energy > abs(Constant.STATE_RUN_DECREASE)) {
            if (inputListener.isKeyPressed(KeyEvent.VK_LEFT)) {
                xVel -= Constant.MOVEMENT_SPEED;
            }
            if (inputListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
                xVel += Constant.MOVEMENT_SPEED;
            }
        }
        transform().setVelocityX(xVel);
        if (inputListener.isKeyPressed(KeyEvent.VK_SPACE) &&
                this.energy > abs(Constant.STATE_JUMP_DECREASE)
        && this.getVelocity().y() <= 0) {
            yVel = (float) Constant.JUMP_SPEED;
        } else if (this.getVelocity().y() == Constant.JUMP_SPEED) {
            yVel = (float) Constant.GRAVITY;
        }
        transform().setVelocityY(yVel);
        if (yVel < 0) { // jumping
            if (xVel != 0) {
                this.avatarState = Constant.STATE_JUMP_AND_RUN;
            } else {
                this.avatarState = Constant.STATE_JUMP;
            }
        } else {
            if (xVel != 0) { // walking
                this.avatarState = Constant.STATE_RUN;
            } else { // resting
                this.avatarState = Constant.STATE_IDLE;
            }
        }
    }

    private void avatarEnergyHandler() {
        if (this.avatarState == Constant.STATE_IDLE) {
            if (this.energy < Constant.ENERGY_FULL) {
                this.energy += Constant.STATE_IDLE_INCREASE;
            }
        } else if (this.avatarState == Constant.STATE_RUN) {
            this.energy += Constant.STATE_RUN_DECREASE;
        } else if (this.avatarState == Constant.STATE_JUMP) {
            this.energy += Constant.STATE_JUMP_DECREASE;
        } else {
            this.energy += Constant.STATE_RUN_DECREASE + Constant.STATE_JUMP_DECREASE;
        }
    }

    private void animationsHandler() {
        if (this.avatarState == Constant.STATE_JUMP) {
            this.renderer().setRenderable(this.jumpAnimation);
            this.renderer().setIsFlippedHorizontally(this.getVelocity().x() < 0);
        } else if (this.avatarState == Constant.STATE_RUN) {
            this.renderer().setRenderable(this.runAnimation);
            this.renderer().setIsFlippedHorizontally(this.getVelocity().x() < 0);
        } else {
            this.renderer().setRenderable(this.idleAnimation);
        }
    }



}