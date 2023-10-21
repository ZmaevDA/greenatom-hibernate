package ru.greenatom.zmaev.greenatomhibernate.dao;

import ru.greenatom.zmaev.greenatomhibernate.domain.entity.Person;
import ru.greenatom.zmaev.greenatomhibernate.domain.entity.PersonRequest;

import java.util.List;

public interface PersonDAO {

    void create(Person person);

    void update(Long id, Person person);

    void delete(Long id);

    Person findById(Long id);

    List<Person> findAll(PersonRequest personRequest);
}