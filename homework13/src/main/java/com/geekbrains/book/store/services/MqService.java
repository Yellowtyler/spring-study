package com.geekbrains.book.store.services;


import com.geekbrains.book.store.receiver.SimpleMessageReceiver;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor

public class MqService {
    public static final String EXCHANGE_FOR_PROCESSING_TASK = "processingExchanger";
    public static final String QUEUE_WITH_PROCESSING_TASK_RESULTS = "processingResultsQueue";

    private RabbitTemplate rabbitTemplate;

    public String sendMessage(String message) {
        rabbitTemplate.convertAndSend(EXCHANGE_FOR_PROCESSING_TASK, null, message);
        return "OK";
    }

    @Bean
    public SimpleMessageListenerContainer containerForTopic(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_WITH_PROCESSING_TASK_RESULTS);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(SimpleMessageReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveReadyOrderId");
    }
}