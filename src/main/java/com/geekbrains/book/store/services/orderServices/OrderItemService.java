package com.geekbrains.book.store.services.orderServices;

import com.geekbrains.book.store.entities.OrderItem;
import com.geekbrains.book.store.repositories.orderRepositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    private OrderItemRepository orderItemRepository;

    @Autowired
    public void setOrderItemRepository(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public OrderItem saveNewOrderItem(OrderItem orderItem) {
       return orderItemRepository.save(orderItem);
    }
}
