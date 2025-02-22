/**
 * The VoidRenderer class implements the Renderer interface and
 * represents a renderer that does not display the game board.
 * It serves as a placeholder for scenarios where no rendering is needed.
 */
class VoidRenderer implements  Renderer{

    /**
     * Constructs a VoidRenderer object with a default constructor.
     */
    public VoidRenderer (){}

    /**
     * Does not render the game board since this is a void renderer.
     *
     * @param board The game board to be rendered.
     */
    public void renderBoard (Board board) {}
}