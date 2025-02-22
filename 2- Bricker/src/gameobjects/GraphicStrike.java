package bricker.gameobjects;

import danogl.GameObject;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
/**
 * Represents a graphic icon to display a strike.
 */
public class GraphicStrike extends GameObject {
    /**
     * Constructs a new GraphicStrike instance.
     *
     * @param topLeftCorner Position of the graphic icon, in window coordinates (pixels).
     *                      Note that (0,0) is the top-left corner of the window.
     * @param dimensions    Width and height of the graphic icon in window coordinates.
     * @param renderable    The renderable representing the graphic icon. Can be null, in which case
     *                      the graphic icon will not be rendered.
     */
    public GraphicStrike(Vector2 topLeftCorner, Vector2 dimensions, Renderable renderable) {
        super(topLeftCorner, dimensions, renderable);
    }
}
