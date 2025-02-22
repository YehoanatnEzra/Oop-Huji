package util;

import danogl.util.Vector2;

import java.awt.*;
import java.util.Random;

/**
 * The Constant class contains various constants used in the application.
 */
public class Constant {

    /**
     * The basic sky color constant.
     * This color represents the default color of the sky in the application.
     */
    public static final Color BASIC_SKY_COLOR = Color.decode("#80C6E5");

    /**
     * The ground height percentage constant.
     * This constant represents the percentage of the screen height
     * that the ground occupies.
     * The value is expressed as a float between 0 and 1,
     * where 0 represents no ground and 1 represents the entire screen height.
     */
    public static final float GROUND_HEIGHT_PERCENTAGE = 2 / 3f;

    /**
     * The base color representing the ground in the environment.
     * This color is defined as an RGB color with values (212, 123, 74).
     */
    public static final Color BASE_GROUND_COLOR = new Color(212, 123, 74);
    /**
     * Represents the size of an object or element.
     * This constant is used to define the size of Block object
     */
    public static final int BLOCK_SIZE = 30;

    /**
     * Minimum safety distance for the ground block object.
     */
    public static final int TERRAIN_BLOCK_MIN_SAFETY_DISTANCE = 25;

    /**
     * Maximum safety distance for the ground block object.
     */
    public static final int TERRAIN_BLOCK_MAX_SAFETY_DISTANCE = 10;

    /**
     * The depth of the terrain, representing the number of terrain blocks along the z-axis.
     */
    public static final int TERRAIN_DEPTH = 20;
    /**
     * Represents the opacity value used for rendering objects at midnight.
     * The value is set to 0.5f.
     */
    public static final Float MIDNIGHT_OPACITY = 0.5f;

    /**
     * Represents the length of a cycle, typically used in time-based calculations.
     * The value is set to 12.
     */
    public static final int CYCLE_LENGTH = 30;

    /**
     * Represents the delay interval for certain operations, measured in seconds.
     * The value is set to 0.1 seconds.
     */
    public static final float DELAY_INTERVA = 0.1f;

    /**
     * Represents the duration of the transition for moving leaves, measured in seconds.
     * The value is set to 1 second.
     */
    public static final float TRANSITION_MOVING_LEAF_TIME = 1;

    /**
     * The seed value for the noise generator used in terrain generation.
     * The value is set to 1234.
     */
    public static int NOIESE_GENERATOR_SEED = 1234;

    /**
     * Represents the factor used in noise generation calculations.
     * The value is calculated as BLOCK_SIZE multiplied by 7.
     */
    public static float FACTOR_NOISE = BLOCK_SIZE * 7;

    /**
     * Represents the spacing factor for trees, measured in pixels.
     * The value is calculated as BLOCK_SIZE multiplied by 7.
     */
    public static float TREE_SPACE_FACTOR = BLOCK_SIZE * 7;

    /**
     * Represents the margin used to fix trees' positions, measured in pixels.
     * The value is set to 5 pixels.
     */
    public static int TREE_FIX_MARGIN = 5;

    /**
     * Represents the radius of the sun, measured in pixels.
     * The value is set to 100 pixels.
     */
    public static final float RADIUS_OF_SUN = 100;

    /**
     * Represents the ratio of the sun's halo compared to its radius.
     * The value is set to 1.8.
     */
    public static final float HALO_SUN_RATIO = 1.8f;

    /**
     * Represents the width of the avatar, measured in pixels.
     * The value is set to 50 pixels.
     */
    public static final float AVATAR_WIDTH = 50;

    /**
     * Represents the height of the avatar, measured in pixels.
     * The value is set to 78 pixels.
     */
    public static final float AVATAR_HEIGHT = 78;


    /**
     * Filename for the first frame of the idle animation.
     */
    public static final String IDLE_0 = "assets/idle_0.png";

    /**
     * Filename for the second frame of the idle animation.
     */
    public static final String IDLE_1 = "assets/idle_1.png";

    /**
     * Filename for the third frame of the idle animation.
     */
    public static final String IDLE_2 = "assets/idle_2.png";

    /**
     * Filename for the fourth frame of the idle animation.
     */
    public static final String IDLE_3 = "assets/idle_3.png";

    /**
     * Filename for the first frame of the run animation.
     */
    public static final String RUN_0 = "assets/run_0.png";

    /**
     * Filename for the second frame of the run animation.
     */
    public static final String RUN_1 = "assets/run_1.png";

    /**
     * Filename for the third frame of the run animation.
     */
    public static final String RUN_2 = "assets/run_2.png";

    /**
     * Filename for the fourth frame of the run animation.
     */
    public static final String RUN_3 = "assets/run_3.png";

    /**
     * Filename for the fifth frame of the run animation.
     */
    public static final String RUN_4 = "assets/run_4.png";

    /**
     * Filename for the sixth frame of the run animation.
     */
    public static final String RUN_5 = "assets/run_5.png";

    /**
     * Filename for the first frame of the jump animation.
     */
    public static final String JUMP_0 = "assets/jump_0.png";

    /**
     * Filename for the second frame of the jump animation.
     */
    public static final String JUMP_1 = "assets/jump_1.png";

    /**
     * Filename for the third frame of the jump animation.
     */
    public static final String JUMP_2 = "assets/jump_2.png";

    /**
     * Filename for the fourth frame of the jump animation.
     */
    public static final String JUMP_3 = "assets/jump_3.png";
    /**
     * Represents the speed of avatar movement.
     */
    public static final float MOVEMENT_SPEED = 400;

    /**
     * Represents the speed of avatar jumping.
     */
    public static final float JUMP_SPEED = -850;

    /**
     * Represents the gravitational force affecting the avatar.
     */
    public static final float GRAVITY = 300;

    /**
     * Represents the state when the avatar is idle.
     */
    public static final int STATE_IDLE = 0;

    /**
     * Represents the state when the avatar is running.
     */
    public static final int STATE_RUN = 1;

    /**
     * Represents the state when the avatar is jumping.
     */
    public static final int STATE_JUMP = 2;

    /**
     * Represents the state when the avatar is
     * jumping and running simultaneously.
     */
    public static final int STATE_JUMP_AND_RUN = 3;

    /**
     * Represents the full energy level of the avatar.
     */
    public static final int ENERGY_FULL = 100;

    /**
     * Represents the empty energy level of the avatar.
     */
    public static final int ENERGY_EMPTY = 0;

    /**
     * Represents the decrease in running energy over time.
     */
    public static final float STATE_RUN_DECREASE = -0.5f;

    /**
     * Represents the speed of animation frames.
     */
    public static final float ANIMATION_FRAME_SPEED = 0.2f;

    /**
     * Represents the decrease in jumping energy over time.
     */
    public static final int STATE_JUMP_DECREASE = -5;

    /**
     * Represents the increase in idle energy over time.
     */
    public static final float STATE_IDLE_INCREASE = 0.5F;

    /**
     * Represents the height of a bar in the user interface.
     */
    public static final int BAR_HEIGHT = 50;

    /**
     * Represents the width of a bar in the user interface.
     */
    public static final int BAR_WIDTH = 50;

    /**
     * Represents the color of the energy bar.
     */
    public static final Color ENERGY_COLOR =
            new Color(32, 152, 28);

    /**
     * Represents the font size of the energy text.
     */
    public static final float ENERGY_FONT_SIZE = 60 ;

    /**
     * Represents the margin for tree placement.
     */
    public static final int MARGIN_FOR_TREES = 30 ;

    /**
     * Represents the minimum height of a tree.
     */
    public static final int MIN_TREE_HEIGHT = 3 ;

    /**
     * Represents the color of leaves.
     */
    public static final Color LEAVES_COLOR = new Color(50, 200 ,30);

    /**
     * Represents the color of the log.
     */
    public static final Color LOG_COLOR = new Color(100, 50 ,20);

    /**
     * Represents the size of leaves and fruits.
     */
    public static final Vector2 LEAF_AND_FRUIT_SIZE = new Vector2(15,15);

    /**
     * Represents the area covered by leaves.
     */
    public static final int LEAF_AREA = 4;

    /**
     * Represents the color of fruits.
     */
    public static final Color FRUIT_COLOR = Color.RED;

    /**
     * Represents the final angle for moving leaves.
     */
    public static final float FINAL_ANGLE_MOVING_LEAF = 10;

    /**
     * Represents the initial angle for moving leaves.
     */
    public static final float INITIAL_ANGLE_MOVING_LEAF = -10;

    /**
     * Represents the energy perk gained by eating an apple.
     */
    public static final float APPLE_ENERGY_PERK = 15;

    /**
     * Represents the color of the second type of fruit.
     */
    public static final Color SECOND_FRUIT_COLOR = Color.BLUE;

}


