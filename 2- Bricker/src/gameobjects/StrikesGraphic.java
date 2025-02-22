package bricker.gameobjects;

import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.gui.rendering.Renderable;
import danogl.util.Counter;
import danogl.util.Vector2;

/**
 * Responsible for displaying the game's strikes graphically with the supplied Renderable.
 */
public class StrikesGraphic extends GameObject {
    private Counter strikesLeft; // Points to the main Counter object managed by BrickerGameManager
    private int symbolsCount; // Local counter
    Renderable graphicStrikeImage;
    private GameObjectCollection gameObjectCollection;
    private GameObject[] strikeSymbols;
    private Vector2 graphicStrikeDimensions;
    private Vector2 graphicsStrikeDimensions;
    private Vector2 screenDimensions;

    /**
     * Constructs a new StrikesGraphic instance.
     *
     * @param graphicsStrikeDimensions Dimensions of the strikes graphic.
     * @param screenDimensions         Dimensions of the game screen.
     * @param renderableDimensions     Dimensions of the renderable representing the strike symbols.
     * @param renderable               The renderable representing the strike symbols.
     * @param gameObjectCollection     The collection of game objects.
     * @param initialStrikes           The initial number of strikes.
     */
    public StrikesGraphic(Vector2 graphicsStrikeDimensions, Vector2 screenDimensions,
                          Vector2 renderableDimensions,
                          Renderable renderable,
                          GameObjectCollection gameObjectCollection,
                          Counter initialStrikes) {
        super(Vector2.ZERO, Vector2.ZERO, renderable);
        this.strikesLeft = initialStrikes;
        this.graphicStrikeImage = renderable;
        this.symbolsCount = initialStrikes.value();
        this.gameObjectCollection = gameObjectCollection;
        this.strikeSymbols = new GraphicStrike[symbolsCount];
        this.graphicStrikeDimensions = renderableDimensions;
        this.graphicsStrikeDimensions = graphicsStrikeDimensions;
        this.screenDimensions = screenDimensions;
        for (int i = 0; i < symbolsCount; i++) {
            // Calculate x-coordinate
            float locationX = i * graphicsStrikeDimensions.x() / symbolsCount + 20;
            float locationY = screenDimensions.y() - 50; // Calculate y-coordinate
            GraphicStrike graphicStrike = new GraphicStrike(new Vector2(locationX,
                    locationY), renderableDimensions, renderable);
            this.strikeSymbols[i] = graphicStrike;
            gameObjectCollection.addGameObject(graphicStrike, Layer.UI);
        }
    }

    /**
     * Updates the StrikesGraphic instance.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    public void update(float deltaTime) {
        super.update(deltaTime);
        if (strikesLeft.value() < symbolsCount) {
            this.gameObjectCollection.removeGameObject(strikeSymbols[symbolsCount - 1], Layer.UI);
            symbolsCount--;
        }
        if (strikesLeft.value() > symbolsCount) {
            symbolsCount++;
            this.updateDisplay();
        }
    }

    /**
     * Updates the display by removing existing strike symbols and
     * adding new ones based on the current state.
     */
    private void updateDisplay() {
        for (GameObject graphicStrike : strikeSymbols) {
            this.gameObjectCollection.removeGameObject(graphicStrike, Layer.UI);
        }
        this.strikeSymbols = new GraphicStrike[symbolsCount];
        this.graphicsStrikeDimensions = new Vector2(graphicStrikeDimensions.x() * symbolsCount,
                this.graphicStrikeDimensions.y());
        for (int i = 0; i < symbolsCount; i++) {
            float locationX = i * graphicsStrikeDimensions.x() / symbolsCount + 20;  // Calculate x-coordinate
            float locationY = screenDimensions.y() - 50; // Calculate y-coordinate
            GraphicStrike graphicStrike = new GraphicStrike(new Vector2(locationX, locationY),
                    graphicStrikeDimensions, this.graphicStrikeImage);
            this.strikeSymbols[i] = graphicStrike;
            gameObjectCollection.addGameObject(graphicStrike, Layer.UI);
        }
    }
}
