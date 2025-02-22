package world.daynight;

import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.components.Transition;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import util.Constant;

import java.awt.*;

/**
 * Represents the night environment in the game world.
 * This class provides a static method to create a night GameObject.
 */
public class Night {
    /**
     * Creates a GameObject representing the night environment.
     *
     * @param windowDimensions The dimensions of the game window.
     * @param cycleLength      The length of the day-night cycle.
     * @return A GameObject representing the night environment.
     */
    public static GameObject create(Vector2 windowDimensions, float cycleLength) {
        RectangleRenderable renderable =
                new RectangleRenderable(
                        pepse.util.ColorSupplier.approximateColor(Color.BLACK));
        GameObject night = new GameObject(Vector2.ZERO, windowDimensions, renderable);
        night.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        night.setTag("Night");
        Transition<Float> transition =
                new Transition<Float>(night,
                        night.renderer()::setOpaqueness,
                0.0f,
                        Constant.MIDNIGHT_OPACITY,
                        Transition.CUBIC_INTERPOLATOR_FLOAT,
                        cycleLength,
                Transition.TransitionType.TRANSITION_BACK_AND_FORTH,
                        null);
        return night;
    }
}
