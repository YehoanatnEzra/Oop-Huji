package world;

import util.Constant;
import danogl.GameObject;
import danogl.components.GameObjectPhysics;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;

/**
 * Represents a block in the game world.
 */
public class Block extends GameObject {
    /**
     * Constructs a Block object with the specified top-left corner
     * position and renderable.
     *
     * @param topLeftCorner The top-left corner position of the block.
     * @param renderable    The renderable component for the block.
     */
    public Block(Vector2 topLeftCorner, Renderable renderable) {
        super(topLeftCorner, Vector2.ONES.mult(Constant.BLOCK_SIZE), renderable);
        physics().preventIntersectionsFromDirection(Vector2.ZERO);
        physics().setMass(GameObjectPhysics.IMMOVABLE_MASS);
        this.setTag("block");
    }
}
