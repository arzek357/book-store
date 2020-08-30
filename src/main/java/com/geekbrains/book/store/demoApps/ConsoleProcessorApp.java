package com.geekbrains.book.store.demoApps;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class ConsoleProcessorApp {
    public static final String QUEUE_FOR_PROCESSING_NAME = "newOrdersQueue";
    public static final String EXCHANGER_FOR_PROCESSING_RESULTS = "successOrders";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("Success join for " + message);

            channel.basicPublish(EXCHANGER_FOR_PROCESSING_RESULTS, "", null, message.getBytes());
        };

        channel.basicConsume(QUEUE_FOR_PROCESSING_NAME, true, deliverCallback, consumerTag -> {
        });
    }
}