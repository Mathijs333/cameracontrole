package be.kdg.simulator.generators;

import be.kdg.simulator.exceptions.FileReadingException;
import be.kdg.simulator.model.CameraMessage;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public interface MessageGenerator {
    /**
     Creates optional that contains generated pair with CameraMessage and amount of seconds between next cameramessage
     @return Optional that contains generated pair with CameraMessage and amount of seconds between next cameramessage
     */
    Optional<Pair<CameraMessage, Integer>> generate() throws InterruptedException, FileReadingException, IOException;
}
