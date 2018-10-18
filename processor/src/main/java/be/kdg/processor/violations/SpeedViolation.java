package be.kdg.processor.violations;

import be.kdg.processor.model.*;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;

/**
 * @author Mathijs Constantin
 * @version 1.0 9/10/2018 15:09
 */
@Component
public class SpeedViolation implements Violation {
    private int factor = Factors.valueOf(this.getClass().getSimpleName()).getValue();
    public HashMap<CameraMessage, Integer> cameraMessages = new HashMap<>();
    @Override
    public Pair<Boolean, Fine> isViolation(Camera camera, CameraMessage message2) {
        if (camera.getEuroNorm() > 0) {
            return new Pair<>(false, null);
        }
        int speedLimit = camera.getSegment().get("speedLimit");
        int connectedCamera = camera.getSegment().get("connectedCameraId");
        for (CameraMessage message1 : cameraMessages.keySet()) {
            //TODO if om kijken of buffer tijd al verstreken is
            if (cameraMessages.get(message1) == connectedCamera && message1.getLicensePlate().equals(message2.getLicensePlate())) {
                int distance = camera.getSegment().get("distance");
                long millis = ChronoUnit.MILLIS.between(message1.getTimestamp(), message2.getTimestamp());
                cameraMessages.remove(message1);
                int speed = (int)(distance / (millis / 10));
                if (speed > speedLimit) {
                    FineData fineData = new FineData(message1.getLicensePlate(), message2.getTimestamp(), camera, speed, speedLimit, this.getClass().getSimpleName());
                    return new Pair<>(true, new Fine(calculateFine(speed, speedLimit), fineData));
                }
                return new Pair<>(false, null);
            }
        }
        cameraMessages.put(message2, connectedCamera);
        return new Pair<>(false, null);
    }

    @Override
    public int calculateFine(int value, int allowedValue) {
        return (value - allowedValue) * factor;
    }

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }
}
