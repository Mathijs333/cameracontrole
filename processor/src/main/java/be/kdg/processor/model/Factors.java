package be.kdg.processor.model;

/**
 * @author Mathijs Constantin
 * @version 1.0 18/10/2018 15:51
 */
public enum Factors {
    EmissionViolation(100), SpeedViolation(10);

    private int value;

    private Factors(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
