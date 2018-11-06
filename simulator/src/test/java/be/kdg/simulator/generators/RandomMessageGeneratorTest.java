package be.kdg.simulator.generators;

import be.kdg.simulator.exceptions.FileReadingException;
import be.kdg.simulator.model.CameraMessage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author Mathijs Constantin
 * @version 1.0 25/09/2018 13:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RandomMessageGeneratorTest {
    @Autowired
    private RandomMessageGenerator randomMessageGenerator;


    @Test
    public void testRandomMessageGenerator() throws InterruptedException, FileReadingException, IOException {
        String regex = "1-[A-Z]{3}-[0-9]{3}";
        CameraMessage cameraMessage = randomMessageGenerator.generate().get().getKey();
        Assert.assertFalse(cameraMessage.getLicensePlate().matches(regex));
    }
}
