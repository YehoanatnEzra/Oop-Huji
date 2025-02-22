package util;

/**
 * Same as Counter but with floats
 */
public class NumericRepresentation {
    private float counter;

    /**
     * Initialize a new counter with the given value
     */
    public NumericRepresentation(float initValue) {
        counter = initValue;
    }

    /**
     * Initialize a new counter with the value 0
     */
    public NumericRepresentation() {
        this(0);
    }

    /**
     * Increment the value by 1
     */
    public void increment() {
        counter++;
    }

    /**
     * Decrement the value by 1
     */
    public void decrement() {
        counter--;
    }

    /**
     * Return the counter's current value
     */
    public float value() {
        return counter;
    }

    /**
     * Reset the counter to zero
     */
    public void reset() {
        counter = 0;
    }

    /**
     * Increase the counter by the supplied value (can be negative)
     */
    public void increaseBy(float val) {
        counter += val;
    }
}