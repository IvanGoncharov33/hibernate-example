package org.example.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, PK extends Serializable> {

    List<T> getAllEntities();

    T getEntityById(PK id);

    public void saveEntity(T entity);

    public void updateEntity(PK id, T updatedEntity);

    public void removeEntity(PK id);
}
