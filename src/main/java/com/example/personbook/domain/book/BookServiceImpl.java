package com.example.personbook.domain.book;

import com.example.personbook.core.exceptions.personbook.PersonBookException;
import com.example.personbook.core.rest.Codes;
import com.example.personbook.domain.person.Person;
import com.example.personbook.domain.person.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class BookServiceImpl {

    private final BookRepository bookRepository;
    private final PersonRepository personRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, PersonRepository personRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.personRepository = personRepository;
        this.bookMapper = bookMapper;

    }

    public BookDto createBook(BookDto bookDTO) throws PersonBookException {

        Long idPerson = bookDTO.getPersonId();
        /*if(idPerson==null){
            throw new PersonNotFoundException("L'id est obligatoire ");
        }*/
        Person person = personRepository.findById(idPerson).orElseThrow(() -> new PersonBookException(Codes.ERR_PERSON_NOT_FOUND));
        Book book = bookMapper.toEntity(person, bookDTO);
        book = bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    public BookDto findBookById(Long id) throws PersonBookException {
        Book book = getBookById(id);
        return bookMapper.toDto(book);
    }

    private Book getBookById(Long id) throws PersonBookException {
        if (id == null) {
            throw new PersonBookException(Codes.ERR_BOOK_NOT_FOUND);
        }
        return bookRepository.findById(id).orElseThrow(() -> new PersonBookException(Codes.ERR_PERSON_NOT_FOUND));
    }

    public List<BookDto> getBooks() {
        List<Book> books = bookRepository.findAll();
        return bookMapper.toDtos(books);
    }

    public BookDto updateBook(BookDto bookDTO, Long id) throws PersonBookException {
        Long idPerson = bookDTO.getPersonId();
        if (idPerson == null) {
            throw new PersonBookException(Codes.ERR_BOOK_NOT_VALID);
        }
        Person person = personRepository.findById(idPerson).orElseThrow(() -> new PersonBookException(Codes.ERR_PERSON_NOT_FOUND));
        Book bookModify = bookMapper.toEntity(person, bookDTO);
        Book bookCurrent = bookMapper.toEntity(person, findBookById(id));
        if (!StringUtils.hasLength(bookModify.getNameBook())) {
            bookCurrent.setNameBook(bookModify.getNameBook());
        }
        if (!StringUtils.hasLength(bookModify.getAuthor())) {
            bookCurrent.setAuthor(bookModify.getAuthor());
        }
        if (bookModify.getPerson() != null) {
            bookCurrent.setPerson(bookModify.getPerson());
        }
        bookCurrent = bookRepository.save(bookCurrent);
        return bookMapper.toDto(bookCurrent);
    }

    public void deleteBook(Long id) throws PersonBookException {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }

}
