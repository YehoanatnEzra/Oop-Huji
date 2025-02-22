package image;


import ascii_art.Constant;

import java.awt.*;

/**
 * The ImageHandler class provides functionality for processing images,
 * including padding an image to ensure.
 * its dimensions are powers of two and dividing it into
 * smaller sub-images based on a specified resolution.
 */
public class ImageHandler {
    private Image image;
    private Image paddedImage;
    private Image[][] subImagesArray;
    private int resolution;

    /**
     * Constructs an ImageHandler object with the provided
     * image and resolution.
     * This constructor pads the original image to ensure its
     * dimensions are powers of two, then divides the padded image into
     * smaller sub-images based on the given resolution.
     *
     * @param image      The original image to be processed.
     * @param resolution The desired resolution for dividing the image into
     * sub-images Higher resolution values result in smaller sub-images.
     */
    public ImageHandler(Image image, int resolution) {
        this.image = image;
        this.resolution = resolution;
        this.paddedImage = imagePadding();
        this.subImagesArray = divideIntoSubImages(this.paddedImage, this.resolution);
    }

    /**
     * Retrieves the array of sub-images created by dividing the original
     * image into smaller sections.
     *
     * @return The 2D array of Image objects representing the sub-images.
     */
    public Image[][] getSubImagesArray() {
        return this.subImagesArray;
    }

    /**
     * Adds padding to the current image to ensure both dimensions (width and height)
     * are powers of two.
     * If either dimension is not a power of two, the method increases the dimension
     * until it becomes one.
     * Then, it calculates the amount of padding needed on each side to make the dimensions
     * even.
     * Finally, it creates a new padded image by copying the original image into a larger
     * canvas with the calculated padding.
     *
     * @return A new Image object representing the padded image.
     */
    private Image imagePadding() {
        int width = this.image.getWidth();
        int height = this.image.getHeight();
        int colsAddedOnEachSide = 0;
        int rowsAddedOnEachSide = 0;
        Color[][] newPixelArray;

        if (!isPowerOfTwo(width) || !isPowerOfTwo(height)) {
            while (!isPowerOfTwo(width)) {
                width++;

            }
            colsAddedOnEachSide = (width - this.image.getWidth()) / 2;
            while (!isPowerOfTwo(height)) {
                height++;
            }
            rowsAddedOnEachSide = (height - this.image.getHeight()) / 2;
        }
        newPixelArray = copyAndPadding(this.image, colsAddedOnEachSide,
                rowsAddedOnEachSide, 0,
                this.image.getWidth(), 0, this.image.getHeight());


        return new Image(newPixelArray, width, height);
    }

    /**
     * Divides the provided image into smaller sub-images based on the given resolution.
     * Each sub-image is of size (imageWidth / resolution) x (imageHeight / resolution).
     * The method creates a 2D array of Image objects to store the sub-images.
     *
     * @param image      The original image to be divided into sub-images.
     * @param resolution The desired resolution for dividing the image.
     *                   Higher resolution values result in smaller sub-images.
     * @return A 2D array of Image objects representing the sub-images.
     */
    private Image[][] divideIntoSubImages(Image image, int resolution) {
        int subImageSize = image.getWidth() / resolution;
        int firstCol, firstRow, lastCol, lastRow;
        int arrayCol = resolution;
        int arrayRow = image.getHeight() / subImageSize;
        Image[][] subImagesArray = new Image[arrayRow][arrayCol];
        for (int r = 0; r < arrayRow; r++) {
            for (int c = 0; c < arrayCol; c++) {
                firstCol = c * subImageSize;
                lastCol = c * subImageSize + subImageSize;
                firstRow = r * subImageSize;
                lastRow = r * subImageSize + subImageSize;
                subImagesArray[r][c] = new Image(copyAndPadding(image, 0, 0,
                        firstCol, lastCol, firstRow, lastRow), subImageSize, subImageSize);
            }
        }

        return subImagesArray;
    }

    /**
     * Copies the pixels from a specified region of the original image, and adds padding
     * around it.
     *
     * @param image               The original image from which pixels will be copied.
     * @param colsAddedOnEachSide The number of columns to be added as padding on each side.
     * @param rowsAddedOnEachSide The number of rows to be added as padding on each side.
     * @param firstCol            The index of the first column in the region to be copied.
     * @param lastCol             The index of the last column in the region to be copied.
     * @param firstRow            The index of the first row in the region to be copied.
     * @param lastRow             The index of the last row in the region to be copied.
     * @return A 2D array of Color representing the copied
     * region with padding added.
     */
    private Color[][] copyAndPadding(Image image, int
            colsAddedOnEachSide, int rowsAddedOnEachSide, int firstCol,
                            int lastCol, int firstRow, int lastRow) {
        int noPaddingWidth = lastCol - firstCol;
        int noPaddingHeight = lastRow - firstRow;
        int width = noPaddingWidth + 2 * colsAddedOnEachSide;
        int height = noPaddingHeight + 2 * rowsAddedOnEachSide;
        Color[][] newPixelArray = new Color[height][width];


        // Fill padding areas with white color
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < colsAddedOnEachSide; c++) {
                newPixelArray[r][c] = Color.WHITE;
            }
            for (int c = width - colsAddedOnEachSide; c < width; c++) {
                newPixelArray[r][c] = Color.WHITE;
            }
        }
        for (int c = 0; c < width; c++) {
            for (int r = 0; r < rowsAddedOnEachSide; r++) {
                newPixelArray[r][c] = Color.WHITE;
            }
            for (int r = height - rowsAddedOnEachSide; r < height; r++) {
                newPixelArray[r][c] = Color.WHITE;
            }
        }

        // Copy pixels from the original image to the padded array
        for (int r = rowsAddedOnEachSide; r < height - rowsAddedOnEachSide; r++) {
            for (int c = colsAddedOnEachSide; c < width - colsAddedOnEachSide; c++) {
                int idxRowInSource = r - rowsAddedOnEachSide + firstRow;
                int idxColInSource = c - colsAddedOnEachSide + firstCol;
                newPixelArray[r][c] = image.getPixel(idxRowInSource, idxColInSource);
            }
        }
        return newPixelArray;
    }

    /**
     * Checks whether the given number is a power of two.
     *
     * @param num The number to be checked.
     * @return {@code true} if the number is a power of two, {@code false} otherwise.
     */
    private boolean isPowerOfTwo(int num) {
        if (num <= 0) {
            return false;
        }
        while (num > 1) {
            if (num % 2 == 1) {
                return false;
            }
            num = num / 2;
        }
        return true;
    }

    /**
     * Calculates the brightness of an image.
     * This method iterates over each pixel of the image,
     * calculates the grayscale value for each pixel,
     * and accumulates the grayscale value and the total reference
     * value for all pixels in the image.
     * The grayscale value for each pixel is computed using the formula:
     * grey = (blue * BLUE_CONST) + (green * GREEN_CONST) + (red * RED_CONST),
     * where BLUE_CONST, GREEN_CONST, and RED_CONST are constants
     * representing
     * the weights of blue, green, and red channels, respectively.
     * The total reference value is computed as the maximum possible value for a
     * pixel in the RGB color space.
     * Finally, the method returns the ratio of the accumulated grayscale value
     * to the total reference value,
     * providing a measure of the overall brightness of the image.
     *
     * @param image The Image object for which the brightness
     *is to be calculated.
     * @return The brightness of the image as a double value in
     * the range [0, 1],
     * where 0 represents complete darkness
     * and 1 represents maximum brightness.
     */
    public double getImageBrightness(Image image) {
        double grey = 0;
        double ref = 0;
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                Color curPixel = image.getPixel(i, j);
                grey += curPixel.getBlue() * Constant.BLUE_CONST +
                        curPixel.getGreen() * Constant.GREEN_CONST +
                        curPixel.getRed() * Constant.RED_CONST;
                ref += Constant.RGB_MAX;
            }
        }
        return grey / ref;
    }

}



