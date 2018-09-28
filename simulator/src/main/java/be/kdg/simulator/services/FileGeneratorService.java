package be.kdg.simulator.services;

import be.kdg.simulator.generators.FileGenerator;
import be.kdg.simulator.generators.MessageGenerator;
import be.kdg.simulator.messengers.Messenger;
import be.kdg.simulator.model.CameraMessage;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

/**
 * @author Mathijs Constantin
 * @version 1.0 27/09/2018 20:41
 */
//Cameramessage met delay meegeven en deze dan teurg genereren langs service?
@Service
@ConditionalOnProperty(name = "generator.type", havingValue = "file")
public class FileGeneratorService implements GeneratorService {
    @Autowired
    private FileGenerator messageGenerator;
    @Autowired
    private Messenger messenger;

    @Override
    public void start() {
        do {
            Pair<CameraMessage, Integer> message = messageGenerator.getFullCameraMessage();
            System.out.println(message.getKey());
            try {
                Thread.sleep(message.getValue());
                System.out.println(message.getKey());
            }
            catch (Exception ex) {
                //TODO exception loggen of throwen
            }
        }
        while (true);
    }
}
