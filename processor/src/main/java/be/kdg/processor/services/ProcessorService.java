package be.kdg.processor.services;

import be.kdg.processor.model.Camera;
import be.kdg.processor.model.CameraMessage;
import be.kdg.processor.violations.Violation;
import be.kdg.sa.services.CameraServiceProxy;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Pair;
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
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    Map<String, Violation> violations = new HashMap<>();

    public void receiveCameraMessage(CameraMessage message) {
        System.out.println(message.toString());
        try {
            Camera camera = objectMapper.readValue(cameraService.get(message.getId()), Camera.class);
            for (Violation violation : violations.values()) {
                Pair<Boolean, Integer> result = violation.isViolation(camera, message);
                if (result.getKey()) {
                    System.out.printf("\nOvertreding: %s, boete: %d", violation.getClass().getSimpleName(), result.getValue());
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
