package util;

/**
 * Represents a numeric bar that can be used to visualize and
 * manipulate numeric values within a range.
 * It extends the NumericRepresentation class.
 */
public class NumericBar extends NumericRepresentation {
    private float max;
    private float min;

    /**
     * Constructs a NumericBar object with the specified initial value,
     * maximum value, and minimum value.
     *
     * @param value The initial value of the numeric bar.
     * @param max   The maximum value of the numeric bar.
     * @param min   The minimum value of the numeric bar.
     */
    public NumericBar(float value, float max, float min){
        super(value);
        this.max = max;
        this.min = min;
    }

    /**
     * Decreases the value of the numeric bar by a predefined amount
     * for a jump action.
     */
    public void jumpDec(){
        this.increaseBy(Constant.STATE_JUMP_DECREASE);
    }

    /**
     * Decreases the value of the numeric bar by a predefined amount
     * for a run action.
     */
    public void runDec(){
        this.increaseBy(Constant.STATE_RUN_DECREASE);
    }

    /**
     * Increases the value of the numeric bar by a predefined
     * for an idle action.
     */
    public void idleInc(){
        this.increaseBy(Constant.STATE_IDLE_INCREASE);
    }
}
