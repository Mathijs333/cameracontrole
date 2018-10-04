package be.kdg.processor.receivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Mathijs Constantin
 * @version 1.0 4/10/2018 18:56
 */
@Component
public class QueueReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueueReceiver.class);

    public void receiveMessage(String message) {
        LOGGER.info("Message: " + message);
        System.out.println(message);
    }
}
