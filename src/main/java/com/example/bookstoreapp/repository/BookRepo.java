package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepo extends JpaRepository<Book,Integer> {

    @Query("SELECT b FROM Book b WHERE b.bookName=:n")
    public Book getBookByBookName(@Param("n") String bookName);

}
