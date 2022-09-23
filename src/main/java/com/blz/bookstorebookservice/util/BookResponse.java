package com.blz.bookstorebookservice.util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  
 * Purpose:Response to the book
 * @author: Pavan Kumar G V 
 * @version: 4.15.1.RELEASE
 * 
 **/ 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private String message;
    private int errorCode;
    private Object token;
}