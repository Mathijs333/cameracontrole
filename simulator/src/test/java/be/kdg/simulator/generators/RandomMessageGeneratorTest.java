package be.kdg.simulator.generators;

import be.kdg.simulator.model.CameraMessage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Mathijs Constantin
 * @version 1.0 25/09/2018 13:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RandomMessageGeneratorTest {
    @Autowired
    private MessageGenerator randomMessageGenerator;


    @Test
    public void testRandomMessageGenerator() {
        String regex = "1-[A-Z]{3}-[0-9]{3}";
        CameraMessage cameraMessage = randomMessageGenerator.generate();
        Assert.assertTrue(cameraMessage.getLicensePlate().matches(regex));
    }
}
