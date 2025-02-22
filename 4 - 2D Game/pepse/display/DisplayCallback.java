package display;
import danogl.GameObject;

/**
 * Functional interface for a display callback.
 * This interface defines a method for updating a game object
 * in the display.
 */
@FunctionalInterface
public interface DisplayCallback {
    /**
     * Updates the specified game object in the display.
     *
     * @param gameObject The game object to be updated.
     */
    void update(GameObject gameObject);
}
