package display;

import danogl.GameObject;
import danogl.gui.rendering.TextRenderable;
import danogl.util.Vector2;

import display.DisplayCallback;
import java.util.function.Supplier;

/**
 * A class that handles a visual UI bar
 */
public class Bar extends GameObject {
    Supplier<Float> energySupplier;
    Vector2 topLeftCorner;
    TextRenderable textRenderable;

    /**
     * Constructs a new Bar object with the specified energy supplier
     * , position, dimensions, and text renderable.
     *
     * @param energySupplier  The supplier for the energy value to
     *                       be displayed on the bar.
     * @param topLeftCorner   The position of the top-left corner of the bar.
     * @param dimensions      The dimensions of the bar.
     * @param textRenderable  The text renderable for displaying
     * information on the bar.
     */
    public Bar(Supplier<Float> energySupplier,
               Vector2 topLeftCorner,
               Vector2 dimensions,
               TextRenderable textRenderable) {
        super(topLeftCorner, dimensions, textRenderable);
        this.energySupplier = energySupplier;
        this.textRenderable = textRenderable;
        this.topLeftCorner = topLeftCorner;
    }

    /**
     * Updates the UI bar with the current energy value.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        int energy = (int) Math.floor(this.energySupplier.get());
        this.textRenderable.setString("Energy "+energy+"%");
    }
}