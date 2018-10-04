package be.kdg.processor.services;

import be.kdg.processor.model.Camera;
import be.kdg.sa.services.CameraServiceProxy;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Mathijs Constantin
 * @version 1.0 4/10/2018 20:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CameraServiceTest {
    //TODO dit zou wired moeten zijn
    @Autowired
    private CameraService cameraService;
    @Test
    public void CallCameraService() {
        try {
            System.out.println(cameraService.get(5));
            ObjectMapper objectMapper = new ObjectMapper();
            Camera camera = objectMapper.readValue(cameraService.get(5), Camera.class);
            System.out.println(camera.getEuroNorm());
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}