package ru.greenatom.zmaev.greenatomhibernate.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.greenatom.zmaev.greenatomhibernate.dao.PersonDAO;
import ru.greenatom.zmaev.greenatomhibernate.dao.PersonDAOHQLImpl;
import ru.greenatom.zmaev.greenatomhibernate.domain.dto.PersonDTO;
import ru.greenatom.zmaev.greenatomhibernate.domain.entity.Person;
import ru.greenatom.zmaev.greenatomhibernate.domain.entity.PersonRequest;
import ru.greenatom.zmaev.greenatomhibernate.domain.mapper.PersonMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceHQL {

    private PersonDAO personDAO;
    private PersonMapper personMapper;

    @Autowired
    public PersonServiceHQL(PersonDAOHQLImpl personDAO, PersonMapper personMapper) {
        this.personDAO = personDAO;
        this.personMapper = personMapper;
    }

    public List<Person> findAll(PersonRequest personRequest) {
        return personDAO.findAll(personRequest);
    }

    public PersonDTO findPersonById(Long id) {
        return personMapper.toDto(personDAO.findById(id));
    }

    public void save(PersonDTO personDTO) {
        personDAO.create(personMapper.toEntity(personDTO));
    }

    public void update(Long id, PersonDTO personDTO) {
        personDAO.update(id, personMapper.toEntity(personDTO));
    }

    public void delete(Long id) {
        personDAO.delete(id);
    }

}
