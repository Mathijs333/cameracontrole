package be.kdg.processor.receivers;

import be.kdg.processor.model.Camera;
import be.kdg.processor.model.CameraMessage;
import be.kdg.processor.services.ProcessorService;
import be.kdg.sa.services.CameraNotFoundException;
import be.kdg.sa.services.InvalidLicensePlateException;
import be.kdg.sa.services.LicensePlateNotFoundException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.json.JsonObject;
import java.io.IOException;
import java.util.Optional;

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
        CameraMessage cameraMessage = null;
        try {
            cameraMessage = XMLToCameraMessage(message);
        } catch (IOException e) {
            LOGGER.error("Error parsing xml: " + e.getMessage());
        }

        try {
            processorService.receiveCameraMessage(cameraMessage);
        }
        catch (IOException e) {
            LOGGER.error("Error: "+  e.getMessage());
        }
        catch (CameraNotFoundException e) {
            LOGGER.error("Camera not found: " + e.getMessage());
        }
        catch (InvalidLicensePlateException e) {
            LOGGER.error("License plate wrong format: " + e.getMessage());
        }
        catch (LicensePlateNotFoundException e) {
            LOGGER.error("License plate not found: " + e.getMessage());
        }

        LOGGER.info("Received: ", message);
        System.out.println("Bericht ontvangen: " + message);
    }

    public CameraMessage XMLToCameraMessage(String xml) throws IOException {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new ParameterNamesModule())
                    .registerModule(new Jdk8Module())
                    .registerModule(new JavaTimeModule());
            xmlMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            xmlMapper.registerModule(new JavaTimeModule());
            CameraMessage message = xmlMapper.readValue(xml, CameraMessage.class);
            return message;
    }
}
