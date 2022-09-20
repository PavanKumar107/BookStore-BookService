package com.blz.bookstorebookservice.service;

import java.util.List;
import java.util.Optional;

import com.blz.bookstorebookservice.dto.BookDto;
import com.blz.bookstorebookservice.model.BookModel;

public interface IBookService {

	BookModel addBook(BookDto bookDto, String token);

	List<BookModel> fetchBooks(String token);

	Optional<BookModel> fetchBookById(Long bookId, String token);

	BookModel deletebook(Long bookId, String token);

	BookModel changeBookQuanity(String token, Long bookId, Long quantity);

	BookModel updateNote(BookDto bookDto, Long bookId, String token);

	BookModel changeBookPrice(String token, Long bookId, Long price);

}
