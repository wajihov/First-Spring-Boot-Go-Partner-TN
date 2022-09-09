package com.example.personbook.domain.book;

import com.example.personbook.core.exceptions.personbook.PersonBookException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) throws PersonBookException {
        BookDto savedBook = bookService.createBook(bookDto);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long id) throws PersonBookException {
        BookDto bookDTO = bookService.findBookById(id);
        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getBooks() {
        List<BookDto> bookDtos = bookService.getBooks();
        return ResponseEntity.ok(bookDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto, @PathVariable("id") Long idPerson) throws PersonBookException {
        BookDto bookDtoUpdate = bookService.updateBook(bookDto, idPerson);
        return new ResponseEntity<>(bookDtoUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") Long id) throws PersonBookException {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
