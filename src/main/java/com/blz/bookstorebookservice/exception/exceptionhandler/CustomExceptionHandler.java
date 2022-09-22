package com.blz.bookstorebookservice.exception.exceptionhandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.blz.bookstorebookservice.exception.CustomNotFoundException;
import com.blz.bookstorebookservice.util.BookResponse;

@ControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(CustomNotFoundException.class)
	public ResponseEntity<BookResponse> handleHiringException(CustomNotFoundException he){
		BookResponse response=new BookResponse();
		response.setErrorCode(400);
		response.setMessage(he.getMessage());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}