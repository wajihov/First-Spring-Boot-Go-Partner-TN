package com.example.personbook.domain.person;


import com.example.personbook.core.exceptions.personbook.PersonBookException;

import java.util.List;

public interface PersonService {


    PersonDto createPerson(PersonDto personDTO);

    PersonDto modifyPerson(PersonDto personDTO, Long id) throws PersonBookException;

    PersonDto getPersonById(Long id) throws PersonBookException;

    List<PersonDto> persons();

    void deletePerson(Long id) throws PersonBookException;

    List<PersonDto> findPersonContaining(String str);

    List<PersonDto> findPersonContains(String str);

    List<PersonDto> findPersonLike(String str);

    List<PersonDto> findPersonNameOrLastname(String str1, String str2);


}
