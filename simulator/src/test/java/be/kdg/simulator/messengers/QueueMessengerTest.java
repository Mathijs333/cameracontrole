package be.kdg.simulator.messengers;

import be.kdg.simulator.generators.MessageGenerator;
import be.kdg.simulator.model.CameraMessage;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Mathijs Constantin
 * @version 1.0 2/10/2018 15:03
 */
//TODO assert werkt niet, zelfde voor commandlinemessengertest
@RunWith(SpringRunner.class)
@SpringBootTest
public class QueueMessengerTest {
    @Autowired
    private Messenger messenger;

    @Autowired
    private MessageGenerator messageGenerator;

    @Test
    public void sendMessage() {
        Boolean exception = false;
        try {
            messenger.sendMessage(messageGenerator.generate());
        }
        catch (Exception ex) {
            exception = true;
        }
        assertTrue("Exception thrown", exception);
    }
}
