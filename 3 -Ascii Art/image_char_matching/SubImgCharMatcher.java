package image_char_matching;

import ascii_art.Constant;

import java.util.*;

/**
 * The SubImgCharMatcher class provides functionality for matching characters
 * based on their brightness.
 * It allows adding and removing characters, pre-normalizing characters,
 * and retrieving characters based on brightness.
 */
public class SubImgCharMatcher {
    // Tree Map of the normalized brightnesses and their corresponding characters
    private TreeMap<Double, TreeSet<Character>> normalizedBrightnessesToChars;
    // Map of characters and their raw brightness
    private HashMap<Character, Double> notNormalizedCharsMap;
    // Map of characters and their normalized brightness
    private HashMap<Character, Double> normalizedCharsMap;
    // Sorted set of all the raw brightnesses
    private TreeSet<Double> notNormalizedBrightnesses;
    private char[] charset;

    /**
     * Constructs a SubImgCharMatcher object with the specified character set.
     *
     * @param charset The character set to be used for matching.
     */
    public SubImgCharMatcher(char[] charset) {
        this.charset = charset;
        normalizedBrightnessesToChars = new TreeMap<>();
        notNormalizedCharsMap = new HashMap<>();
        normalizedCharsMap = new HashMap<>();
        notNormalizedBrightnesses = new TreeSet<>();
        if (this.charset == null) {
            return;
        }
        for (char c : this.charset) {
            this.preNormalizeChar(c);
        }
        double maxBrightness = this.notNormalizedBrightnesses.first();
        double minBrightness = this.notNormalizedBrightnesses.last();
        this.normalize(minBrightness, maxBrightness);
    }

    /**
     * Retrieves the character that best matches the specified brightness value.
     * This method returns the character with the closest brightness value to the
     * specified brightness.
     *
     * @param brightness The brightness value for which the matching character
     *                   is to be retrieved.
     * @return The character that best matches the specified brightness value.
     */
    public char getCharByImageBrightness(double brightness) {
        double higherKey, lowerKey;
        if(brightness >= this.normalizedBrightnessesToChars.lastKey()){
            higherKey = this.normalizedBrightnessesToChars.lastKey();
        }else{
            higherKey = this.normalizedBrightnessesToChars.higherKey(brightness);
        }
        if(brightness <= this.normalizedBrightnessesToChars.firstKey()){
            lowerKey = this.normalizedBrightnessesToChars.firstKey();
        }else{
            lowerKey = this.normalizedBrightnessesToChars.lowerKey(brightness);
        }
        double diffCeiling = Math.abs(higherKey - brightness);
        double diffFloor = Math.abs(lowerKey - brightness);
        if (diffCeiling < diffFloor) {
            return this.normalizedBrightnessesToChars.get(higherKey).first();
        } else {
            return this.normalizedBrightnessesToChars.get(lowerKey).first();
        }
    }

    /**
     * Adds a character to the character matcher.
     *
     * @param c The character to be added.
     */
    public void addChar(char c) {
        double maxBrightness, minBrightness, newMaxBrightness, newMinBrightness;
        int charsAmount = this.notNormalizedBrightnesses.size();
        if (charsAmount == 0) {
            maxBrightness = 1;
            minBrightness = Constant.EMPTY;
        }
        else {
            maxBrightness = this.notNormalizedBrightnesses.last();
            minBrightness = this.notNormalizedBrightnesses.first();
        }
        this.preNormalizeChar(c);
        newMaxBrightness = this.notNormalizedBrightnesses.last();
        newMinBrightness = this.notNormalizedBrightnesses.first();
        if (maxBrightness != newMaxBrightness || minBrightness !=
                newMinBrightness) {
            if(charsAmount > 0){
                this.normalize(newMinBrightness, newMaxBrightness);
            }else{
                this.normalize(minBrightness, maxBrightness);
            }
        } else {
            this.normalizeCharacter(c, this.notNormalizedCharsMap.get(c),
                    minBrightness, maxBrightness);
        }
    }

    /**
     * Retrieves the map of characters and their raw brightness values.
     *
     * @return The map of characters and their raw brightness values.
     */
    public HashMap<Character, Double> getNotNormalizedCharsMap() {
        return notNormalizedCharsMap;
    }

    /**
     * Removes a character from the character matcher.
     *
     * @param c The character to be removed.
     */
    public void removeChar(char c) {
        double normalizedBrightness = this.normalizedCharsMap.get(c);
        TreeSet<Character> chars =
                this.normalizedBrightnessesToChars.get(normalizedBrightness);
        int sameBrightnessAmount = chars.size();
        // removing c from the hashmaps
        double notNormalizedBrightness = this.notNormalizedCharsMap.get(c);
        this.notNormalizedCharsMap.remove(c);
        this.normalizedCharsMap.remove(c);
        this.notNormalizedBrightnesses.remove(notNormalizedBrightness);
        // removing c from tree
        if (sameBrightnessAmount == 0) {
            return;
        } else if (sameBrightnessAmount == 1) {
            normalizedBrightnessesToChars.remove(normalizedBrightness);
            //check weather the normalization is effected
            if ((!normalizedBrightnessesToChars.isEmpty()) &&
                    (normalizedBrightnessesToChars.firstKey() == normalizedBrightness ||
                            normalizedBrightnessesToChars.lastKey() == normalizedBrightness)) {
                this.normalize(this.notNormalizedBrightnesses.first(),
                        this.notNormalizedBrightnesses.last());
            }
        } else {
            chars.remove(c);
        }
    }

    private void preNormalizeChar(char c) {
        boolean[][] charConverted = CharConverter.convertToBoolArray(c);
        double notNormalizedBrightness = getRawBrightness(charConverted);
        this.notNormalizedCharsMap.put(c, notNormalizedBrightness);
        this.notNormalizedBrightnesses.add(notNormalizedBrightness);
    }

    private void normalize(double minBrightness, double maxBrightness) {
        this.normalizedBrightnessesToChars.clear();
        this.normalizedCharsMap.clear();
        //Normalizes notNormalizedCharsMap field into normalizedBrightnessesToChars
        for (HashMap.Entry<Character, Double> entry :
                this.notNormalizedCharsMap.entrySet()) {
            this.normalizeCharacter(entry.getKey(), entry.getValue(),
                    minBrightness, maxBrightness);
        }
    }

    private void normalizeCharacter(Character curChar, Double rawBrightness,
                                    double minBrightness, double maxBrightness) {
        double normalizedBrightness = (rawBrightness - minBrightness) /
                (maxBrightness - minBrightness);
        this.normalizedCharsMap.put(curChar, normalizedBrightness);
        if (this.normalizedBrightnessesToChars.containsKey(normalizedBrightness)) {
            // If it exists, add the character to the corresponding SortedSet
            this.normalizedBrightnessesToChars.get(normalizedBrightness).add(curChar);
        } else {
            // If it doesn't exist, create a new SortedSet and add the character to it
            TreeSet<Character> charSet = new TreeSet<>();
            charSet.add(curChar);
            normalizedBrightnessesToChars.put(normalizedBrightness, charSet);
        }
    }
    private double getRawBrightness(boolean[][] charConverted) {
        double truePixels = 0;
        for (int i = 0; i < Constant.CHAR_BRIGHTNESS_CALC_DIM; i++) {
            for (int j = 0; j < Constant.CHAR_BRIGHTNESS_CALC_DIM; j++) {
                truePixels += charConverted[i][j] ? 1 : 0;
            }
        }
        return truePixels / Constant.CHAR_BRIGHTNESS_CALC_RESOLUTION;
    }
}
