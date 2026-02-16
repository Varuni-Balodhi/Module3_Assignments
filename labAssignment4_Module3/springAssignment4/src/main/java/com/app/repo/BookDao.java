package com.app.repo;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.entity.Book;
import com.app.jdbc.rowmapper.BookRowMapper;

@Repository
public class BookDao {

    private final NamedParameterJdbcTemplate named;

    public BookDao(NamedParameterJdbcTemplate named) {
        this.named = named;
    }

    // FIND BY AUTHOR
    public List<Book> findByAuthor(String author) {
        String sql = "SELECT * FROM books_jdbc WHERE author = :author";

        Map<String, Object> params = Map.of(
                "author", author
        );

        return named.query(sql, params, new BookRowMapper());
    }
    
    public List<Book> findByPriceRange(double min, double max) {
        String sql = "SELECT * FROM books_jdbc WHERE price BETWEEN :min AND :max";

        Map<String, Object> params = Map.of(
                "min", min,
                "max", max);

        return named.query(sql, params, new BookRowMapper());
    }
}
