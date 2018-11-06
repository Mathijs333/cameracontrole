package be.kdg.processor.receivers;

import be.kdg.processor.exceptions.MessageProcessingException;
import be.kdg.processor.model.CameraMessage;
import be.kdg.processor.model.Settings;
import be.kdg.processor.persistence.SettingsService;
import be.kdg.processor.services.ProcessorService;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Mathijs Constantin
 * @version 1.0 4/10/2018 18:56
 */
@Component
public class QueueReceiver {
    @Autowired
    private SettingsService settingsService;
    @Value("${retryCount}")
    private int retryCount;
    @Value("#{new Integer('${retryDelay}')}")
    private int retryDelay;
    @Value("${failedFileLocation}")
    private String failedFileLocation;
    private List<String> deletionList = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(QueueReceiver.class);
    @Autowired
    private ProcessorService processorService;
    private HashMap<String, Integer> failedMessages = new HashMap<>();


    public void receiveMessage(String message) {
        CameraMessage cameraMessage = null;
        try {
            cameraMessage = XMLToCameraMessage(message);
            processorService.receiveCameraMessage(cameraMessage);
        } catch (MessageProcessingException e) {
            try {
                messageFailed(message, true);
            } catch (Exception e1) {
                LOGGER.error("Error failed to write message to file: " + e1.getMessage());
            }
            LOGGER.error("Error: " + e.getMessage());
            System.out.println(e.getClass().getSimpleName());
        } catch (Exception e) {
            try {
                messageFailed(message, false);
            } catch (Exception e1) {
                LOGGER.error("Error failed to write message to file: " + e1.getMessage());
            }
            LOGGER.error("Error parsing xml: " + e.getMessage());
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

    public void writeMessageToFile(String message) throws IOException {
        File file = new File(failedFileLocation);
        BufferedWriter output = new BufferedWriter(new FileWriter(file, true));
        output.write(message);
        output.newLine();
        output.close();
    }

    @Scheduled(fixedDelay = 1000)
    private void processFailedMessages() {
        if (failedMessages.size() > 0) {
            List<String> messages = new ArrayList<>(failedMessages.keySet());
            for (String message : messages) {
                receiveMessage(message);
            }
        }
    }

    public void messageFailed(String message, Boolean retry) throws IOException {
        Settings settings = settingsService.load().get();
        retryCount = settings.getRetryCount();
        retryDelay = settings.getRetryDelay();
        if (retry) {
            if (!failedMessages.containsKey(message)) {
                failedMessages.put(message, 0);
            } else if (failedMessages.get(message) > retryCount) {
                failedMessages.remove(message);
                writeMessageToFile(message);
            } else {
                failedMessages.put(message, failedMessages.get(message) + 1);
            }
        }
        writeMessageToFile(message);
    }
}
