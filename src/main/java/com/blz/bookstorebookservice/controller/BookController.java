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
import com.blz.bookstorebookservice.util.BookResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * Purpose: Book Controller to process User Data APIs.
 * @version: 4.15.1.RELEASE
 * @author: Pavan Kumar G V
 *   
 */

@RestController
@RequestMapping("/bookservice")
public class BookController {

	@Autowired
	IBookService bookService;

	/**
	 * Purpose:To add book
	 * @Param: token , bookDto
	 * 
	 */
	@PostMapping("/add")
	public ResponseEntity<BookResponse>  addBook(@RequestBody BookDto bookDto,@RequestHeader String token) {
		BookModel bookModel = bookService.addBook(bookDto,token);
		BookResponse response = new BookResponse("Book added sucessfully", 200, bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}

	/**
	 * Purpose:To update book
	 * @Param: token , bookDto,bookId
	 * 
	 */
	@PutMapping("/update/{bookId}")
	public ResponseEntity<BookResponse> updateNote(@RequestBody BookDto bookDto,@PathVariable Long bookId,@RequestHeader String token) {
		BookModel bookModel = bookService.updateNote(bookDto,bookId,token);
		BookResponse response = new BookResponse("Book updated sucessfully", 200, bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Purpose: to fetch all books
	 * @Param: token
	 * 
	 */
	@GetMapping("/read")
	public ResponseEntity<BookResponse> fetchBooks(@RequestHeader String token) {
		List<BookModel> bookModel = bookService.fetchBooks(token);
		BookResponse response = new BookResponse("Fetching books sucessfully", 200, bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Purpose: to add logo to the book
	 * @Param: token,bookId,bookLogo
	 * 
	 */
	@PostMapping("/addbooklogo/{bookId}")
	public ResponseEntity<BookResponse> addBookLogo(@PathVariable Long bookId,@RequestParam MultipartFile bookLogo,@RequestHeader String token) throws IOException {
		BookResponse bookModel = bookService.addBookLogo(bookId,bookLogo,token);
		BookResponse response = new BookResponse("Book logo uploaded sucessfully ", 200, bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Purpose: to fetch all books by id
	 * @Param: token,bookId
	 * 
	 */
	@GetMapping("/readbyid/{bookId}")
	public ResponseEntity<BookResponse> fetchBookById(@PathVariable Long bookId,@RequestHeader String token) {
		Optional<BookModel> bookModel = bookService.fetchBookById(bookId, token);
		BookResponse response = new BookResponse("Fetching book by id successfully", 200, bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * Purpose: to delete book
	 * @Param: token,bookId
	 * 
	 */
	@DeleteMapping("/delete/{bookId}")
	public ResponseEntity<BookResponse> deletebook(@PathVariable Long bookId,@RequestHeader String token) {
		BookModel bookModel = bookService.deletebook(bookId,token);
		BookResponse response = new BookResponse("Book deleted sucessfully", 200, bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}

	/**
	 * Purpose: to change quantity of the books
	 * @Param: token,bookId,quantity
	 * 
	 */
	@PutMapping("/changebookquantity/{bookId}")
	public ResponseEntity<BookResponse> changeBookQuanity(@RequestHeader String token,@PathVariable Long bookId,@RequestParam Long quantity) {
		BookModel bookModel = bookService.changeBookQuanity(token,bookId,quantity);
		BookResponse response = new BookResponse("Books quantity changed sucessfully", 200, bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}

	/**
	 * Purpose: to change price of the book
	 * @Param: token,bookId,price
	 * 
	 */
	@PutMapping("/changebookprice/{bookId}")
	public ResponseEntity<BookResponse> changeBookPrice(@RequestHeader String token,@PathVariable Long bookId,@RequestParam Long price) {
		BookModel bookModel = bookService.changeBookPrice(token,bookId,price);
		BookResponse response = new BookResponse("Book price changed sucessfully", 200, bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}

	/**
	 * Purpose: to validate bookId
	 * @Param: bookId
	 * 
	 */
	@GetMapping("/validatebookid/{bookId}")
	public BookResponse validateBook(@PathVariable Long bookId) {
		return bookService.validateBook(bookId);
	}

	/**
	 * Purpose: book quantity after adding book to the cart
	 * @Param: bookId,bookQuantity
	 * 
	 */
	@GetMapping("/addingtocart/{bookId}/{bookQuantity}")
	public BookResponse addingToCart(@PathVariable Long bookId,@PathVariable Long bookQuantity) {
		return bookService.addingToCart(bookId, bookQuantity);
	}

	/**
	 * Purpose: book quantity after removing book from cart
	 * @Param: bookId,bookQuantity
	 * 
	 */
	@GetMapping("/removefromcart/{bookId}/{bookQuantity}")
	public BookResponse removingFromCart(@PathVariable Long bookId,@PathVariable Long bookQuantity ) {
		return bookService.removingFromCart(bookId, bookQuantity);
	}
	
	/**
	 * Purpose: to fetch all books by book name
	 * @Param: token,bookname
	 * 
	 */
	@GetMapping("/fetchbybookname/{bookName}")
	public ResponseEntity<BookResponse> fetchByBookName(@PathVariable String bookName,@RequestHeader String token) {
		List<BookModel> bookModel = bookService.fetchByBookName(bookName,token);
		BookResponse response = new BookResponse("fetching books by book name", 200, bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	/**
	 * Purpose: to fetch all books by book author
	 * @Param: token,bookauthor
	 * 
	 */
	@GetMapping("/fetchbybookauthor/{bookAuthor}")
	public ResponseEntity<BookResponse> fetchByBookAuthor(@PathVariable String bookAuthor,@RequestHeader String token) {
		List<BookModel> bookModel = bookService.fetchByBookAuthor(bookAuthor,token);
		BookResponse response = new BookResponse("fetching books by book Author", 200, bookModel);
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
}
