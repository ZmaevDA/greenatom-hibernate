package ru.greenatom.zmaev.greenatomhibernate.domain.mapper;

import org.mapstruct.Mapper;
import ru.greenatom.zmaev.greenatomhibernate.domain.dto.PersonDTO;
import ru.greenatom.zmaev.greenatomhibernate.domain.entity.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonDTO toDto(Person p);
    Person toEntity(PersonDTO p);
}
