package com.blz.bookstorebookservice.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.blz.bookstorebookservice.dto.BookDto;
import com.blz.bookstorebookservice.exception.CustomNotFoundException;
import com.blz.bookstorebookservice.model.BookModel;
import com.blz.bookstorebookservice.repository.BookRepository;
import com.blz.bookstorebookservice.util.BookResponse;
import com.blz.bookstorebookservice.util.TokenUtil;


@Service
public class BookService implements IBookService {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	TokenUtil tokenUtil;

	@Autowired
	RestTemplate restTemplate;


	@Override
	public BookModel addBook(BookDto bookDto,String token) {
		boolean isUserPresent = restTemplate.getForObject("http://BookStore-UserService:8068/userservice/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			BookModel model = new BookModel(bookDto);
			bookRepository.save(model);
			return model;
		}
		throw new CustomNotFoundException(400,"Invalid token");
	}

	@Override
	public BookModel updateNote(BookDto bookDto,Long bookId, String token) {
		boolean isUserPresent = restTemplate.getForObject("http://BookStore-UserService:8068/userservice/validateuser/"  + token, Boolean.class);
		if (isUserPresent) {
			Optional<BookModel> isBookPresent = bookRepository.findById(bookId);
			if(isBookPresent.isPresent()) {
				isBookPresent.get().setBookName(bookDto.getBookName());
				isBookPresent.get().setBookDescription(bookDto.getBookDescription());
				isBookPresent.get().setBookAuthor(bookDto.getBookAuthor());
				isBookPresent.get().setBookQuantity(bookDto.getBookQuantity());
				isBookPresent.get().setBookPrice(bookDto.getBookPrice());
				bookRepository.save(isBookPresent.get());
				return isBookPresent.get();
			}
		}
		throw new CustomNotFoundException(400,"Invalid token");
	}

	@Override
	public List<BookModel> fetchBooks(String token) {
		boolean isUserPresent = restTemplate.getForObject("http://BookStore-UserService:8068/userservice/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			List<BookModel> readAllBooks = bookRepository.findAll();
			if(readAllBooks.size()>0) {
				return readAllBooks;	
			}else {
				throw new CustomNotFoundException(400,"Books not present");
			}
		}
		throw new CustomNotFoundException(400,"Invalid token");
	}

	@Override
	public Optional<BookModel> fetchBookById(Long bookId,String token) {
		boolean isUserPresent = restTemplate.getForObject("http://BookStore-UserService:8068/userservice/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			return bookRepository.findById(bookId);
		}
		throw new CustomNotFoundException(400,"Invalid token");
	}

	@Override
	public BookModel deletebook(Long bookId,String token) {
		boolean isUserPresent = restTemplate.getForObject("http://BookStore-UserService:8068/userservice/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<BookModel> isBookPresent = bookRepository.findById(bookId);
			if(isBookPresent.isPresent()) {
				bookRepository.delete(isBookPresent.get());
				return isBookPresent.get();
			}
			throw new CustomNotFoundException(400,"Book not present");
		}
		throw new CustomNotFoundException(400,"Invalid token");
	}

	@Override
	public BookResponse addBookLogo(Long bookId,MultipartFile bookLogo,String token) throws IOException {
		boolean isUserPresent = restTemplate.getForObject("http://BookStore-UserService:8068/userservice/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<BookModel> isIdPresent = bookRepository.findById(bookId);
			if(isIdPresent.isPresent()) {
				isIdPresent.get().setBookLogo(String.valueOf(bookLogo.getBytes()));
				bookRepository.save(isIdPresent.get());
				return new BookResponse("Success", 200, isIdPresent.get());
			}
			throw new CustomNotFoundException(400, "Book not found");
		}
		throw new CustomNotFoundException(400,"Invalid token");
	}

	@Override
	public BookModel changeBookQuanity(String token,Long bookId,Long quantity) {
		boolean isUserPresent = restTemplate.getForObject("http://BookStore-UserService:8068/userservice/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<BookModel> isBookPresent = bookRepository.findById(bookId);
			if(isBookPresent.isPresent()) {
				isBookPresent.get().setBookQuantity(quantity);
				bookRepository.save(isBookPresent.get());
				return isBookPresent.get();
			}
		}
		throw new CustomNotFoundException(400,"Invalid token");
	}

	@Override
	public BookModel changeBookPrice(String token,Long bookId,Long price) {
		boolean isUserPresent = restTemplate.getForObject("http://BookStore-UserService:8068/userservice/validateuser/" + token, Boolean.class);
		if (isUserPresent) {
			Optional<BookModel> isBookPresent = bookRepository.findById(bookId);
			if(isBookPresent.isPresent()) {
				isBookPresent.get().setBookPrice(price);
				bookRepository.save(isBookPresent.get());
				return isBookPresent.get();
			}
		}
		throw new CustomNotFoundException(400,"Invalid token");
	}

	@Override
	public BookModel validateBook(Long bookId) {
		Optional<BookModel> isBookPresent = bookRepository.findById(bookId);
		if (isBookPresent.isPresent()) {
			return isBookPresent.get();
		}
		throw new CustomNotFoundException(400,"user not found");

	}

	@Override
	public BookResponse addingToCart(Long bookId, Long bookQuantity) {
		Optional<BookModel> isBookPresent = bookRepository.findById(bookId);
		if(isBookPresent.isPresent()) {
			isBookPresent.get().setBookQuantity(isBookPresent.get().getBookQuantity()- bookQuantity);
			bookRepository.save(isBookPresent.get());
			return new BookResponse ("book quantity updated sucessfully after adding book to cart", 200,isBookPresent.get());
		}
		throw new CustomNotFoundException(400, "Book not found");
	}

	@Override
	public BookResponse removingFromCart(Long bookId,Long bookQuantity ) {
		Optional<BookModel> isBookPresent = bookRepository.findById(bookId);
		if(isBookPresent.isPresent()) {
			isBookPresent.get().setBookQuantity(isBookPresent.get().getBookQuantity() + bookQuantity);
			bookRepository.save(isBookPresent.get());
			return new BookResponse ("book quantity updated sucessfully after removing book from cart", 200,isBookPresent.get());
		}
		throw new CustomNotFoundException(400, "Book not found");
	}

}
