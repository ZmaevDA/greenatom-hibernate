package ru.greenatom.zmaev.greenatomhibernate.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.stereotype.Component;
import ru.greenatom.zmaev.greenatomhibernate.domain.entity.Person;
import ru.greenatom.zmaev.greenatomhibernate.domain.entity.PersonRequest;
import ru.greenatom.zmaev.greenatomhibernate.util.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.Map;

@Component
public class PersonDAOCrApiImpl implements PersonDAO {

    @Override
    public void create(Person person) {

    }

    @Override
    public void update(Long id, Person person) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Person findById(Long id) {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);
            Root<Person> root = criteriaQuery.from(Person.class);
            criteriaQuery.select(root).where(builder.equal(root.get("id"), id));
            return session.createQuery(criteriaQuery).getSingleResult();
        }
    }

    @Override
    public List<Person> findAll(PersonRequest personRequest) {
        Map<String, Object> filters = personRequest.getCriteria();
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);
            Root<Person> root = criteriaQuery.from(Person.class);
            criteriaQuery.select(root);
            for (String key: filters.keySet()) {
                Predicate pr = builder.greaterThanOrEqualTo(root.get(key), filters.get(key).toString());
                criteriaQuery.where(pr);
            }
            Query<Person> query = session.createQuery(criteriaQuery);
            return query.setFirstResult(personRequest.getCurrentPage()).setMaxResults(5).getResultList();
        }
    }
}
