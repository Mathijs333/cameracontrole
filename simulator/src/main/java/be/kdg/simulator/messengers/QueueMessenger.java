package be.kdg.simulator.messengers;

import be.kdg.simulator.generators.FileGenerator;
import be.kdg.simulator.model.CameraMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "messenger.type", havingValue = "queue")
public class QueueMessenger implements Messenger {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileGenerator.class);
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("")
    private String exchange;

    @Value("")
    private String routingKey;

    @Override
    public void sendMessage(CameraMessage message) {
            rabbitTemplate.convertAndSend("spring-boot-exchange", "cameraControle.queue", message.toString());

        LOGGER.info("Placed: ", message);
    }
}
