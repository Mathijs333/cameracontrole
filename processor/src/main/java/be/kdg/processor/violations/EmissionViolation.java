package be.kdg.processor.violations;

import be.kdg.processor.model.Camera;
import be.kdg.processor.model.CameraMessage;
import be.kdg.processor.model.Car;
import be.kdg.processor.services.LicensePlateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Mathijs Constantin
 * @version 1.0 4/10/2018 20:51
 */
@Component
public class EmissionViolation implements Violation {
    @Autowired
    private LicensePlateService licensePlateService;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public boolean isViolation(Camera camera, CameraMessage message1, Optional<CameraMessage> message2) {
        try { ;
            Car car = objectMapper.readValue(licensePlateService.get(message1.getLicensePlate()), Car.class);
            System.out.println("Euronorm auto: " + car.getEuroNumber());
            if (car.getEuroNumber() < camera.getEuroNorm()) {
                return true;
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public double calculateFine(Camera camera, CameraMessage message1, Optional<CameraMessage> message2) {
        return 0;
    }
}
