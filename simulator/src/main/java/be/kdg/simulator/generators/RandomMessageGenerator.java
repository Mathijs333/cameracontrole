package be.kdg.simulator.generators;

import be.kdg.simulator.model.CameraMessage;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

@Component
@ConditionalOnProperty(name = "generator.type", havingValue = "random")
public class RandomMessageGenerator implements MessageGenerator {
    private Random random = new Random();
    @Value("${frequency}")
    private int frequency;
    @Value("${frequency}")
    private int standardFrequency;
    @Value("${rushFrequency}")
    private int rushFrequency;
    @Value("${maximumId}")
    private int MAX_ID;
    @Value("${rushHourTimeframes}")
    private String rushHourTimeFrames;
    private static final Logger LOGGER = LoggerFactory.getLogger(FileGenerator.class);

    @Override
    public CameraMessage generate() {
       CameraMessage message = new CameraMessage(random.nextInt(MAX_ID - 1) + 1, generateLicensePlate(), LocalDateTime.now());
       LOGGER.info("Generated: ", message);
       return message;
    }

    private void checkRushHour() {
        LocalTime now = LocalTime.now();
        String[] splitTimeframes = rushHourTimeFrames.split(",");
        for (String timeFrame : splitTimeframes) {
            String[] splitTimes = timeFrame.split(" ");
            LocalTime startTime = LocalTime.parse(splitTimes[0]);
            LocalTime endTime = LocalTime.parse(splitTimes[1]);
            if (startTime.isBefore(now) && endTime.isAfter(now)) {
                frequency = rushFrequency;
                return;
            }
            else {
                frequency = standardFrequency;
            }
        }
    }

    @Override
    public Pair<CameraMessage, Integer> getFullCameraMessage() throws InterruptedException {
        checkRushHour();
        Thread.sleep(random.nextInt(1000) * frequency);
        int minutes = random.nextInt(1000) + 100;
        return new Pair<>(generate(), minutes);
    }

    public String generateLicensePlate() {
        String licensePlate = "1-";
        for (int i = 0; i < 3; i++) {
            licensePlate += ((char)(random.nextInt(26) + 'A'));
        }
        licensePlate += "-" + (random.nextInt(900) + 100);
        return licensePlate;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
