package com.example.personbook.core.rest;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Codes {

    ERR_PERSON_NOT_FOUND("Person not found", HttpStatus.NOT_FOUND),
    ERR_PERSON_NOT_EXIST("Person not exist", HttpStatus.BAD_REQUEST),
    ERR_BOOK_NOT_FOUND("Book not found", HttpStatus.NOT_FOUND),
    ERR_BOOK_NOT_VALID("Book info not valid", HttpStatus.BAD_REQUEST);


    private String message;
    private HttpStatus httpStatus;

    Codes(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
