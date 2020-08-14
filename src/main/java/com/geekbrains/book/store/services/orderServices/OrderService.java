package com.geekbrains.book.store.services.orderServices;

import com.geekbrains.book.store.entities.Order;
import com.geekbrains.book.store.entities.OrderItem;
import com.geekbrains.book.store.repositories.orderRepositories.OrderRepository;
import com.geekbrains.book.store.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {
    private OrderRepository orderRepository;
    private OrderItemService orderItemService;
    private UserService userService;

    @Transactional
    public void saveNewOrder(String username, List<OrderItem> orderItemList){
        Order order = orderRepository.save(new Order(userService.findByUsername(username).get()));
        orderItemList.forEach(orderItem -> {
            orderItem.setOrder(order);
            orderItemService.saveNewOrderItem(orderItem);
        });
    }
}
