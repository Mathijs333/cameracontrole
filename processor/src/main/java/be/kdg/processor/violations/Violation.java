package be.kdg.processor.violations;

import be.kdg.processor.model.Camera;
import be.kdg.processor.model.CameraMessage;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Mathijs Constantin
 * @version 1.0 4/10/2018 20:49
 */
@Component
//TODO strategie met alle violations? (map en dan in elke methode isviolation uitvoeren)
public interface Violation {
    boolean isViolation(Camera camera, CameraMessage message1, Optional<CameraMessage> message2);
    double calculateFine(Camera camera, CameraMessage message1, Optional<CameraMessage> message2);
}
