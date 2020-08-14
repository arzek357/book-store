package com.geekbrains.book.store.controllers;

import com.geekbrains.book.store.entities.Book;
import com.geekbrains.book.store.repositories.specifications.BookSpecifications;
import com.geekbrains.book.store.services.BookService;
import com.geekbrains.book.store.utils.BookFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    @GetMapping
    public String showAllBooks(Model model,
                               @RequestParam(name = "p", defaultValue = "1") Integer pageIndex,
                               @RequestParam(name = "category",required = false) List<String> categories,
                               @RequestParam Map<String, String> params
    ) {
        BookFilter bookFilter = new BookFilter(params,categories);
        Page<Book> books = bookService.findAllByPage(bookFilter.getSpec(), PageRequest.of(pageIndex-1,5, Sort.Direction.ASC,"id"));
        model.addAttribute("books", books);
        model.addAttribute("filterDef",bookFilter.getFilterDefinition());
        return "store-page";
    }
}
