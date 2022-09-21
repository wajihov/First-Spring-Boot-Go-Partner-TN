package com.example.personbook.domain.person;

import com.example.personbook.core.exceptions.personbook.PersonBookException;
import com.example.personbook.core.rest.Codes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }


    public PersonDto createPerson(PersonDto personDTO) {
        Person person = personMapper.toEntity(personDTO);
        person = personRepository.save(person);
        return personMapper.toDto(person);
    }

    public PersonDto modifyPerson(PersonDto personDTO, Long id) throws PersonBookException {
        Person personCurrent = personMapper.toEntity(getPersonById(id));
        Person personModify = personMapper.toEntity(personDTO);
        if (personModify.getName() != null) {
            personCurrent.setName(personModify.getName());
        }
        if (personModify.getLastname() != null) {
            personCurrent.setLastname(personModify.getLastname());
        }
        if (personModify.getBooks() != null) {
            personCurrent.setBooks(personModify.getBooks());
        }
        Person savedPerson = personRepository.save(personCurrent);
        return personMapper.toDto(savedPerson);
    }

    private Person findPersonById(Long id) throws PersonBookException {
        if (id == null) {
            throw new PersonBookException(Codes.ERR_PERSON_NOT_EXIST);
        }
        return personRepository.findById(id).orElseThrow(() -> new PersonBookException(Codes.ERR_PERSON_NOT_FOUND));
    }

    public PersonDto getPersonById(Long id) throws PersonBookException {
        if (id == null) {
            log.error("l'id est null");
            throw new PersonBookException(Codes.ERR_PERSON_NOT_EXIST);
        }
        Person person = findPersonById(id);
        return personMapper.toDto(person);
    }

    public List<PersonDto> persons() {
        List<Person> personList = personRepository.findAll();
        return personMapper.toDtos(personList);
    }

    public void deletePerson(Long id) throws PersonBookException {
        Person person = findPersonById(id);
        personRepository.delete(person);
    }

    public List<PersonDto> findPersonContaining(String str) {
        List<Person> personList = personRepository.findByNameContaining(str);
        return personMapper.toDtos(personList);
    }

    public List<PersonDto> findPersonContains(String str) {
        List<Person> personList = personRepository.findByNameContains(str);
        return personMapper.toDtos(personList);
    }

    public List<PersonDto> findPersonLike(String str) {
        List<Person> personList = personRepository.findByNameLike(str);
        return personMapper.toDtos(personList);
    }

    public List<PersonDto> findPersonNameOrLastname(String str1, String str2) {
        List<Person> personList = personRepository.findByNameOrLastname(str1, str2);
        return personMapper.toDtos(personList);
    }
}
