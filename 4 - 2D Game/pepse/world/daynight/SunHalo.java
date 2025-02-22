package world.daynight;

import util.Constant;
import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.gui.rendering.OvalRenderable;

import java.awt.*;

/**
 * Represents the halo around the sun during the day.
 * This class is responsible for creating and configuring
 * the sun halo GameObject.
 */
public class SunHalo {
    /**
     * Creates a sun halo GameObject based on the provided sun GameObject.
     *
     * @param sun The sun GameObject around which the halo will be created.
     * @return The sun halo GameObject.
     */
    public static GameObject create(GameObject sun) {
        Color customColor = new Color(255, 255, 0, 20);
        OvalRenderable renderable = new OvalRenderable(customColor);
        GameObject sunHalo = new GameObject(sun.getTopLeftCorner(),
                sun.getDimensions().mult(Constant.HALO_SUN_RATIO), renderable);
        sunHalo.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        sunHalo.setTag("SunHalo");
        // use of Component interface as shown in the exercise instructions.
        sunHalo.addComponent((deltaTime -> sunHalo.setCenter(sun.getCenter())));
        return sunHalo;
    }
}
