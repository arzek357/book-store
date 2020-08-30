package com.geekbrains.book.store.controllers.rabbitControllers;



import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/msg")
public class SpringRabbitmqController {
    public static final String EXCHANGE_FOR_NEW_ORDERS = "orders";
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @GetMapping("/order/{message}")
    public String sendMessage(@PathVariable String message) {
        rabbitTemplate.convertAndSend(EXCHANGE_FOR_NEW_ORDERS, null, "Order "+message);
        return "redirect:/books";
    }
}