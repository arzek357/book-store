package com.geekbrains.book.store.beans;

import com.geekbrains.book.store.services.orderServices.OrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SuccessOrderStatusMaker {

    private RabbitTemplate rabbitTemplate;
    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void receiveMessage(byte[] message) {
        String[] args = new String(message).split(" ");
        if (args.length==2&&args[0].equals("Order")){
            orderService.findOrderByIdAndChangeStatusOnSuccess(Long.parseLong(args[1]));
        }
    }
}