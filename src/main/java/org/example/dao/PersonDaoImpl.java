package org.example.dao;

import org.example.entity.Person;
import org.example.utils.AppProperties;
import org.example.utils.JpaUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class PersonDaoImpl implements GenericDao<Person, Long> {

    AppProperties properties;

    public PersonDaoImpl(AppProperties properties) {
        this.properties = properties;
    }

    @Override
    public List<Person> getAllEntities() {
        Session session = JpaUtils.getSession(properties);
        TypedQuery<Person> query = session.createQuery("SELECT entity FROM Person AS entity ", Person.class);
        List<Person> personList = query.getResultList();
        session.close();
        return personList;
    }

    @Override
    public Person getEntityById(Long id) {
        Session session = JpaUtils.getSession(properties);
        Person person = session.find(Person.class, id);
        session.close();
        return person;
    }

    @Override
    public void saveEntity(Person entity) {
        Session session = JpaUtils.getSession(properties);
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            session.close();
    }

    @Override
    public void updateEntity(Long id, Person entity) {
        Session session = JpaUtils.getSession(properties);
        Transaction transaction = session.beginTransaction();
            Person updatedEntity = session.find(Person.class, id);
            if (entity != null) {
                if (entity.getName() != null && !entity.getName().isEmpty()){
                    updatedEntity.setName(entity.getName());
                }
                if (entity.getAge() > 0){
                    updatedEntity.setAge(entity.getAge());
                }
            }
            session.update(updatedEntity);
            transaction.commit();
            session.close();
    }


    @Override
    public void removeEntity(Long id) {
        Session session = JpaUtils.getSession(properties);
        Transaction transaction = session.beginTransaction();
        session.delete(session.find(Person.class, id));
        transaction.commit();
        session.close();
    }
}
