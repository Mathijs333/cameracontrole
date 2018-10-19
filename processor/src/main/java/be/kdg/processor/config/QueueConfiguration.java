package be.kdg.processor.config;

import be.kdg.processor.receivers.QueueReceiver;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mathijs Constantin
 * @version 1.0 19/10/2018 11:37
 */
@Configuration
public class QueueConfiguration {
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
    MessageListenerAdapter listenerAdapter (QueueReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
}
