package pepse;

import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.util.Vector2;
import util.Constant;
import world.Block;
import world.Sky;
import world.Terrain;
import world.daynight.Night;
import world.daynight.Sun;
import world.daynight.SunHalo;
import world.Avatar;
import world.trees.Flora;

import java.util.*;

/**
 * The main game manager class for the "Pepse" game.
 * This class manages the initialization and update
 * of game objects and game logic.
 */
public class PepseGameManager extends GameManager {
    private GameObject sky;
    private WindowController windowController;
    private Vector2 windowDimensions;
    private Terrain terrain;
    private Avatar avatar;
    private UserInputListener inputListener;
    private ImageReader imageReader;


    /**
     * The entry point of the game. Creates an instance of
     * PepseGameManager and starts the game.
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        new PepseGameManager().run();
    }

    /**
     * Updates the game state based on the elapsed time.
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    /**
     * Initializes the game with necessary components and objects.
     * @param imageReader The image reader for loading game assets.
     * @param soundReader The sound reader for loading game audio.
     * @param inputListener The input listener for handling user input.
     * @param windowController The window controller for managing the game window.
     */
    @Override
    public void initializeGame(ImageReader imageReader,
                               SoundReader soundReader,
                               UserInputListener
                                    inputListener,
                               WindowController
                                      windowController) {

        super.initializeGame(imageReader,
                soundReader,
                inputListener,
                windowController);
        this.windowController = windowController;
        this.windowDimensions = this.windowController.getWindowDimensions();
        this.inputListener = inputListener;
        this.imageReader = imageReader;
        this.terrain = new Terrain(this.windowController.getWindowDimensions(),
                Constant.NOIESE_GENERATOR_SEED);
        createSky(this.windowDimensions);
        createTerrainBlock(terrain, 0,
                (int) this.windowDimensions.x() + 1);
        createNight(Constant.CYCLE_LENGTH);
        GameObject sun = createSun();
        createSunHalo(sun);
        this.avatar = createAvatar();
        createTrees();

    }

    private void createTrees() {
        Flora flora = new Flora(this.gameObjects(),
                this.windowDimensions,
                this.terrain::groundHeightAt,
                this.avatar::appleEaten,
                this.avatar::isJump);
        List<GameObject> listTrees = flora.createInRange(
                Constant.BLOCK_SIZE + Constant.TREE_FIX_MARGIN,
                (int) windowDimensions.x() - Constant.BLOCK_SIZE);
        for (GameObject obj : listTrees) {
            if(obj.getTag()=="Log"){
                this.gameObjects().addGameObject(obj, Layer.STATIC_OBJECTS);
            }
            else{
                this.gameObjects().addGameObject(obj);
            }
        }
    }

    private Avatar createAvatar() {
        float avatarX =
                this.windowDimensions.x() - Constant.AVATAR_WIDTH;
        float avatarY =
                this.terrain.groundHeightAt(avatarX)
                        - Constant.AVATAR_HEIGHT;
        Avatar avatar = Avatar.create(gameObjects(),
                new Vector2(avatarX, avatarY),
                this.inputListener,
                this.imageReader);
        this.gameObjects().addGameObject(avatar, Layer.DEFAULT);
        return avatar;
    }

    private GameObject createSun() {
        GameObject sun = Sun.create(windowDimensions,
                (float) Constant.CYCLE_LENGTH * 2);
        gameObjects().addGameObject(sun, Layer.BACKGROUND);
        return sun;
    }

    private void createSunHalo(GameObject sun) {
        GameObject sunHalo = SunHalo.create(sun);
        gameObjects().addGameObject(sunHalo, Layer.BACKGROUND);
    }

    private void createNight(float cycleLength) {
        GameObject night = Night.create(windowDimensions, cycleLength);
        gameObjects().addGameObject(night);
    }

    private void createSky(Vector2 windowDimensions) {
        this.sky = Sky.create(windowDimensions);
        gameObjects().addGameObject(sky, Layer.BACKGROUND);
    }

    private void createTerrainBlock(Terrain terrain, int maxX, int minY) {
        List<Block> listBlocks = terrain.createInRange(maxX, minY);
        for (Block block : listBlocks) {
            gameObjects().addGameObject(block, Layer.STATIC_OBJECTS);
        }
    }
}
