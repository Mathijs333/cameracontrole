package be.kdg.processor.violations;

import be.kdg.processor.model.Camera;
import be.kdg.processor.model.CameraMessage;
import be.kdg.processor.model.Fine;
import be.kdg.sa.services.CameraServiceProxy;
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
            speedViolation.isViolation(camera, cameraMessage);
            Pair<Boolean, Fine> result = speedViolation.isViolation(camera, cameraMessage2);
            assertTrue("Speed detectie", result.getKey());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void EmissionViolation() {
        try {
            Camera camera = objectMapper.readValue(cameraServiceProxy.get(cameraMessage3.getId()), Camera.class);
            Pair<Boolean, Fine> result = emissionViolation.isViolation(camera, cameraMessage3);
            assertTrue("Emission detectie", result.getKey());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void SpeedFine() {
        try {
            Camera camera = objectMapper.readValue(cameraServiceProxy.get(cameraMessage.getId()), Camera.class);
            speedViolation.isViolation(camera, cameraMessage);
            Pair<Boolean, Fine> result = speedViolation.isViolation(camera, cameraMessage2);
            assertEquals("Speed fine", (long)result.getValue().getAmount(), (long)160);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void EmissionFine() {
        try {
            Camera camera = objectMapper.readValue(cameraServiceProxy.get(cameraMessage3.getId()), Camera.class);
            Pair<Boolean, Fine> result = emissionViolation.isViolation(camera, cameraMessage3);
            assertEquals("Emission fine", (long)result.getValue().getAmount(), (long)100);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
