package com.geekbrains.book.store.controllers.rest;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.entities.BookParams;
import com.geekbrains.book.store.exceptions.ResourceNotFoundException;
import com.geekbrains.book.store.services.BookService;
import com.geekbrains.book.store.utils.BookFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookRestController {
    private BookService bookService;

    @GetMapping("/all")
    public List<Book> getAllBooks(){
        return bookService.findAll();
    }

    @GetMapping
    public Page<Book> getFirstBookPage(){
        return bookService.findAllByPageWithoutSpec(PageRequest.of(0,5, Sort.Direction.ASC,"id"));
    }

    @PostMapping(value = "/filter",consumes = "application/json",produces = "application/json")
    public Page<Book> getAllBooksWithFilter(@RequestBody BookParams bookParams){
        BookFilter bookFilter = new BookFilter(bookParams);
        System.out.println(bookFilter.getFilterDefinition());
        Page<Book> books = bookService.findAllByPage(bookFilter.getSpec(), PageRequest.of(bookParams.getPageNumber()-1,5, Sort.Direction.ASC,"id"));
        return books;
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable long id){
        return bookService.findById(id);
    }

    @PostMapping(consumes = "application/json",produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createNewBook(@RequestBody Book book){
        if (book.getId()!=null){
            book.setId(null);
        }
        return bookService.saveOrUpdate(book);
    }

    @PutMapping(consumes = "application/json",produces = "application/json")
    public Book modifyBook(@RequestBody Book book){
        if (!bookService.existsById(book.getId())){
            throw new ResourceNotFoundException("Book with id: "+book.getId()+" doesn't exist!");
        }
        return bookService.saveOrUpdate(book);
    }

    @DeleteMapping
    public void deleteAllBooks(){
        bookService.deleteAllBooks();
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable long id){
        bookService.deleteById(id);
    }
}
