package com.blz.bookstorebookservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blz.bookstorebookservice.model.BookModel;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long>{

	List<BookModel> findByBookName(String bookName);

	List<BookModel> findByBookAuthor(String bookAuthor);

}
