package be.kdg.simulator.services;

import be.kdg.simulator.generators.MessageGenerator;
import be.kdg.simulator.model.CameraMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author Mathijs Constantin
 * @version 1.0 25/09/2018 14:35
 */
@Service
@ConditionalOnProperty(name = "generator.type", havingValue = "random")
public class MessageGeneratorService implements GeneratorService {
    @Autowired
    private MessageGenerator messageGenerator;

    @Override
    public void start() {
        CameraMessage message;
        try {
            do {
                Random r = new Random();
                message = messageGenerator.generate();
                System.out.println(message.toString());
                Thread.sleep(r.nextInt(500) + 100);
                System.out.println(message.toString());
            }
            while (true);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
