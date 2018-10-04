package be.kdg.processor.services;

import be.kdg.processor.model.Camera;
import be.kdg.processor.model.CameraMessage;
import be.kdg.sa.services.CameraServiceProxy;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Mathijs Constantin
 * @version 1.0 4/10/2018 21:31
 */
@Service
public class ProcessorService {
    @Autowired
    private CameraService cameraService;
    private ObjectMapper objectMapper = new ObjectMapper();

    public void receiveCameraMessage(CameraMessage message) {
        try {
            Camera camera = objectMapper.readValue(cameraService.get(5), Camera.class);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
