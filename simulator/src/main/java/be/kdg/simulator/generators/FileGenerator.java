package be.kdg.simulator.generators;

import be.kdg.simulator.model.CameraMessage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

@Component
@ConditionalOnProperty(name = "generator.type", havingValue = "file")
public class FileGenerator implements MessageGenerator {
    //static list die gebruikt word door generate en timestamp aanpast naar delay, generate handelt deze één voor één af
    //Moet bestand inlezen (cameraID, nummerplaat, delay)
    @Override
    public CameraMessage generate() {
        String fileName = "";
        String textLine;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((textLine = bufferedReader.readLine()) != null) {
                textLine = bufferedReader.readLine();
                List<String> splitTextLine = Arrays.asList(textLine.split(","));
                //parsen naar int, string, int?
            }
        }
        catch (Exception ex) {
            //TODO catchen
        }
        return null;
    }
}
