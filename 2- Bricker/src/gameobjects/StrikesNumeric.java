package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.rendering.Renderable;
import danogl.gui.rendering.TextRenderable;
import danogl.util.Counter;
import danogl.util.Vector2;

import java.awt.*;

/**
 * Responsible to display game's strikes left in form of some hearts and a number
 * matching the strikesLeft field
 */
public class StrikesNumeric extends GameObject {
    private static final int GREEN_STRIKES = 3;
    private static final int YELLOW_STRIKES = 2;
    private static final int RED_STRIKES = 1;
    private Counter strikesLeft; // Points to the main Counter object managed by BrickerGameManager
    private int numberDisplayed; // Local counter
    private GameObject displayedObject;
    private final Vector2 displayLocation;
    private Vector2 displayDimensions;
    private GameObjectCollection gameObjectCollection;

    /**
     * Constructs a new StrikesNumeric instance.
     *
     * @param topLeftCorner        Position of the object, in window coordinates (pixels).
     *                             Note that (0,0) is the top-left corner of the window.
     * @param dimensions           Width and height in window coordinates.
     * @param textRenderable       The renderable representing the object. Can be null, in which case
     *                             the GameObject will not be rendered.
     * @param gameObjectCollection The collection of game objects.
     * @param initialStrikes       The initial number of strikes.
     */
    public StrikesNumeric(Vector2 topLeftCorner, Vector2 dimensions, TextRenderable textRenderable,
                   GameObjectCollection gameObjectCollection, Counter initialStrikes ) {
        super(topLeftCorner, dimensions, null);
        // init
        this.strikesLeft = initialStrikes;
        this.numberDisplayed = initialStrikes.value();
        this.gameObjectCollection = gameObjectCollection;
        this.displayDimensions = dimensions;
        this.displayLocation = topLeftCorner;
        // Display object handle
        if(this.numberDisplayed >= GREEN_STRIKES) {
            textRenderable.setColor(Color.GREEN);
        }
        else if(numberDisplayed == YELLOW_STRIKES){
            textRenderable.setColor(Color.YELLOW);
        }
        else if (numberDisplayed == RED_STRIKES){
            textRenderable.setColor(Color.RED);
        }
        this.displayedObject = new GameObject(topLeftCorner, dimensions, textRenderable);
        gameObjectCollection.addGameObject(displayedObject, Layer.UI);
    }
    /**
     * Updates the StrikesNumeric instance.
     *
     * This method should be called once per frame.
     *
     * @param deltaTime The time elapsed since the last frame, in seconds.
     */
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (strikesLeft.value() != numberDisplayed) {
            if(strikesLeft.value() > numberDisplayed){
                numberDisplayed++;
            }else{
                numberDisplayed--;
            }
            this.gameObjectCollection.removeGameObject(this.displayedObject, Layer.UI);
            TextRenderable displayNumber = new TextRenderable(String.format("" +
                    "Strikes left: %d", strikesLeft.value()));
            //TODO:: DUPLICATION
            if(numberDisplayed >= GREEN_STRIKES) {
                displayNumber.setColor(Color.GREEN);
            }
            else if(numberDisplayed == YELLOW_STRIKES){
                displayNumber.setColor(Color.YELLOW);
            }
            else if (numberDisplayed == RED_STRIKES){
                displayNumber.setColor(Color.RED);
            }
            displayedObject = new GameObject(displayLocation, displayDimensions ,displayNumber);
            gameObjectCollection.addGameObject(displayedObject, Layer.UI);

        }
    }
}
