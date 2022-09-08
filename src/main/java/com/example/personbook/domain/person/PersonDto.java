package com.example.personbook.domain.person;

import com.example.personbook.domain.book.BookDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDto {

    private Long id;
    private String name;
    private String lastname;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<BookDto> books;

}