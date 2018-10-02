package be.kdg.simulator;

import be.kdg.simulator.messengers.Messenger;
import be.kdg.simulator.model.CameraMessage;
import be.kdg.simulator.services.GeneratorService;
import be.kdg.simulator.services.MessageGeneratorService;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.sound.midi.Receiver;

@SpringBootApplication
@EnableScheduling
public class SimulatorApplication {

    static final String topicExchangeName = "spring-boot-exchange";

    static final String queueName = "cameraControle";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.Cameracontrole");
    }



    public static void main(String[] args) {
        SpringApplication.run(SimulatorApplication.class, args);
        MessageGeneratorService service = new MessageGeneratorService();
        service.start();
    }
}