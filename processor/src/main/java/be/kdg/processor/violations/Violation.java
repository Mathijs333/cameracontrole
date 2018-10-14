package be.kdg.processor.violations;

import be.kdg.processor.model.Camera;
import be.kdg.processor.model.CameraMessage;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Mathijs Constantin
 * @version 1.0 4/10/2018 20:49
 */
@Component
public interface Violation {
    Pair<Boolean, Integer> isViolation(Camera camera, CameraMessage message1);
    int calculateFine(int value, int allowedValue);
}
