package com.example.personbook.domain.book;

import com.example.personbook.domain.person.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookMapper {

    public BookDto toDto(Book book) {
        if (book == null) {
            return null;
        }
        //Person person = book.getPerson();

        return BookDto.builder()
                .id(book.getId())
                .nameBook(book.getNameBook())
                .author(book.getAuthor())
                .personId(book.getPerson().getId())
                .build();
    }

    public Book toEntity(Person person, BookDto bookDTO) {
        if (bookDTO == null) {
            return null;
        }
        Book book = Book.builder()
                .id(bookDTO.getId())
                .nameBook(bookDTO.getNameBook())
                .author(bookDTO.getAuthor())
                .person(person)
                .build();
        return book;
    }

    public List<BookDto> toDtos(List<Book> listBook) {
        return listBook.stream().map(this::toDto).collect(Collectors.toList());
    }
}
