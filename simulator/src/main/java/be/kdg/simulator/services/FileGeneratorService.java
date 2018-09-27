package be.kdg.simulator.services;

import be.kdg.simulator.generators.MessageGenerator;
import be.kdg.simulator.model.CameraMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author Mathijs Constantin
 * @version 1.0 27/09/2018 20:41
 */
@Service
@ConditionalOnProperty(name = "generator.type", havingValue = "file")
public class FileGeneratorService implements GeneratorService {
    @Autowired
    private MessageGenerator messageGenerator;

    @Override
    public void start() {
        CameraMessage message;

    }
}
