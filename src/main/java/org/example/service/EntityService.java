package org.example.service;

import java.util.List;

public interface EntityService<T, PK> {

    public List<T> getAllEntities();

    public T getEntityById(PK id);

    public void saveEntity(T entity);

    public void updateEntity(PK id, T entity);

    public void removeEntity(PK id);
}
