package be.kdg.processor.violations;

import be.kdg.processor.model.Camera;
import be.kdg.processor.model.CameraMessage;
import be.kdg.processor.model.Car;
import be.kdg.processor.model.Fine;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Mathijs Constantin
 * @version 1.0 4/10/2018 20:49
 */
@Component
public interface Violation {
    Optional<Fine> isViolation(Camera camera, CameraMessage message1, Car car);
    int calculateFine(int value, int allowedValue);
    int getFactor();
    void setFactor(int factor);
}
