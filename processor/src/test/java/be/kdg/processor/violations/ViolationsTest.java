package be.kdg.processor.violations;

import be.kdg.processor.model.Camera;
import be.kdg.processor.model.CameraMessage;
import be.kdg.processor.model.Car;
import be.kdg.processor.model.Fine;
import be.kdg.processor.services.LicensePlateService;
import be.kdg.sa.services.CameraServiceProxy;
import be.kdg.sa.services.LicensePlateServiceProxy;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Pair;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mathijs Constantin
 * @version 1.0 14/10/2018 23:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ViolationsTest {
    @Autowired
    private CameraServiceProxy cameraServiceProxy;
    @Autowired
    private LicensePlateServiceProxy licensePlateServiceProxy;
    @Autowired
    private SpeedViolation speedViolation;
    @Autowired
    private EmissionViolation emissionViolation;
    @Autowired
    private ObjectMapper objectMapper;
    private CameraMessage cameraMessage = new CameraMessage(1, "1-AZE-123", LocalDateTime.now());
    private CameraMessage cameraMessage2 = new CameraMessage(1, "1-AZE-123", LocalDateTime.now().plusNanos(500000000));
    private CameraMessage cameraMessage3 = new CameraMessage(4, "1-ABC-123", LocalDateTime.now());

    @Test
    public void SpeedViolation() {
        try {
            Camera camera = objectMapper.readValue(cameraServiceProxy.get(cameraMessage.getId()), Camera.class);
            Car car = objectMapper.readValue(licensePlateServiceProxy.get(cameraMessage.getLicensePlate()), Car.class);
            speedViolation.isViolation(camera, cameraMessage, car);
            Pair<Boolean, Fine> result = speedViolation.isViolation(camera, cameraMessage2, car);
            assertTrue("Speed detectie", result.getKey());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void EmissionViolation() {
        try {
            Camera camera = objectMapper.readValue(cameraServiceProxy.get(cameraMessage3.getId()), Camera.class);
            Car car = objectMapper.readValue(licensePlateServiceProxy.get(cameraMessage3.getLicensePlate()), Car.class);
            Pair<Boolean, Fine> result = emissionViolation.isViolation(camera, cameraMessage3, car);
            assertTrue("Emission detectie", result.getKey());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void SpeedFine() {
        try {
            Camera camera = objectMapper.readValue(cameraServiceProxy.get(cameraMessage.getId()), Camera.class);
            Car car = objectMapper.readValue(licensePlateServiceProxy.get(cameraMessage2.getLicensePlate()), Car.class);
            speedViolation.isViolation(camera, cameraMessage, car);
            Pair<Boolean, Fine> result = speedViolation.isViolation(camera, cameraMessage2, car);
            assertEquals("Speed fine", (long)result.getValue().getAmount(), (long)160);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void EmissionFine() throws  IOException {
            Camera camera = objectMapper.readValue(cameraServiceProxy.get(cameraMessage3.getId()), Camera.class);
            Car car = objectMapper.readValue(licensePlateServiceProxy.get(cameraMessage3.getLicensePlate()), Car.class);
            Pair<Boolean, Fine> result = emissionViolation.isViolation(camera, cameraMessage3, car);
            assertEquals("Emission fine", (long)result.getValue().getAmount(), (long)100);
    }
}
