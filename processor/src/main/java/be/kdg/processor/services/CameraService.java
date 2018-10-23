package be.kdg.processor.services;

import be.kdg.processor.model.CameraMessage;
import be.kdg.sa.services.CameraNotFoundException;
import be.kdg.sa.services.CameraServiceProxy;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import be.kdg.processor.model.Camera;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Mathijs Constantin
 * @version 1.0 4/10/2018 21:19
 */
@Component
public class CameraService extends CameraServiceProxy{
    @Autowired
    private ObjectMapper objectMapper;
    public Camera getCameraById(int id) throws IOException, CameraNotFoundException {
        return objectMapper.readValue(get(id), Camera.class);
    }
}
