package com.example.personbook.domain.person;

import com.example.personbook.domain.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByNameContaining(String str);

    List<Person> findByNameContains(String str);

    List<Person> findByNameLike(String str);

    List<Person> findByNameOrLastname(String str1,String str2);

}
