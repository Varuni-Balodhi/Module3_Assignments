package com.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Book;
import com.app.repo.BookDao;
import com.app.repo.BookJdbcDao;

@RestController
@RequestMapping("/api/jdbc/books")
public class BookController {

    private final BookJdbcDao bookJdbcDao;
    private final BookDao bookNamedDao;

    public BookController(BookJdbcDao bookJdbcDao, BookDao bookNamedDao) {
        this.bookJdbcDao = bookJdbcDao;
        this.bookNamedDao = bookNamedDao;
    }
    
    @GetMapping
    public List<Book> getAllBooks() {
        return bookJdbcDao.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookJdbcDao.findById(id);
    }

    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        bookJdbcDao.save(book);
        return ResponseEntity.ok("Book inserted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(
            @PathVariable int id,
            @RequestBody Book book) {

        bookJdbcDao.update(book);
        return ResponseEntity.ok("Book updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        bookJdbcDao.delete(id);
        return ResponseEntity.ok("Book deleted successfully");
    }

    @GetMapping("/author/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author) {
        return bookNamedDao.findByAuthor(author);
    }
    
    @GetMapping("/price-range")
    public List<Book> getBooksByPriceRange(
            @RequestParam double min,
            @RequestParam double max) {

        return bookNamedDao.findByPriceRange(min, max);
    }
}
