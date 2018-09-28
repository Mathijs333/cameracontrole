package be.kdg.simulator.messengers;

import org.springframework.stereotype.Component;

//waarden versturen
//TODO van messengers gebruik maken
@Component
public interface Messenger {
    void sendMessage();
}
