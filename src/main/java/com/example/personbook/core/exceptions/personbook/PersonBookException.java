package com.example.personbook.core.exceptions.personbook;

import com.example.personbook.core.rest.Codes;
import lombok.Getter;

@Getter
public class PersonBookException extends Exception {

    private static final Long serialVersionUID = 1L;

    private final Codes codes;

    public PersonBookException(Codes codes) {
        super(codes.getMessage());
        this.codes = codes;
    }


}
