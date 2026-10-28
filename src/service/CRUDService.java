package service;

import model.BaseEntity;
import java.util.List;

public interface CRUDService<T extends BaseEntity> {
    T findById(long id);
    void save(T entity);
    boolean delete(long id);
    List<T> findAllSortedByName();
}