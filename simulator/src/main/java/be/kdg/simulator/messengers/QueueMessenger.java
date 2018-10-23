package be.kdg.simulator.messengers;

import be.kdg.simulator.generators.FileGenerator;
import be.kdg.simulator.model.CameraMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "messenger.type", havingValue = "queue")
public class QueueMessenger implements Messenger {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileGenerator.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Override
    public void sendMessage(CameraMessage message) throws JsonProcessingException {
        if (message != null) {
                rabbitTemplate.convertAndSend("spring-boot-exchange", "cameraControle.queue", cameraMessageToXML(message));
                LOGGER.info("Placed: ", message);
        }
    }

    public String cameraMessageToXML(CameraMessage message) throws JsonProcessingException {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            xmlMapper.registerModule(new JavaTimeModule());
            String xml = xmlMapper.writeValueAsString(message);
            return xml;
    }
}
