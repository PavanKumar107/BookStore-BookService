package com.blz.bookstorebookservice.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.blz.bookstorebookservice.dto.BookDto;
import com.blz.bookstorebookservice.model.BookModel;
import com.blz.bookstorebookservice.util.BookResponse;

/**
 *  
 * Purpose:Book Service Interface
 * @author: Pavan Kumar G V 
 * @version: 4.15.1.RELEASE
 * 
 **/


public interface IBookService {

	BookModel addBook(BookDto bookDto, String token);

	List<BookModel> fetchBooks(String token);

	Optional<BookModel> fetchBookById(Long bookId, String token);

	BookModel deletebook(Long bookId, String token);

	BookModel changeBookQuanity(String token, Long bookId, Long quantity);

	BookModel updateNote(BookDto bookDto, Long bookId, String token);

	BookModel changeBookPrice(String token, Long bookId, Long price);

	BookResponse validateBook(Long bookId);

	BookResponse addBookLogo(Long bookId, MultipartFile bookLogo, String token) throws IOException;

	BookResponse addingToCart(Long bookId, Long bookQuantity);

	BookResponse removingFromCart(Long bookId, Long bookQuantity);

	List<BookModel>  fetchByBookName(String bookName, String token);
	
	List<BookModel>  fetchByBookAuthor(String bookAuthor, String token);

//	Boolean validateBook(String token);

}
