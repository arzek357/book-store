package com.geekbrains.book.store;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.services.BookService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.slf4j.MDC.get;

@WebMvcTest
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class BookRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private static Book book1;
    private static Book book2;
    private static Book book3;

    @BeforeAll
    public static void init(){
        book1 = new Book(1L, Book.Category.FANTASTIC,"title1","desc1",new BigDecimal(500),2004);
        book2 = new Book(2L, Book.Category.FANTASTIC,"title2","desc2",new BigDecimal(500),2004);
        book3 = new Book(3L, Book.Category.FANTASTIC,"title3","desc3",new BigDecimal(500),2004);
    }

    @Test
    public void getAllBooksTest(){
        List<Book> allBooks = Arrays.asList(book1,book2,book3);
        given(bookService.findAll()).willReturn(allBooks);

    }



}
