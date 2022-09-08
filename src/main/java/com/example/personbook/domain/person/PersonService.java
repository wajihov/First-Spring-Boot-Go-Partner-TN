package com.example.personbook.domain.person;


import java.util.List;

public interface PersonService {


    PersonDto createPerson(PersonDto personDTO);

    PersonDto modifyPerson(PersonDto personDTO, Long id);

    PersonDto getPersonById(Long id);

    List<PersonDto> persons();

    void deletePerson(Long id);

    List<PersonDto> findPersonContaining(String str);

    List<PersonDto> findPersonContains(String str);

    List<PersonDto> findPersonLike(String str);

    List<PersonDto> findPersonNameOrLastname(String str1, String str2);


}
