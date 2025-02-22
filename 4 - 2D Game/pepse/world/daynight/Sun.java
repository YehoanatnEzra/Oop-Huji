package world.daynight;

import util.Constant;
import danogl.GameObject;
import danogl.components.CoordinateSpace;
import danogl.components.Transition;
import danogl.gui.rendering.OvalRenderable;
import danogl.util.Vector2;

import java.awt.*;

/**
 * Represents the sun in the game world.
 * This class is responsible for creating
 * and configuring the sun GameObject.
 */
public class Sun {
    /**
     * Creates a sun GameObject with the specified window dimensions
     * cycle length.
     *
     * @param windowDimensions The dimensions of the game window.
     * @param cycleLength      The length of the sun's cycle.
     * @return The sun GameObject.
     */
    public static GameObject create(Vector2 windowDimensions, float cycleLength) {
        OvalRenderable renderable = new OvalRenderable(Color.YELLOW);
        float screenMidX = (windowDimensions.x() / 2 - Constant.RADIUS_OF_SUN/2);
        float screenMidY = (windowDimensions.y() / 3 - Constant.RADIUS_OF_SUN/2);
        Vector2 initialSunCenter = new Vector2(screenMidX, screenMidY);
        Vector2 cycleCenter = new Vector2(screenMidX, windowDimensions.y());
        GameObject sun = new GameObject(initialSunCenter,
                Vector2.ONES.mult(Constant.RADIUS_OF_SUN),
                renderable);
        sun.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        sun.setTag("Sun");
        Transition<Float> transition = new Transition<Float>(sun,
                (Float angle)->
                        sun.setCenter(initialSunCenter.subtract(cycleCenter)
                                .rotated(angle).add(cycleCenter)),
                0.0f,
                360f,
                Transition.LINEAR_INTERPOLATOR_FLOAT,
                cycleLength,
                Transition.TransitionType.TRANSITION_LOOP,
                null);
        return sun;
    }
}
