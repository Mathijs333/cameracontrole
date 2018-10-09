package be.kdg.simulator.messengers;

import be.kdg.simulator.generators.FileGenerator;
import be.kdg.simulator.model.CameraMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.codec.xml.Jaxb2XmlEncoder;
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
        if (message != null) {
                rabbitTemplate.convertAndSend("spring-boot-exchange", "cameraControle.queue", cameraMessageToXML(message));
                LOGGER.info("Placed: ", message);
        }
    }

    public String cameraMessageToXML(CameraMessage message) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            xmlMapper.registerModule(new JavaTimeModule());
            String xml = xmlMapper.writeValueAsString(message);
            return xml;
        }
        catch (Exception ex) {
            LOGGER.error("Error xml", ex);
        }
        return null;
    }
}
