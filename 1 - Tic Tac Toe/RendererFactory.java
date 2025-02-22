/**
 * The RendererFactory class is responsible for creating instances of different types of renderers
 * based on the provided type and board size. It acts as a factory for creating renderer objects.
 */
class RendererFactory {

    /**
     * Constructs a RendererFactory object with a default constructor.
     */
    public RendererFactory() {
    }

    /**
     * Builds and returns a renderer object based on the provided type and board size.
     *
     * @param type The type of renderer to be created ("console" or "none").
     * @param size The size of the game board.
     * @return A renderer object of the specified type, or null if the type is not recognized.
     */
    public Renderer buildRenderer(String type, int size) {

        String lowCaseType = type.toLowerCase();
        Renderer newRenderer = switch (lowCaseType) {
            case "console" -> new ConsoleRenderer(size);
            case "none" -> new VoidRenderer();
            default -> null;
        };
        return newRenderer;
    }
}