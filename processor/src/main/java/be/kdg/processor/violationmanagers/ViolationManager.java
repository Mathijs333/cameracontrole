package be.kdg.processor.violationmanagers;

import be.kdg.processor.model.Camera;
import be.kdg.processor.model.CameraMessage;
import be.kdg.processor.model.Car;
import be.kdg.processor.model.Fine;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 *
 * Interface for classes that check on violations and return Fine in case violation is detected. Also calculates fine amount.
 *
 * @author Mathijs Constantin
 * @version 1.0 4/10/2018 20:49
 */
@Component
public interface ViolationManager {
    /**
     This method detects violation
     @param camera Camera object
     @param message1 CameraMessage object
     @param car Car object
     @return Returns optional of type Fine in case violation is detected
     */
    Optional<Fine> isViolation(Camera camera, CameraMessage message1, Car car);
    /**
     This method calculates fine of violation
     @param value Value of cameramessage
     @param allowedValue Allowed value of speed camera
     @return Amount of fine
     */
    int calculateFine(int value, int allowedValue);
    int getFactor();
    void setFactor(int factor);
}
