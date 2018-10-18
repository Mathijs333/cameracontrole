package be.kdg.processor.services;

import be.kdg.processor.model.Camera;
import be.kdg.processor.model.Fine;
import be.kdg.processor.model.FineData;
import be.kdg.processor.persistence.FineService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author Mathijs Constantin
 * @version 1.0 18/10/2018 21:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FineServiceTest {

    @Autowired
    private FineService fineService;

    @Test
    public void testSaveFine() {
        Fine fine = new Fine(100, new FineData("1-ABC-123", LocalDateTime.now(), new Camera(), 50, 40, "SpeedViolation"));
        Fine savedFine = fineService.save(fine);
        assertNotNull("Greeting is not null", savedFine.getId());
    }
}
