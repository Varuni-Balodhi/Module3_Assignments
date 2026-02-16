package com.app.jdbc.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.entity.Book;

public class BookRowMapper implements RowMapper<Book> {
	@Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {

        Book b = new Book();
        b.setTitle(rs.getString("title"));
        b.setAuthor(rs.getString("author"));
        b.setPrice(rs.getDouble("price"));
        b.setIsbn(rs.getString("isbn"));

        return b;
    }
}
