package ru.greenatom.zmaev.greenatomhibernate.dao;

import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import ru.greenatom.zmaev.greenatomhibernate.domain.entity.Person;
import ru.greenatom.zmaev.greenatomhibernate.domain.entity.PersonRequest;

import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.Session;
import ru.greenatom.zmaev.greenatomhibernate.util.HibernateSessionFactoryUtil;

@Component
public class PersonDAOImpl implements PersonDAO {

    @Override
    public void create(Person person) {
        if (person.getCreationTime() == null) {
            person.setCreationTime(LocalDateTime.now());
        }
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            System.out.println(person);
            session.persist(person);
            tx.commit();
        }
    }

    @Override
    public void update(Long id, Person person) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.merge(person);
            tx1.commit();
        }
    }
 
    @Override
    public void delete(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Person person = findById(id);
        session.remove(person);
        tx1.commit();
        session.close();
    }

    @Override
    public Person findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Person.class, id);
    }

    @Override
    public List<Person> findAll(PersonRequest personRequest) {
        return HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery("FROM Person", Person.class)
                .list();
    }
}
