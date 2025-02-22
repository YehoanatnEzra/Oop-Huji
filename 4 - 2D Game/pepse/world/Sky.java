package world;

import util.Constant;
import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;

/**
 * The Sky class provides a static method to create a
 * GameObject representing the sky in the game.
 */
 public class Sky {
    /**
     * Creates a sky GameObject with the specified window dimensions.
     * @param windowDimensions The dimensions of the game window.
     * @return The sky GameObject.
     */
    public static GameObject create(Vector2 windowDimensions){
        GameObject sky = new GameObject(
                Vector2.ZERO, windowDimensions,
                new RectangleRenderable(Constant.BASIC_SKY_COLOR));
        sky.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        sky.setTag("sky");
        return sky;
    }
}
