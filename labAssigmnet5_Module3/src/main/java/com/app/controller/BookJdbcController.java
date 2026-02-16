package com.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Book;
import com.app.service.BookService;

@RestController
@RequestMapping("/api/jdbc/books")
public class BookJdbcController {
	private final BookService bookService;

	public BookJdbcController(BookService bookService) {
		super();
		this.bookService = bookService;
	}
	
	@GetMapping
	public List<Book> getAll() {
        return bookService.getAllBooks();
    }
	
	@GetMapping("/{id}")
	public Book getBookById(@PathVariable Integer id) {
        return bookService.getBook(id);
    }
	
	@GetMapping("/author/{author}")
	public List<Book> getBookByAuthor(@PathVariable String author) {
        return bookService.getByAuthor(author);
    }
	
	@PostMapping
	public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }
	
	@PutMapping("/{id}")
	public Book updateBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }
	
	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable Integer id) {
		bookService.deleteBook(id);
	}
}
