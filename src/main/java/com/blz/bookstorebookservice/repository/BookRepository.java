package com.blz.bookstorebookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blz.bookstorebookservice.model.BookModel;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long>{

}
