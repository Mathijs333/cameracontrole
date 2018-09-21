package be.kdg.simulator;

import be.kdg.simulator.generators.CameraMessage;
import be.kdg.simulator.generators.MessageGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimulatorApplicationTests {
    @Autowired
    private MessageGenerator messageGenerator() {

    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testMessageGenerator() {
        CameraMessage.cameraMessage = messageGenerator().generate();
        Assert.assertTrue();
    }

}
