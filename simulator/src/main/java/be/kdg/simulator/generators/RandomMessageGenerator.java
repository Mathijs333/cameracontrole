package be.kdg.simulator.generators;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Mathijs Constantin
 * @version 1.0 18/09/2018 14:31
 */
@Component
public class RandomMessageGenerator implements MessageGenerator {

    public CameraMessage generate() {
        return new CameraMessage(1, "1-ABC-123", LocalDateTime.now());
    }
}
