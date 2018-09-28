package be.kdg.simulator;

import be.kdg.simulator.messengers.Messenger;
import be.kdg.simulator.model.CameraMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SimulatorApplication {
    @Autowired
    private Messenger messenger;

    public static void main(String[] args) {
        SpringApplication.run(SimulatorApplication.class, args);
    }
}