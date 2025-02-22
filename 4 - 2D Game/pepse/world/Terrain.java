package world;

import util.Constant;
import danogl.gui.rendering.RectangleRenderable;
import danogl.util.Vector2;
import java.util.ArrayList;
import java.util.List;
import pepse.util.NoiseGenerator;
import pepse.util.ColorSupplier;

import static java.lang.Math.round;

/**
 * Represents the terrain in the game world.
 * The Terrain class generates and manages the terrain blocks
 * within the game world.
 */
public class Terrain {
    private float groundHeightAtX0;
    private int seed;
    private NoiseGenerator noiseGenerator;

    /**
     * Constructs a Terrain object with the specified window dimensions and seed.
     *
     * @param windowDimensions The dimensions of the game window.
     * @param seed             The seed used for random generation.
     */
    public Terrain(Vector2 windowDimensions, int seed){
        this.groundHeightAtX0 = windowDimensions.y() * Constant.GROUND_HEIGHT_PERCENTAGE;
        this.seed=seed;
        this.noiseGenerator = new NoiseGenerator(this.seed,
                round(this.groundHeightAtX0));
    }

    /**
     * Creates a list of Block objects within the specified range of x-coordinates.
     *
     * @param minX The minimum x-coordinate for creating blocks.
     * @param maxX The maximum x-coordinate for creating blocks.
     * @return A list of Block objects created within the specified range.
     */
    public List<world.Block> createInRange(int minX, int maxX) {
        List<Block> listInRange = new ArrayList<>();
        float terrainBlockX =
                minX - Constant.TERRAIN_BLOCK_MIN_SAFETY_DISTANCE;
        float terrainBlockMaxX =
                maxX + Constant.TERRAIN_BLOCK_MAX_SAFETY_DISTANCE;
        while (terrainBlockX <= terrainBlockMaxX) {
            float terrainBlockY =
                    groundHeightAt(terrainBlockX);
            int depthCount = 0;
            while (depthCount < Constant.TERRAIN_DEPTH) {
                listInRange.add(createGroundBlock(terrainBlockX, terrainBlockY));
                terrainBlockY += Constant.BLOCK_SIZE;
                depthCount++;
            }
            terrainBlockX += Constant.BLOCK_SIZE;
        }
        return listInRange;
    }

    /**
     * Calculates the ground height at the specified x-coordinate.
     *
     * @param x The x-coordinate.
     * @return The ground height at the specified x-coordinate.
     */
    public float groundHeightAt(float x) {
        float noise = this.groundHeightAtX0 +
                (float)(this.noiseGenerator.noise(x,Constant.FACTOR_NOISE));
        return (float)
                (Math.floor (noise / Constant.BLOCK_SIZE) * Constant.BLOCK_SIZE);
    }

    /**
     * Creates a ground block at the specified position.
     *
     * @param topLeftCorner The top-left corner position of the block.
     * @return The created Block object.
     */
    private Block createGroundBlock(float blockGroundX, float blockGourndY){
       RectangleRenderable rectangleRenderable =
               new RectangleRenderable
                       (ColorSupplier.approximateColor(Constant.BASE_GROUND_COLOR));
       Block blockGround =
               new Block(new Vector2(blockGroundX,blockGourndY),rectangleRenderable);
        return blockGround;
    }


}
