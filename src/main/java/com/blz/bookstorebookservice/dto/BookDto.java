package com.blz.bookstorebookservice.dto;

import lombok.Data;

/**
 *  
 * Purpose:DTO for the book service
 * 
 * @author: Pavan Kumar G V
 * @version: 4.15.1.RELEASE
 * 
 **/ 
@Data
public class BookDto {
	private String bookName;
	private String bookAuthor;
	private String bookDescription;
	private long bookPrice;
	private long bookQuantity;
}
