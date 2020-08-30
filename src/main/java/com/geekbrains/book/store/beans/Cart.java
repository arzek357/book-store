package com.geekbrains.book.store.beans;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.OrderItem;
import com.geekbrains.book.store.services.orderServices.OrderItemService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Getter
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {

    private Map<Book,Integer> goodsList = new HashMap<>();

    public void addNewBooks(Book book, int number){
        if (!goodsList.containsKey(book))
        goodsList.put(book,number);
        else {
            goodsList.replace(book,goodsList.get(book),goodsList.get(book)+number);
        }
    }

    public void deleteBooks(Book book){
            goodsList.remove(book);
    }

    public List<OrderItem> getOrderItemsListAndCleanCart(){
        List<OrderItem> orderItemsList = new ArrayList<>();
        for (Map.Entry<Book,Integer> entry:goodsList.entrySet()){
            orderItemsList.add(new OrderItem(entry.getKey(),entry.getValue()));
        }
        clearCart();
        return orderItemsList;
    }

    public void clearCart(){
        goodsList.clear();
    }
}
