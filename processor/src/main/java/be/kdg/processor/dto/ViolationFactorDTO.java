package be.kdg.processor.dto;

/**
 * @author Mathijs Constantin
 * @version 1.0 18/10/2018 17:37
 */
public class ViolationFactorDTO {
    private String violation;
    private int factor;

    public ViolationFactorDTO(String violation, int factor) {
        this.violation = violation;
        this.factor = factor;
    }

    public String getViolation() {
        return violation;
    }

    public void setViolation(String violation) {
        this.violation = violation;
    }

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }
}
