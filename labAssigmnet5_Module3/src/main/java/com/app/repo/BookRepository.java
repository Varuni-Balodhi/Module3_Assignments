package com.app.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByAuthor(String author);

    List<Book> findByPriceLessThan(Double price);

    List<Book> findByCategory(String category);

    Boolean existsByIsbn(String isbn);

    void deleteByTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.title LIKE %:keyword%")
    List<Book> findByTitleContains(@Param("keyword") String keyword);

    @Query("SELECT b FROM Book b WHERE b.price BETWEEN ?1 AND ?2")
    List<Book> findByPriceBetween(Double minPrice, Double maxPrice);
}
