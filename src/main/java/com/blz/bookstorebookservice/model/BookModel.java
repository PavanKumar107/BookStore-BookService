package com.blz.bookstorebookservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

import com.blz.bookstorebookservice.dto.BookDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class BookModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long bookId;
	private String bookName;
	private String bookAuthor;
	private String bookDescription;
	@Column(length = 1000)
	private String bookLogo;
	private long bookPrice;
	private long bookQuantity;
	
	
	public BookModel(BookDto bookDto) {
		this.bookName = bookDto.getBookName();
		this.bookAuthor = bookDto.getBookAuthor();
		this.bookDescription = bookDto.getBookDescription();
		this.bookPrice = bookDto.getBookPrice();
		this.bookQuantity = bookDto.getBookQuantity();
	}
}
