package ru.greenatom.zmaev.greenatomhibernate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.greenatom.zmaev.greenatomhibernate.domain.dto.PersonDTO;
import ru.greenatom.zmaev.greenatomhibernate.domain.entity.Person;
import ru.greenatom.zmaev.greenatomhibernate.domain.entity.PersonRequest;
import ru.greenatom.zmaev.greenatomhibernate.sevice.PersonServiceCrApi;

import java.util.List;

@RestController
@RequestMapping(value = "/api/personCrApi")
public class PersonControllerCrApi {

    private final PersonServiceCrApi personService;

    public PersonControllerCrApi(PersonServiceCrApi personService) {
        this.personService = personService;
    }

    @PostMapping("/get")
    public ResponseEntity<List<Person>> getPersons(@RequestBody PersonRequest personRequest) {
        return new ResponseEntity<>(personService.findAll(personRequest), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable Long id) {
        PersonDTO personDTO = personService.findPersonById(id);
        if (personDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(personDTO, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<PersonDTO> addPerson(@RequestBody PersonDTO personDTO) {
        try {
            personService.save(personDTO);
            return new ResponseEntity<>(personDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<String> updatePerson(@RequestBody PersonDTO personDTO, @PathVariable Long id) {
        PersonDTO p = personService.findPersonById(id);
        if (p == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            personService.update(id, personDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        try {
            personService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
