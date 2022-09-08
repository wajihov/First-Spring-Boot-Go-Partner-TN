package com.example.personbook.domain.person;

import com.example.personbook.domain.book.BookMapperImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonMapperImpl {

    BookMapperImpl bookMapper = new BookMapperImpl();

    public PersonDto toDto(Person person) {
        if (person == null) {
            return null;
        }
        return PersonDto.builder()
                .id(person.getId())
                .name(person.getName())
                .lastname(person.getLastname())
                .books(bookMapper.toDtos(person.getBooks()))
                //.books(person.getBooks().stream().map(BookDto::fromEntity).collect(Collectors.toList()))
                .build();
    }

    public Person toEntity(PersonDto personDTO) {
        if (personDTO == null) {
            return null;
        }
        Person person = Person
                .builder()
                .id(personDTO.getId())
                .name(personDTO.getName())
                .lastname(personDTO.getLastname())
                .build();

        //book.setPerson(PersonDto.toEntity(bookDTO.getPerson()));
        return person;
    }

    public List<PersonDto> toDtos(List<Person> personList) {
        return personList.stream().map(this::toDto).collect(Collectors.toList());
    }


}
