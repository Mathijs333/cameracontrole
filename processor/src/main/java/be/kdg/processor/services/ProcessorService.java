package be.kdg.processor.services;

import be.kdg.processor.model.Camera;
import be.kdg.processor.model.CameraMessage;
import be.kdg.processor.violations.Violation;
import be.kdg.sa.services.CameraServiceProxy;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mathijs Constantin
 * @version 1.0 4/10/2018 21:31
 */
@Service
public class ProcessorService {
    @Autowired
    private CameraService cameraService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    Map<String, Violation> violations = new HashMap<>();

    public void receiveCameraMessage(CameraMessage message) {
        System.out.println(message.toString());
        try {
            Camera camera = objectMapper.readValue(cameraService.get(5), Camera.class);
            for (Violation violation : violations.values()) {
                if (violation.isViolation(camera, message, null)) {
                    System.out.println("Boete, euronorm camera: " + camera.getEuroNorm());

                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
