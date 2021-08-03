package org.example.dao;

import org.example.entity.CoreEntity;
import org.example.entity.Person;
import org.example.utils.AppProperties;
import org.example.utils.JpaUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class PersonDaoImplEm implements GenericDao<Person, Long> {

    private final EntityManagerFactory entityManagerFactory;

    public PersonDaoImplEm(AppProperties properties) {
        this.entityManagerFactory = JpaUtils.getEntityManagerFactory("Person", properties);
    }

    @Override
    public List<Person> getAllEntities() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Person> query =
                entityManager.createQuery("SELECT entity FROM Person AS entity ", Person.class);
        return query.getResultList();
    }

    @Override
    public Person getEntityById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Person.class, id);
    }

    @Override
    public void saveEntity(Person entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public void updateEntity(Long id, Person entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Person updatedEntity = entityManager.find(Person.class, id);
            updatedEntity.setAge(entity.getAge());
            entityManager.merge(updatedEntity);
            transaction.begin();
        } catch (Exception e) {
            transaction.rollback();
        }
    }


    @Override
    public void removeEntity(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            CoreEntity removedEntity = entityManager.find(CoreEntity.class, id);
            entityManager.remove(removedEntity);
            transaction.begin();
        } catch (Exception e) {
            transaction.rollback();
        }
    }
}
