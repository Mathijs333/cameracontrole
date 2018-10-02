package be.kdg.simulator.messengers;

import be.kdg.simulator.generators.MessageGenerator;
import be.kdg.simulator.model.CameraMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Mathijs Constantin
 * @version 1.0 2/10/2018 15:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QueueMessengerTest {
    @Autowired
    private Messenger messenger;

    @Autowired
    private MessageGenerator messageGenerator;

    @Test
    public void sendMessage() {
        messenger.sendMessage(messageGenerator.generate());
    }
}
