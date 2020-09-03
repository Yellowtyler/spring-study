package com.geekbrains.book.store.receiver;

import com.geekbrains.book.store.entities.Order;
import com.geekbrains.book.store.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SimpleMessageReceiver {
    private RabbitTemplate rabbitTemplate;
    private OrderService orderService;

    public void receiveMessage(byte[] message) {
        System.out.println("Received from topic <" + new String(message) + ">");
        System.out.println(message);
    }

    public void receiveReadyOrderId(byte[] message) {
        System.out.println("Received result <" + new String(message) + ">");
        Order order = orderService.findById(Long.parseLong(new String(message)));
        order.setStatus("ready");
        orderService.save(order);
        System.out.println(order.getId() + " ready");
    }
}
