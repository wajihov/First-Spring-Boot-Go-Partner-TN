package com.example.personbook.domain.person;

import com.example.personbook.domain.book.BookMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonMapper {

    private final BookMapper bookMapper;

    public PersonMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public PersonDto toDto(Person person) {
        if (person == null) {
            return null;
        }
        return PersonDto.builder()
                .id(person.getId())
                .name(person.getName())
                .lastname(person.getLastname())
                .books(bookMapper.toDtos(person.getBooks()))
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
        return person;
    }

    public List<PersonDto> toDtos(List<Person> personList) {
        return personList.stream().map(this::toDto).collect(Collectors.toList());
    }


}
