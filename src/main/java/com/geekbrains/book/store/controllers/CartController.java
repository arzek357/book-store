package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.beans.Cart;
import com.geekbrains.book.store.services.BookService;
import com.geekbrains.book.store.services.orderServices.OrderService;
import com.geekbrains.book.store.utils.BookFilter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private BookService bookService;
    private OrderService orderService;
    private Cart cart;

    @GetMapping
    public String showCart(Model model){
        model.addAttribute("goods",cart.getGoodsList());
        return "cart-page";
    }

    @GetMapping("/add/{id}")
    public String addProductInCart(
                                   @PathVariable long id,
                                   @RequestParam(defaultValue = "1") int number){
        cart.addNewBooks(bookService.findById(id),number);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductFromCart(@PathVariable long id){
        cart.deleteBooks(bookService.findById(id));
        return "redirect:/cart";
    }

    @GetMapping("/create_order")
    public String createOrder(Principal principal){
        orderService.saveNewOrder(principal.getName(),cart.getOrderItemsListAndCleanCart());
        return "redirect:/books";
    }
}
