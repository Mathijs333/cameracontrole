package be.kdg.simulator.services;

import be.kdg.simulator.exceptions.FileReadingException;
import be.kdg.simulator.generators.MessageGenerator;
import be.kdg.simulator.messengers.Messenger;
import be.kdg.simulator.model.CameraMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpMessageReturnedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Mathijs Constantin
 * @version 1.0 27/09/2018 20:41
 */

@Service
public class GeneratorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneratorService.class);
    @Autowired
    private MessageGenerator messageGenerator;
    @Autowired
    private Messenger messenger;
    @Value("${generator.type}")
    private String generatorType;

    @PostConstruct
    public void start() {
        do {
            Pair<CameraMessage, Integer> message = null;
            try {
                Optional<Pair<CameraMessage, Integer>> messageOpt = messageGenerator.generate();
                if (messageOpt.isPresent()) {
                    message = messageOpt.get();
                    try {
                        messenger.sendMessage(message.getKey());
                    } catch (AmqpMessageReturnedException e) {
                        LOGGER.error("Error sending to queue: " + e.getMessage());
                    }
                    Thread.sleep(message.getValue());
                    message.getKey().setTimestamp(LocalDateTime.now());
                    messenger.sendMessage(message.getKey());
                }

            }
            catch (JsonProcessingException e) {
                LOGGER.error("Error processing json: " + e.getMessage());
            }
            catch (InterruptedException e) {
                LOGGER.error("Error thread interrupted: " + e.getMessage());
            } catch (FileReadingException e) {
                LOGGER.error("Error reading file: " + e.getMessage());
            } catch (IOException ex) {
                LOGGER.error("Error reading line in file: " + ex.getMessage());
            }


        }
        while (true);
    }
}
