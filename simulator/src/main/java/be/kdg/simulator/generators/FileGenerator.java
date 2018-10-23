package be.kdg.simulator.generators;

import be.kdg.simulator.model.CameraMessage;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Component
@ConditionalOnProperty(name = "generator.type", havingValue = "file")
public class FileGenerator implements MessageGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileGenerator.class);
    private HashMap<CameraMessage, Integer> cameraMessages = new HashMap<CameraMessage, Integer>();
    //static list die gebruikt word door generate en timestamp aanpast naar delay, generate handelt deze één voor één af
    //Moet bestand inlezen (cameraID, nummerplaat, delay)

    public FileGenerator() throws FileNotFoundException, IOException {
        if (cameraMessages.isEmpty()) {
            readCameraMessages();
        }
    }

    public void readCameraMessages() throws FileNotFoundException, IOException {
        String fileName = "E:\\Software Architecture\\example.csv";
        String textLine;
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while ((textLine = bufferedReader.readLine()) != null && !textLine.isEmpty()) {
            List<String> splitTextLine = Arrays.asList(textLine.split(","));
            if (!splitTextLine.get(0).isEmpty() && !splitTextLine.get(1).isEmpty() && !splitTextLine.get(2).isEmpty()) {
                cameraMessages.put(new CameraMessage(Integer.parseInt(splitTextLine.get(0)), splitTextLine.get(1), LocalDateTime.now()), Integer.parseInt(splitTextLine.get(2)));
            }
        }

    }

    public Pair<CameraMessage, Integer> getFullCameraMessage() {
        for (CameraMessage cameraMessage : cameraMessages.keySet()) {
            Pair<CameraMessage, Integer> fullCameraMessage = new Pair<>(cameraMessage, cameraMessages.get(cameraMessage));
            cameraMessages.remove(fullCameraMessage.getKey());
            return fullCameraMessage;
        }
        return new Pair<>(new CameraMessage(), 0);
    }

    @Override
    public CameraMessage generate() {
        for (CameraMessage cameraMessage : cameraMessages.keySet()) {
            LOGGER.info("Gegenereerd: ", cameraMessage);
            cameraMessages.remove(cameraMessage);
            return cameraMessage;
        }
        return new CameraMessage();
    }
}
