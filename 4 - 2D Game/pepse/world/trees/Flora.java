package world.trees;
import danogl.GameObject;
import danogl.collisions.GameObjectCollection;
import danogl.collisions.Layer;
import danogl.util.Vector2;
import util.Constant;
import world.Block;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import static java.lang.Math.round;

/**
 * Represents the flora in the game world.
 * This class generates trees, logs, leaves,
 * and fruits within a specified range.
 */
public class Flora {
    private final GameObjectCollection gameObjects;
    private final Vector2 windowDimensions;
    private pepse.util.NoiseGenerator noiseGenerator;
    private Function<Float, Float> terrainHeightCallback;
    private Runnable appleEaten;
    private BooleanSupplier avatarState;

    /**
     * Constructs a new Flora instance.
     *
     * @param gameObjects           Collection of game objects.
     * @param windowDimensions      Dimensions of the game window.
     * @param terrainHeightCallback Callback for terrain height calculation.
     * @param appleEatenCall
     * back    Callback for apple eaten action.
     * @param avatarStateCallBack   Supplier for avatar state.
     */
    public Flora(GameObjectCollection gameObjects,
                 Vector2 windowDimensions,
                 Function<Float, Float> terrainHeightCallback,
                 Runnable appleEatenCallback,
                 BooleanSupplier avatarStateCallBack) {
        this.gameObjects = gameObjects;
        this.windowDimensions = windowDimensions;
        this.terrainHeightCallback = terrainHeightCallback;
        this.appleEaten = appleEatenCallback;
        this.avatarState = avatarStateCallBack;
        this.noiseGenerator = new pepse.util.NoiseGenerator(
                Constant.NOIESE_GENERATOR_SEED,
                round(terrainHeightCallback.apply(0f)));
    }
    /**
     * Generates trees within the specified range.
     *
     * @param minX The minimum X coordinate.
     * @param maxX The maximum X coordinate.
     * @return A list of generated game objects.
     */
    public List<GameObject> createInRange(int minX, int maxX) {
        List<GameObject> trees = new ArrayList<>();
        Random random = new Random(Objects.hash(minX, maxX));
        for (int locationX = minX; locationX < maxX;
             locationX += Constant.BLOCK_SIZE) {
            if (random.nextInt() < 0) {
                this.createTree(trees, locationX);
                locationX += Constant.TREE_SPACE_FACTOR;
            }
        }
        return trees;
    }


    private void createTree(List<GameObject> trees, float locationX) {
        Random random = new Random();
        int treeHeight = random.nextInt(5) + Constant.MIN_TREE_HEIGHT;
        float locationY = this.terrainHeightCallback.apply(locationX);
        this.createLog(trees, locationX, locationY, treeHeight);
        this.createLeavesAndFruits(trees, locationX, locationY, treeHeight);
    }

    private void createLeavesAndFruits(List<GameObject> createdObjects,
                                       float locationLogX, float locationGroundY,
                                       int treeHeight) {
        int minLeavesAreaX =
                (int) (locationLogX -
                        (Constant.LEAF_AREA * Constant.LEAF_AND_FRUIT_SIZE.x()));
        int maxLeavesAreaX = (int) (locationLogX +
                (Constant.LEAF_AREA * Constant.LEAF_AND_FRUIT_SIZE.x() ));
        int topTrunkHigh = (int)(locationGroundY -
                (treeHeight * Constant.BLOCK_SIZE));
        int minLeavesAreaY =(int)(topTrunkHigh +
                (Constant.LEAF_AREA * Constant.LEAF_AND_FRUIT_SIZE.y()));
        int maxLeavesAreaY =(int)(topTrunkHigh -
                (Constant.LEAF_AREA * Constant.LEAF_AND_FRUIT_SIZE.y()));
        Random random = new Random();
        for(int idxX = minLeavesAreaX; idxX < maxLeavesAreaX; idxX +=
                Constant.LEAF_AND_FRUIT_SIZE.x() + 2) {
            for (int idxY = maxLeavesAreaY; idxY < minLeavesAreaY; idxY +=
                    Constant.LEAF_AND_FRUIT_SIZE.y() + 2) {
                if ((random.nextInt(100) < 45) &&(idxX>0) &&
                        (idxY>0) && (idxY<locationGroundY)) {
                    Leaf leaf = new Leaf(new Vector2(idxX, idxY), this.avatarState);
                    createdObjects.add(leaf);
                }
                if(random.nextInt(100)<7) {
                    Fruit fruit = new Fruit(new Vector2(idxX,idxY),
                            Constant.LEAF_AND_FRUIT_SIZE,
                            Constant.FRUIT_COLOR,
                            this.appleEaten,
                            this.avatarState);
                    createdObjects.add(fruit);
                }
            }
        }
    }

    private void createLog(List<GameObject> createdObjects,
                           float locationX, float locationY, int treeHeight) {

            GameObject log = new Log(new Vector2(locationX,
                    locationY -( Constant.BLOCK_SIZE*treeHeight)),
                    treeHeight,
                    this.avatarState);
            createdObjects.add(log);
    }
}
