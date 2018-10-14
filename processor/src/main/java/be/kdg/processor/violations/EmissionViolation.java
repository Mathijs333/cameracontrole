package be.kdg.processor.violations;

import be.kdg.processor.model.Camera;
import be.kdg.processor.model.CameraMessage;
import be.kdg.processor.model.Car;
import be.kdg.processor.services.LicensePlateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

/**
 * @author Mathijs Constantin
 * @version 1.0 4/10/2018 20:51
 */
@Component
public class EmissionViolation implements Violation {
    private final int factor = 100;

    @Autowired
    private LicensePlateService licensePlateService;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Pair<Boolean, Integer> isViolation(Camera camera, CameraMessage message1) {
        if (camera.getEuroNorm() == 0) {
            return new Pair<>(false, 0);
        }
        try { ;
            Car car = objectMapper.readValue(licensePlateService.get(message1.getLicensePlate()), Car.class);
            System.out.println("Euronorm auto: " + car.getEuroNumber());
            int euronorm = car.getEuroNumber();
            int allowedEuronorm = camera.getEuroNorm();
            if (euronorm < allowedEuronorm) {
                return new Pair<>(true, calculateFine(euronorm, allowedEuronorm));
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return new Pair<>(false, 0);
    }

    @Override
    public int calculateFine(int value, int allowedValue) {
        return factor;
    }
}
