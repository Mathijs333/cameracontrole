package be.kdg.simulator.services;

import be.kdg.simulator.generators.FileGenerator;
import be.kdg.simulator.generators.MessageGenerator;
import be.kdg.simulator.messengers.Messenger;
import be.kdg.simulator.model.CameraMessage;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Random;

/**
 * @author Mathijs Constantin
 * @version 1.0 27/09/2018 20:41
 */
//Cameramessage met delay meegeven en deze dan teurg genereren langs service?
@Service
public class GeneratorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneratorService.class);
    @Autowired
    private MessageGenerator messageGenerator;
    @Autowired
    private Messenger messenger;

    @PostConstruct
    public void start() {
        do {
            Pair<CameraMessage, Integer> message = messageGenerator.getFullCameraMessage();
            messenger.sendMessage(message.getKey());
            try {
                Thread.sleep(message.getValue());
                message.getKey().setTimestamp(LocalDateTime.now());
                messenger.sendMessage(message.getKey());
            }
            catch (InterruptedException ex) {
                LOGGER.error("Thread interrupted", ex);
            }
        }
        while (true);
    }
}
