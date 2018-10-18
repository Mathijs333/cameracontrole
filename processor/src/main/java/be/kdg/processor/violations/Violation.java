package be.kdg.processor.violations;

import be.kdg.processor.model.Camera;
import be.kdg.processor.model.CameraMessage;
import be.kdg.processor.model.Fine;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

/**
 * @author Mathijs Constantin
 * @version 1.0 4/10/2018 20:49
 */
@Component
public interface Violation {
    Pair<Boolean, Fine> isViolation(Camera camera, CameraMessage message1);
    int calculateFine(int value, int allowedValue);
    int getFactor();
    void setFactor(int factor);
}
