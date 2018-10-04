package be.kdg.processor.receivers;

import be.kdg.processor.model.Camera;
import be.kdg.processor.model.CameraMessage;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.json.JsonObject;

/**
 * @author Mathijs Constantin
 * @version 1.0 4/10/2018 18:56
 */
@Component
public class QueueReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueueReceiver.class);

    public void receiveMessage(CameraMessage message) {
        LOGGER.info("Message: " + message);

        System.out.println(message);

    }
}
