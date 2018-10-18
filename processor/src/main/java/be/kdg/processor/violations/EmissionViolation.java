package be.kdg.processor.violations;

import be.kdg.processor.model.*;
import be.kdg.processor.services.LicensePlateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Mathijs Constantin
 * @version 1.0 4/10/2018 20:51
 */
@Component
public class EmissionViolation implements Violation {
    private int factor = Factors.valueOf(this.getClass().getSimpleName()).getValue();

    @Autowired
    private LicensePlateService licensePlateService;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Pair<Boolean, Fine> isViolation(Camera camera, CameraMessage message1) {
        if (camera.getEuroNorm() == 0) {
            return new Pair<>(false, null);
        }
        try { ;
            Car car = objectMapper.readValue(licensePlateService.get(message1.getLicensePlate()), Car.class);
            System.out.println("Euronorm auto: " + car.getEuroNumber());
            int euronorm = car.getEuroNumber();
            int allowedEuronorm = camera.getEuroNorm();
            if (euronorm < allowedEuronorm) {
                FineData fineData = new FineData(message1.getLicensePlate(), message1.getTimestamp(), camera, euronorm, allowedEuronorm, this.getClass().getSimpleName());
                return new Pair<>(true, new Fine(calculateFine(euronorm, allowedEuronorm), fineData));
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return new Pair<>(false, null);
    }

    @Override
    public int calculateFine(int value, int allowedValue) {
        return factor;
    }

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }
}
