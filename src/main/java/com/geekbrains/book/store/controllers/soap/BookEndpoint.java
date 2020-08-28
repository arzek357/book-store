package com.geekbrains.book.store.controllers.soap;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.exceptions.BadSoapRequestException;
import com.geekbrains.book.store.services.BookService;
import localhost._8189.store.spring.ws.books.GetBookResponse;
import localhost._8189.store.spring.ws.books.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.stream.Collectors;

@Component
@Endpoint
public class BookEndpoint {
    private static final String NAMESPACE_URI = "http://localhost:8189/store/spring/ws/books";

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "root")
    @ResponsePayload
    public Root getBook(@RequestPayload localhost._8189.store.spring.ws.books.Root root){
        if (root.getGetBookRequest()==null||(root.getGetBookRequest().getCommand()==null&&root.getGetBookRequest().getId()==null)){
            throw new BadSoapRequestException("Invalid request! Need 1 command or some books IDs!");
        }

        GetBookResponse getBookResponse = new GetBookResponse();
        if (root.getGetBookRequest().getCommand()!=null&&root.getGetBookRequest().getCommand().equals("GET_ALL")){
            getBookResponse.getBook().addAll(bookService.findAll().stream().map(this::mapToSoapBook).collect(Collectors.toList()));
        }
        else {
            for (BigInteger id : root.getGetBookRequest().getId()){
                getBookResponse.getBook().add(mapToSoapBook(bookService.findById(id.longValue())));
            }
        }
        root.setGetBookResponse(getBookResponse);
        return root;
    }
    private localhost._8189.store.spring.ws.books.Book mapToSoapBook(Book book){
        localhost._8189.store.spring.ws.books.Book newBook = new localhost._8189.store.spring.ws.books.Book();
        newBook.setCategory(book.getCategory().toString());
        newBook.setDescription(book.getDescription());
        newBook.setId(new BigInteger(book.getId().toString()));
        newBook.setPrice(new BigDecimal(String.valueOf(book.getPrice())));
        newBook.setTitle(book.getTitle());
        newBook.setPublishYear(new BigInteger(book.getPublishYear().toString()));
        return newBook;
    }
}
