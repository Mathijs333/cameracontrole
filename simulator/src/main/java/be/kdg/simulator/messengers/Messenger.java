package be.kdg.simulator.messengers;

import be.kdg.simulator.model.CameraMessage;
import org.springframework.stereotype.Component;

//waarden versturen
//TODO van messengers gebruik maken
@Component
public interface Messenger {
    void sendMessage(CameraMessage message);
}
