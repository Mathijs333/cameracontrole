package be.kdg.simulator.generators;

import be.kdg.simulator.exceptions.FileReadingException;
import be.kdg.simulator.model.CameraMessage;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Component
@ConditionalOnProperty(name = "generator.type", havingValue = "file")
public class FileGenerator implements MessageGenerator {
    @Value("${filename}")
    private String fileName;
    private static final Logger LOGGER = LoggerFactory.getLogger(FileGenerator.class);
    private HashMap<CameraMessage, Integer> cameraMessages = new HashMap<CameraMessage, Integer>();
    private Boolean readFile = false;

    @PostConstruct
    public void readCameraMessages() throws FileReadingException, IOException {
        BufferedReader bufferedReader;
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile().toString().replaceFirst("%20", " "));
            FileReader fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
        }
        catch (Exception ex) {
            throw new FileReadingException(ex);
        }
        String textLine;
            while ((textLine = bufferedReader.readLine()) != null && !textLine.isEmpty()) {
                try {
                    List<String> splitTextLine = Arrays.asList(textLine.split(","));
                    if (!splitTextLine.get(0).isEmpty() && !splitTextLine.get(1).isEmpty() && !splitTextLine.get(2).isEmpty()) {
                        cameraMessages.put(new CameraMessage(Integer.parseInt(splitTextLine.get(0)), splitTextLine.get(1), LocalDateTime.now()), Integer.parseInt(splitTextLine.get(2)));
                    }
                }
                catch (Exception ex) {
                    LOGGER.error("Error reading line: " + ex.getMessage());
                }
            }



    }

    public Optional<Pair<CameraMessage, Integer>> generate() throws FileReadingException, IOException {
        for (CameraMessage cameraMessage : cameraMessages.keySet()) {
            Pair<CameraMessage, Integer> fullCameraMessage = new Pair<>(cameraMessage, cameraMessages.get(cameraMessage));
            cameraMessages.remove(fullCameraMessage.getKey());
            return Optional.of(fullCameraMessage);
        }
        return Optional.empty();
    }
}
