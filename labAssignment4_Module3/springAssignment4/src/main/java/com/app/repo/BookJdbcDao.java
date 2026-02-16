package com.app.repo;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.entity.Book;
import com.app.jdbc.rowmapper.BookRowMapper;

@Repository
public class BookJdbcDao {
	 private final JdbcTemplate jdbc;

	    public BookJdbcDao(JdbcTemplate jdbc) {
	        this.jdbc = jdbc;
	    }

	    public int save(Book b) {
	    	return jdbc.update(
	    			"INSERT INTO books(title, author, price, isbn) VALUES (?, ?, ?, ?)\r\n",
	    				b.getTitle(),
	                b.getAuthor(),
	                b.getPrice(),
	                b.getIsbn()
	        );
	    }

	    public Book findById(int id) { 

	        return jdbc.queryForObject("SELECT * FROM books WHERE id = ?",
	                new BookRowMapper(),id
	        );
	    }

	    public List<Book> findAll() {

	        return jdbc.query("SELECT * FROM books",
	                new BookRowMapper()
	        );
	    }

	    public int update(Book b) { 
	    	return jdbc.update("UPDATE books_jdbc SET title = ?, author = ?, price = ?, isbn = ? WHERE id = ?",
	                b.getTitle(),
	                b.getAuthor(),
	                b.getPrice(),
	                b.getIsbn()
	        );
	    }

	    public int delete(int id) { 
	    	return jdbc.update("DELETE FROM books_jdbc WHERE id = ?", id);
 }
}
