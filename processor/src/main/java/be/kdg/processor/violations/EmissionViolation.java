package be.kdg.processor.violations;

import be.kdg.processor.model.*;
import be.kdg.processor.persistence.FineRepository;
import be.kdg.processor.persistence.FineService;
import be.kdg.processor.services.LicensePlateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Mathijs Constantin
 * @version 1.0 4/10/2018 20:51
 */
@Component
public class EmissionViolation implements Violation {
    private int factor = Factors.valueOf(this.getClass().getSimpleName()).getValue();
    @Value("${emissionViolationTimeframe}")
    private int timeframe;

    @Autowired
    private FineService fineService;
    @Override
    public Pair<Boolean, Fine> isViolation(Camera camera, CameraMessage message1, Car car) {
        if (camera.getEuroNorm() == 0) {
            return new Pair<>(false, null);
        }
        try { ;
            int euronorm = car.getEuroNumber();
            int allowedEuronorm = camera.getEuroNorm();
            if (euronorm < allowedEuronorm && !fineService.existsFine(message1.getLicensePlate(), LocalDateTime.now().minusHours((long)timeframe), this.getClass().getSimpleName())) {
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
