package be.kdg.simulator.messengers;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "messenger.type", havingValue = "queue")
public class QueueMessenger implements Messenger {
    @Override
    public void sendMessage() {

    }
}
