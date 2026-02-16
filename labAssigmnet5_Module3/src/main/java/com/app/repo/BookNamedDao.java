package com.app.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.app.entity.Book;

public class BookNamedDao {
	private final NamedParameterJdbcTemplate named;

	public BookNamedDao(NamedParameterJdbcTemplate named) {
		super();
		this.named = named;
	}

	public List<Book> findByAuthor(String author) {
		String sql = "SELECT * FROM books_jdbc WHERE author = :author";
		Map<String, Object> params = new HashMap<>();
		params.put("author", author);
		return named.query(sql, params, new BeanPropertyRowMapper<>(Book.class));
	}

	public List<Book> findByPriceRange(double min, double max) {
		String sql = "SELECT * FROM books_jdbc WHERE price BETWEEN :min AND :max";
		Map<String, Object> params = new HashMap<>();
		params.put("min", min);
		params.put("max", max);
		return named.query(sql, params, new BeanPropertyRowMapper<>(Book.class));
	}
}
