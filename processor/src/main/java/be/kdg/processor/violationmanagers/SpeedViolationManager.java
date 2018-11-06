package be.kdg.processor.violationmanagers;

import be.kdg.processor.model.*;
import be.kdg.processor.persistence.SettingsService;
import net.jodah.expiringmap.ExpiringMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author Mathijs Constantin
 * @version 1.0 9/10/2018 15:09
 */
@Component
public class SpeedViolationManager implements ViolationManager {
    @Autowired
    private SettingsService settingsService;
    @Value("${speedCameraBufferTime}")
    private int bufferTime;
    private int factor = Factors.valueOf(this.getClass().getSimpleName()).getValue();

    private Map<CameraMessage, Integer> cameraMessages = new HashMap<>();/*ExpiringMap.builder().expiration(bufferTime, TimeUnit.MINUTES).build();*/
    @Override
    public Optional<Fine> isViolation(Camera camera, CameraMessage message2, Car car) {
        Settings settings = settingsService.load().get();
        bufferTime = settings.getBufferTime();
        factor = settings.getViolationFactors().get(this.getClass().getSimpleName());

        if (camera.getSegment() == null) {
            return Optional.empty();
        }
        int speedLimit = camera.getSegment().get("speedLimit");
        int connectedCamera = camera.getSegment().get("connectedCameraId");
        for (CameraMessage message1 : cameraMessages.keySet()) {
            if (LocalDateTime.now().minusMinutes((long)bufferTime).isAfter(message1.getTimestamp())) {
                cameraMessages.remove(message1);
            }
            if (/*cameraMessages.get(message1) == connectedCamera && */message1.getLicensePlate().equals(message2.getLicensePlate())) {
                int distance = camera.getSegment().get("distance");
                long millis = ChronoUnit.MILLIS.between(message1.getTimestamp(), message2.getTimestamp());
                cameraMessages.remove(message1);
                int speed = ((int)(distance / (millis / 10))) * 5;
                if (speed > speedLimit) {
                    return Optional.of(new Fine(calculateFine(speed, speedLimit), message1.getLicensePlate(), message2.getTimestamp(), this.getClass().getSimpleName(), speed, speedLimit, car.getNationalNumber()));
                }
                return Optional.empty();
            }
        }
        cameraMessages.putIfAbsent(message2, connectedCamera);
        return Optional.empty();
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
