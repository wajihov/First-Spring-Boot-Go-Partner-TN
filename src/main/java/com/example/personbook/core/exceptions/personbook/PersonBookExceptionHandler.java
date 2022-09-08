package com.example.personbook.core.exceptions.personbook;

import com.example.personbook.core.rest.ServerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class PersonBookExceptionHandler {

    @ExceptionHandler(value = PersonBookException.class)
    public ResponseEntity<ServerResponse> exception(PersonBookException ex) {

        ServerResponse serverResponse = ServerResponse
                .builder()
                .timeStamp(LocalDateTime.now())
                .message(ex.getCodes().getMessage())
                .build();
        return new ResponseEntity<>(serverResponse, ex.getCodes().getHttpStatus());
    }

}
