package com.app.repo;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.entity.Book;

@Repository
public class BookJdbcDao {
	private final JdbcTemplate jdbc;

	public BookJdbcDao(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	public int save(Book b) {
		String sql = "INSERT INTO books(title, author, price, isbn) VALUES (?, ?, ?, ?)";
		return jdbc.update(sql, b.getTitle(), b.getAuthor(), b.getPrice(), b.getIsbn());
	}

	public Book findById(int id) {
		String sql = "SELECT * FROM books WHERE id = ?";
		Book book = jdbc.queryForObject(sql, new BeanPropertyRowMapper<>(), id);
		return book;
	}

	public List<Book> findAll() {
		String sql = "SELECT * FROM books";
		return jdbc.query(sql, new BeanPropertyRowMapper<>(Book.class));
	}

	public int update(Book b) {
		String sql = "UPDATE books SET title=?, author=?, price=?, isbn=? WHERE id=?";
		return jdbc.update(sql, b.getTitle(), b.getAuthor(), b.getPrice(), b.getIsbn(), b.getId());
	}

	public int delete(int id) {
		String sql = "DELETE FROM books WHERE id=?";
		return jdbc.update(sql, id);
	}
}
