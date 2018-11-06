package be.kdg.processor.violationmanagers;

import be.kdg.processor.model.Camera;
import be.kdg.processor.model.CameraMessage;
import be.kdg.processor.model.Car;
import be.kdg.processor.model.Fine;
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
    private SpeedViolationManager speedViolation;
    @Autowired
    private EmissionViolationManager emissionViolation;
    @Autowired
    private ObjectMapper objectMapper;
    private CameraMessage cameraMessage = new CameraMessage(1, "1-AZE-123", LocalDateTime.now());
    private CameraMessage cameraMessage2 = new CameraMessage(1, "1-AZE-123", LocalDateTime.now().plusNanos(50));
    private CameraMessage cameraMessage3 = new CameraMessage(4, "1-ABC-123", LocalDateTime.now());

    @Test
    public void SpeedViolation() throws IOException {

            Camera camera = objectMapper.readValue(cameraServiceProxy.get(cameraMessage.getId()), Camera.class);
            Car car = objectMapper.readValue(licensePlateServiceProxy.get(cameraMessage.getLicensePlate()), Car.class);
            speedViolation.isViolation(camera, cameraMessage, car);
            Boolean result = speedViolation.isViolation(camera, cameraMessage2, car).isPresent();
            assertTrue("Speed detectie", result);

    }

    @Test
    public void EmissionViolation() throws IOException {

            Camera camera = objectMapper.readValue(cameraServiceProxy.get(cameraMessage3.getId()), Camera.class);
            Car car = objectMapper.readValue(licensePlateServiceProxy.get(cameraMessage3.getLicensePlate()), Car.class);
            Boolean result = emissionViolation.isViolation(camera, cameraMessage3, car).isPresent();
            assertTrue("Emission detectie", result);

    }

    @Test
    public void SpeedFine() throws IOException {

            Camera camera = objectMapper.readValue(cameraServiceProxy.get(cameraMessage.getId()), Camera.class);
            Car car = objectMapper.readValue(licensePlateServiceProxy.get(cameraMessage2.getLicensePlate()), Car.class);
            speedViolation.isViolation(camera, cameraMessage, car);
            int result = speedViolation.calculateFine(80,70);
            assertEquals("Speed fine", (long)result, (long)100);

    }

    @Test
    public void EmissionFine() throws  IOException {
            Camera camera = objectMapper.readValue(cameraServiceProxy.get(cameraMessage3.getId()), Camera.class);
            Car car = objectMapper.readValue(licensePlateServiceProxy.get(cameraMessage3.getLicensePlate()), Car.class);
            int result = emissionViolation.calculateFine(1,2);
            assertEquals("Emission fine", (long)result, (long)100);
    }
}
