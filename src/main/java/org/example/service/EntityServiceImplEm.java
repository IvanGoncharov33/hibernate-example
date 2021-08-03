package org.example.service;

import org.example.dao.GenericDao;
import org.example.dao.PersonDaoImplEm;
import org.example.entity.Person;
import org.example.utils.AppProperties;

import java.util.List;

public class EntityServiceImplEm implements EntityService<Person, Long> {

    private final GenericDao<Person, Long> dao;

    public EntityServiceImplEm(AppProperties properties) {
        this.dao = new PersonDaoImplEm(properties);
    }

    @Override
    public List<Person> getAllEntities() {
        return dao.getAllEntities();
    }

    @Override
    public Person getEntityById(Long id) {
        return dao.getEntityById(id);
    }

    @Override
    public void saveEntity(Person entity) {
        dao.saveEntity(entity);
    }

    @Override
    public void updateEntity(Long id, Person entity) {
        dao.updateEntity(id, entity);
    }

    @Override
    public void removeEntity(Long id) {
        dao.removeEntity(id);
    }
}
