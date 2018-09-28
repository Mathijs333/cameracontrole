package be.kdg.simulator.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Mathijs Constantin
 * @version 1.0 25/09/2018 14:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageGeneratorServiceTest {
    @Autowired
    private GeneratorService generatorService;

    @Test
    public void testStart() {
        generatorService.start();
    }
}
