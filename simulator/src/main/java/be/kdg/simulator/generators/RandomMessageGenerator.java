package be.kdg.simulator.generators;

import be.kdg.simulator.model.CameraMessage;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Random;

@Component
@ConditionalOnProperty(name = "generator.type", havingValue = "random")
public class RandomMessageGenerator implements MessageGenerator {
    private Random random = new Random();
    private final static int MAX_ID = 8;
    private static final Logger LOGGER = LoggerFactory.getLogger(FileGenerator.class);

    @Override
    public CameraMessage generate() {
       CameraMessage message = new CameraMessage(random.nextInt(MAX_ID - 1) + 1, generateLicensePlate(), LocalDateTime.now());
       LOGGER.info("Generated: ", message);
       return message;
    }

    @Override
    public Pair<CameraMessage, Integer> getFullCameraMessage() {
        int minutes = random.nextInt(1000) + 100; //TODO getal veranderen
        return new Pair<>(generate(), minutes);
    }

    //TODO 3 cijfers moeten ook met 0 kunnen beginnen
    public String generateLicensePlate() {
        String licensePlate = "1-";
        for (int i = 0; i < 3; i++) {
            licensePlate += ((char)(random.nextInt(26) + 'A'));
        }
        licensePlate += "-" + (random.nextInt(900) + 100);
        return licensePlate;
    }
}
