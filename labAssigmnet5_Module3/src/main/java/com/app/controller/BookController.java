package com.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Book;
import com.app.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return service.addBook(book);
    }

    @GetMapping
    public List<Book> getAll() {
        return service.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Integer id) {
        return service.getBook(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.deleteBook(id);
    }

    // 🔎 search APIs
    @GetMapping("/author/{author}")
    public List<Book> byAuthor(@PathVariable String author) {
        return service.getByAuthor(author);
    }

    @GetMapping("/category/{category}")
    public List<Book> byCategory(@PathVariable String category) {
        return service.getByCategory(category);
    }

    @GetMapping("/price/less/{price}")
    public List<Book> byPrice(@PathVariable Double price) {
        return service.getByPriceLessThan(price);
    }

    @GetMapping("/search/{keyword}")
    public List<Book> search(@PathVariable String keyword) {
        return service.searchByTitle(keyword);
    }

    @GetMapping("/price-range")
    public List<Book> priceRange(@RequestParam double min, @RequestParam double max) {
        return service.getByPriceRange(min, max);
    }
}
