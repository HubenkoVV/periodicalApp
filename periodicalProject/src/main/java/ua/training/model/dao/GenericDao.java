package ua.training.model.dao;

import java.util.List;

public interface GenericDao<T> extends AutoCloseable{
    int create (T entity);
    T findById(int id);
    List<T> findAll();
    void update(T entity, int id);
    void delete(int id);
    void close();
    void setAutoCommit(boolean state);
    void rollback();
}
