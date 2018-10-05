package be.kdg.processor.receivers;

import be.kdg.processor.model.Camera;
import be.kdg.processor.model.CameraMessage;
import be.kdg.processor.services.ProcessorService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.json.JsonObject;

/**
 * @author Mathijs Constantin
 * @version 1.0 4/10/2018 18:56
 */
@Component
public class QueueReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueueReceiver.class);
    @Autowired
    private ProcessorService processorService;

    public void receiveMessage(String message) {
        CameraMessage cameraMessage = new CameraMessage(message);
        processorService.receiveCameraMessage(cameraMessage);
        LOGGER.info("Received: ", message);
        System.out.println("Bericht ontvangen: " + message);
    }
}
