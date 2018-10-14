package be.kdg.simulator.generators;

import be.kdg.simulator.model.CameraMessage;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public interface MessageGenerator {
    CameraMessage generate();
    Pair<CameraMessage, Integer> getFullCameraMessage();
}
