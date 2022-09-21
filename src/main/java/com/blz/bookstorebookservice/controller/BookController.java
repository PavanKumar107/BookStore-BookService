package com.blz.bookstorebookservice.controller;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blz.bookstorebookservice.dto.BookDto;
import com.blz.bookstorebookservice.model.BookModel;
import com.blz.bookstorebookservice.service.IBookService;
import com.blz.bookstorebookservice.util.Response;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/bookservice")
public class BookController {
	
	@Autowired
	IBookService bookService;
	
	@PutMapping("/add")
	public ResponseEntity<Response>  addBook(@RequestBody BookDto bookDto,@RequestHeader String token) {
		BookModel bookModel = bookService.addBook(bookDto,token);
		Response response = new Response("Book added sucessfully", 200, bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	@PutMapping("/update/{bookId}")
	public ResponseEntity<Response> updateNote(@RequestBody BookDto bookDto,@PathVariable Long bookId,@RequestHeader String token) {
		BookModel bookModel = bookService.updateNote(bookDto,bookId,token);
		Response response = new Response("Book updated sucessfully", 200, bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/read")
	public ResponseEntity<Response> fetchBooks(@RequestHeader String token) {
		List<BookModel> bookModel = bookService.fetchBooks(token);
		Response response = new Response("Fetching books sucessfully", 200, bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/addbooklogo/{bookId}")
	public ResponseEntity<Response> addBookLogo(@PathVariable Long bookId,@RequestParam MultipartFile bookLogo,@RequestHeader String token) throws IOException {
		Response bookModel = bookService.addBookLogo(bookId,bookLogo,token);
		Response response = new Response("Book logo uploaded sucessfully ", 200, bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/readbyid/{bookId}")
	public ResponseEntity<Response> fetchBookById(@PathVariable Long bookId,@RequestHeader String token) {
		Optional<BookModel> bookModel = bookService.fetchBookById(bookId, token);
		Response response = new Response("Fetching book by id successfully", 200, bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{bookId}")
	public ResponseEntity<Response> deletebook(@PathVariable Long bookId,@RequestHeader String token) {
		BookModel bookModel = bookService.deletebook(bookId,token);
		Response response = new Response("Book deleted sucessfully", 200, bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	@PutMapping("/changebookquantity/{bookId}")
	public ResponseEntity<Response> changeBookQuanity(@RequestHeader String token,@PathVariable Long bookId,@RequestParam Long quantity) {
		BookModel bookModel = bookService.changeBookQuanity(token,bookId,quantity);
		Response response = new Response("Books quantity changed sucessfully", 200, bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	@PutMapping("/changebookprice/{bookId}")
	public ResponseEntity<Response> changeBookPrice(@RequestHeader String token,@PathVariable Long bookId,@RequestParam Long price) {
		BookModel bookModel = bookService.changeBookPrice(token,bookId,price);
		Response response = new Response("Book price changed sucessfully", 200, bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	@GetMapping("/validatebookid/{bookId}")
	public ResponseEntity<Response> validateBook(@PathVariable Long bookId) {
		Boolean bookModel = bookService.validateBook(bookId);
		Response response = new Response("BookId verified sucessfully", 200, bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
