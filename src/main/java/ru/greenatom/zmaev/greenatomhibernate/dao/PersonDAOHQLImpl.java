package ru.greenatom.zmaev.greenatomhibernate.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import ru.greenatom.zmaev.greenatomhibernate.domain.entity.Person;
import ru.greenatom.zmaev.greenatomhibernate.domain.entity.PersonRequest;
import ru.greenatom.zmaev.greenatomhibernate.util.HibernateSessionFactoryUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component
public class PersonDAOHQLImpl implements PersonDAO {

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
        Map<String, Object> filters = personRequest.getCriteria();
        String hql = getHql(filters);
        Query<Person> res = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession()
                .createQuery(hql, Person.class);
        for (String key: filters.keySet()) {
            res.setParameter(key, filters.get(key));
        }
        res.setFirstResult(personRequest.getCurrentPage() * 5).setMaxResults(5);
        return res.list();
    }

    private String getHql(Map<String, Object> filters) {
        StringBuilder sb = new StringBuilder("FROM Person");
        if (!filters.isEmpty()) {
            sb.append(" WHERE ");
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                sb.append(entry.getKey()).append(String.format(" = :%s", entry.getKey()));
                sb.append(" AND ");
            }
            sb.delete(sb.length() - 4, sb.length());
        }
    return sb.toString();
    }
}
