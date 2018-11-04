package be.kdg.processor.config;

import be.kdg.processor.receivers.QueueReceiver;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Mathijs Constantin
 * @version 1.0 19/10/2018 11:37
 */
@Configuration
@EnableScheduling
public class QueueConfiguration {
    @Autowired
    private QueueReceiver queueReceiver;
    public static final String topicExchangeName = "spring-boot-exchange";

    public static final String queueName = "cameraControle";

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter () {
        return new MessageListenerAdapter(queueReceiver, "receiveMessage");
    }
}
