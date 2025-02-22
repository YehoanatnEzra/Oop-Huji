package ascii_art;
import image.Image;
import image.ImageHandler;
import image_char_matching.SubImgCharMatcher;

/**
 * Represents an algorithm for generating ASCII art from an image.
 */
public class AsciiArtAlgorithm {
    private ImageHandler processedImage;
    private SubImgCharMatcher matcher;

    /**
     * Constructs an AsciiArtAlgorithm object with the specified image,
     * resolution, and character matcher.
     * @param image The input image to be processed.
     * @param resolution The resolution for processing the image.
     * @param subImgCharMatcher The character matcher used for mapping
     *image brightness to characters.
     */
    public AsciiArtAlgorithm(Image image, int resolution, SubImgCharMatcher subImgCharMatcher) {
        this.processedImage = new ImageHandler(image, resolution);
        this.matcher = subImgCharMatcher;
    }

    /**
     * Runs the ASCII art generation algorithm on the input image.
     * @return A 2D array representing the ASCII art image.
     */
    public char[][] run() {
        Image[][] imageArray = this.processedImage.getSubImagesArray();
        int rows = imageArray.length;
        int cols = imageArray[0].length;
        char[][] asciiArtImage = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                asciiArtImage[i][j] = this.matcher.getCharByImageBrightness(
                        this.processedImage.getImageBrightness(imageArray[i][j]));
            }
        }
    return asciiArtImage;
    }
}
