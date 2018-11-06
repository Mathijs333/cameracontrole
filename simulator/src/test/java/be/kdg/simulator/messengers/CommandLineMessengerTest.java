package be.kdg.simulator.messengers;

import be.kdg.simulator.model.CameraMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommandLineMessengerTest {

    @Autowired
    private Messenger messenger;

    @Test
    public void sendMessage()  {
        Boolean exception = false;
        try {
            messenger.sendMessage(new CameraMessage());
        }
        catch (Exception ex) {
            exception = true;
        }
        Assert.assertFalse("Thrown exception", exception);
    }
}
