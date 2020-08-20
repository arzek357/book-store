package com.geekbrains.book.store.services;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.exceptions.ResourceNotFoundException;
import com.geekbrains.book.store.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Page<Book> findAllByPage(Specification<Book> spec, Pageable pageable) {
        return bookRepository.findAll(spec, pageable);
    }

    public Page<Book> findAllByPageWithoutSpec(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book with id: " + id + " not found"));
    }

    public Book saveOrUpdate(Book book) {
        return bookRepository.save(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public boolean existsById(long id){
        return bookRepository.existsById(id);
    }

    public void deleteAllBooks(){
        bookRepository.deleteAll();
    }

    public long getCount(){
        return bookRepository.count();
    }
}
