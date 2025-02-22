package ascii_art;

/**
 * Contains constant values used throughout the application.
 */
public class Constant {
    /**
     * Resolution for character brightness calculation.
     */
    public static final int CHAR_BRIGHTNESS_CALC_RESOLUTION = 256;

    /**
     * Dimension for character brightness calculation.
     */
    public static final int CHAR_BRIGHTNESS_CALC_DIM = 16;

    /**
     * Weight for blue component in RGB to grayscale conversion.
     */
    public static final double BLUE_CONST = 0.0722;

    /**
     * Weight for red component in RGB to grayscale conversion.
     */
    public static final double RED_CONST = 0.2126;

    /**
     * Weight for green component in RGB to grayscale conversion.
     */
    public static final double GREEN_CONST = 0.7152;

    /**
     * Maximum value for RGB color channel.
     */
    public static final double RGB_MAX = 255;

    /**
     * Default character set used for ASCII art generation.
     */
    public static final char[] DEFAULT_CHAR_SET = {'0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9'};

    /**
     * Default resolution for ASCII art generation.
     */
    public static final int DEFAULT_RESOLUTION = 128;

    /**
     * Default path for input image.
     */
    public static final String DEFAULT_IMAGE_PATH = "cat.jpeg";

    /**
     * Default output file name for generated ASCII art.
     */
    public static final String OUTPUT_FILE_NAME = "outp.html";

    /**
     * Default font name for output HTML file.
     */
    public static final String FONT_NAME_OUTPUT = "Courier New";

    /**
     * Message indicating a change in resolution.
     */
    public static final String CHANGE_RESOLUTION_MESSAGE = "Resolution set to ";

    /**
     * Error message for invalid format during addition operation.
     */
    public static final String ADD_INVALID_MESSAGE = "Did not add due to" +
            " incorrect format.";

    /**
     * Error message for invalid format during removal operation.
     */
    public static final String REMOVE_INVALID_MESSAGE = "Did not remove due to " +
            "incorrect format.";

    /**
     * Represents the value for an empty state or condition.
     * This constant is typically used to signify that a data structure
     * or field is empty or has no elements.
     */
    public static  final int EMPTY = 0;
    /**
     * Prompt message for user input.
     */
    public static final String INPUT_USER_MESSAGE = ">>> ";

    /**
     * Error message for empty charset.
     */
    public static final String EMPTY_CHARSET_MESSAGE = "Did not execute." +
            " Charset is empty.";

    /**
     * Error message for resolution change exceeding boundaries.
     */
    public static final String EXCEEDING_BOUNDARIES_MESSAGE = "Did not change" +
            " resolution due to exceeding boundaries.";

    /**
     * Error message for invalid format during resolution change.
     */
    public static final String INVALID_RES_INPUT = "Did not change resolution" +
            " due to incorrect format.";

    /**
     * Error message for invalid image file path.
     */
    public static final String EXCEPTION_INVALID_IMAGE_PATH = "Did not execute due to" +
            " problem with image file.";

    /**
     * Error message for invalid user input.
     */
    public static final String INVALID_USER_INPUT = "Did not execute due to" +
            " incorrect command.";

    /**
     * Error message for invalid format during output method change.
     */
    public static final String SET_OUT_PUT_INVALID_COMMAND = "Did not change output" +
            " method due to incorrect format.";
}
