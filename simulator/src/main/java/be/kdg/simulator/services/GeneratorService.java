package be.kdg.simulator.services;

import be.kdg.simulator.messengers.Messenger;
import org.springframework.stereotype.Component;

/**
 * @author Mathijs Constantin
 * @version 1.0 25/09/2018 14:35
 */
//TODO messengers aanspreken langs services in de plaats van generators?
@Component
public interface GeneratorService   {
    void start();
}
