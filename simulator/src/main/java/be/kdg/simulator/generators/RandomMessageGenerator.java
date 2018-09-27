package be.kdg.simulator.generators;

import be.kdg.simulator.model.CameraMessage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
@ConditionalOnProperty(name = "generator.type", havingValue = "random")
public class RandomMessageGenerator implements MessageGenerator {
    private Random random = new Random();
    private static int maxNumber = 9;
    private static int id = 1;

    @Override
    public CameraMessage generate() {
        return new CameraMessage(id++, generateLicensePlate(), LocalDateTime.now());
    }

    public String generateLicensePlate() {
        String licensePlate = "1-";
        for (int i = 0; i < 3; i++) {
            licensePlate += ((char)(random.nextInt(26) + 'A'));
        }
        licensePlate += "-" + (random.nextInt(900) + 100);
        return licensePlate;
    }
}
