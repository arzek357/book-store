package com.geekbrains.book.store;

import com.geekbrains.book.store.beans.Cart;
import com.geekbrains.book.store.entities.Book;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class CartTests {
    private Cart cart;
    private static Book book1;
    private static Book book2;
    private static Book book3;

    @BeforeAll
    public static void init(){
        book1 = new Book(1L, Book.Category.FANTASTIC,"title1","desc1",new BigDecimal(500),2004);
        book2 = new Book(2L, Book.Category.FANTASTIC,"title2","desc2",new BigDecimal(500),2004);
        book3 = new Book(3L, Book.Category.FANTASTIC,"title3","desc3",new BigDecimal(500),2004);
    }


    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @AfterEach
    public void clearCart(){
        cart.clearCart();
    }

    @Test
    public void newBookInCartTest(){
        cart.addNewBooks(book1,1);
        Assertions.assertTrue(cart.getGoodsList().containsKey(book1));
    }


    @Test
    public void addCupOfBooksInCartTest(){
        cart.addNewBooks(book1,1);
        cart.addNewBooks(book2,1);
        cart.addNewBooks(book3,1);
        Assertions.assertTrue(cart.getGoodsList().containsKey(book1));
        Assertions.assertTrue(cart.getGoodsList().containsKey(book2));
        Assertions.assertTrue(cart.getGoodsList().containsKey(book3));
        Assertions.assertEquals(3,cart.getGoodsList().size());
    }

    @Test
    public void addSameBookInCartAndCheckSizeTest(){
        cart.addNewBooks(book1,1);
        cart.addNewBooks(book1,4);
        cart.addNewBooks(book1,12);
        Assertions.assertEquals(1,cart.getGoodsList().size());
    }

    @Test
    public void addSameBookInCartAndCheckNumberTest(){
        cart.addNewBooks(book1,1);
        cart.addNewBooks(book1,4);
        cart.addNewBooks(book1,12);
        Assertions.assertEquals(17,cart.getGoodsList().get(book1));
    }

    @Test
    public void clearCartTest(){
        cart.addNewBooks(book1,1);
        cart.addNewBooks(book2,4);
        cart.clearCart();
        Assertions.assertEquals(0,cart.getGoodsList().size());
    }

    @Test
    public void deleteBookTest(){
        cart.addNewBooks(book1,1);
        cart.addNewBooks(book2,4);
        cart.deleteBooks(book1);
        Assertions.assertFalse(cart.getGoodsList().containsKey(book1));
    }

    @Test
    public void checkOrderItemsListSizeTest(){
        cart.addNewBooks(book1,1);
        cart.addNewBooks(book2,4);
        cart.addNewBooks(book2,9);
        cart.addNewBooks(book3,2);
        Assertions.assertEquals(cart.getGoodsList().size(),cart.getOrderItemsListAndCleanCart().size());
    }
}
