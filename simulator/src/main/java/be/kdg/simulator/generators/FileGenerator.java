package be.kdg.simulator.generators;

import be.kdg.simulator.model.CameraMessage;
import javafx.util.Pair;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Component
@ConditionalOnProperty(name = "generator.type", havingValue = "file")
public class FileGenerator implements MessageGenerator {
    private HashMap<CameraMessage, Integer> cameraMessages = new HashMap<CameraMessage, Integer>();
    //static list die gebruikt word door generate en timestamp aanpast naar delay, generate handelt deze één voor één af
    //Moet bestand inlezen (cameraID, nummerplaat, delay)

    public FileGenerator() {
        if (cameraMessages.isEmpty()) {
            readCameraMessages();
        }
    }

    public void readCameraMessages() {
        String fileName = "E:\\Software Architecture\\file.txt";
        String textLine;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((textLine = bufferedReader.readLine()) != null) {
                textLine = bufferedReader.readLine();
                List<String> splitTextLine = Arrays.asList(textLine.split(","));
                cameraMessages.put(new CameraMessage(Integer.parseInt(splitTextLine.get(0)), splitTextLine.get(1), LocalDateTime.now()), Integer.parseInt(splitTextLine.get(2)));
            }
        }
        catch (Exception ex) {
            //TODO catchen
        }
    }

    public Pair<CameraMessage, Integer> getFullCameraMessage() {
        for (CameraMessage cameraMessage : cameraMessages.keySet()) {
            Pair<CameraMessage, Integer> fullCameraMessage = new Pair<>(cameraMessage, cameraMessages.get(cameraMessage));
            cameraMessages.remove(fullCameraMessage.getKey());
            return fullCameraMessage;
        }
        return null;
    }

    @Override
    public CameraMessage generate() {
        for (CameraMessage cameraMessage : cameraMessages.keySet()) {
            cameraMessages.remove(cameraMessage);
            return cameraMessage;
        }
        return null;
    }
}
