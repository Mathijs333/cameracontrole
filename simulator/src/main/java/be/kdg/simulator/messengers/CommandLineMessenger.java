package be.kdg.simulator.messengers;

import be.kdg.simulator.generators.MessageGenerator;
import be.kdg.simulator.model.CameraMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
@ConditionalOnProperty(name = "messenger.type", havingValue = "commandLine")
//APPLICATION FAILED TO START oplossen met:
//a) @Qualifier gebruiken
//b) @ConditionalOnProperty gebruiken
//dd-MM-yyyy HH:mm:ss:SSS
public class CommandLineMessenger implements Messenger {
    @Override
    public void sendMessage(CameraMessage message) {
        System.out.println(message);
    }
}
