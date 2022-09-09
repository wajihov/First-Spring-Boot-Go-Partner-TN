package com.example.personbook.domain.person;

import com.example.personbook.core.exceptions.personbook.PersonBookException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto personDTO) {
        return new ResponseEntity<>(personService.createPerson(personDTO), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable("id") Long id) throws PersonBookException {
        return new ResponseEntity<>(personService.getPersonById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> getPersons() {
        return ResponseEntity.ok(personService.persons());
    }

    @GetMapping("/containing/{str}")
    public ResponseEntity<List<PersonDto>> getPersonsContainingStr(@PathVariable("str") String str) {
        return ResponseEntity.ok(personService.findPersonContains(str));
    }

    @GetMapping("/contains/{str}")
    public ResponseEntity<List<PersonDto>> getPersonsContainsStr(@PathVariable("str") String str) {
        return ResponseEntity.ok(personService.findPersonContaining(str));
    }

    @GetMapping("/like/{str}")
    public ResponseEntity<List<PersonDto>> getPersonsLike(@PathVariable("str") String str) {
        return ResponseEntity.ok(personService.findPersonLike(str));
    }

    @GetMapping("/findBy")
    public ResponseEntity<List<PersonDto>> getPersonsNameOrLastName(@RequestParam("name") String str1, @RequestParam("lastname") String str2) {
        return ResponseEntity.ok(personService.findPersonNameOrLastname(str1, str2));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDto> updatePerson(@RequestBody PersonDto personDTO, @PathVariable("id") Long id) throws PersonBookException {
        return new ResponseEntity<>(personService.modifyPerson(personDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> removePerson(@PathVariable("id") Long id) throws PersonBookException {
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
