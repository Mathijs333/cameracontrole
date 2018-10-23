package be.kdg.simulator.messengers;

import be.kdg.simulator.model.CameraMessage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "messenger.type", havingValue = "commandLine")

public class CommandLineMessenger implements Messenger {
    @Override
    public void sendMessage(CameraMessage message) {
        System.out.println(message);
    }
}
