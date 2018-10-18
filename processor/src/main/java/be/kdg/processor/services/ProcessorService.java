package be.kdg.processor.services;

import be.kdg.processor.model.Camera;
import be.kdg.processor.model.CameraMessage;
import be.kdg.processor.model.Fine;
import be.kdg.processor.persistence.FineRepository;
import be.kdg.processor.violations.Violation;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
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
    private FineRepository fineRepository;

    @Autowired
    Map<String, Violation> violations = new HashMap<>();

    public void receiveCameraMessage(CameraMessage message) {
        System.out.println(message.toString());
        try {
            Camera camera = objectMapper.readValue(cameraService.get(message.getId()), Camera.class);
            for (Violation violation : violations.values()) {
                Pair<Boolean, Fine> result = violation.isViolation(camera, message);
                if (result.getKey()) {
                    Fine fine = result.getValue();
                    fineRepository.save(fine);
                    System.out.printf("\nOvertreding: %s, boete: %d : %d test", violation.getClass().getSimpleName(), fine.getAmount(), fine.getId());
                }
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
