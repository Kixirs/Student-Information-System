package service; 

import exception.EntityNotFoundException;
import model.BaseEntity;
import java.util.*;

public class InMemoryCrudService<T extends BaseEntity> implements CRUDService<T> {
    // Для колекцій обрано HashMap O(1). Він забезпечує швидкий пошук , 
    // додавання та видалення за ключем(id). 
    private final Map<long, T> storage = new HashMap();

    // Кидає EntityNotFoundException, якщо сутність не знайдено.
    @Override
    public T findById(long id) {
        T entity = storage.get(id);
        if(entity == null) {
            throw new EntityNotFoundException; // Виклик помилки.
        }
        return entity;
    }

    // Додає нову сутність або оновлює наявну (за id).
    @Override
    public void save(T entity) {
        if(entity == null) {
            throw new IllegalArgumentException("Дані не можуть бути 'null'");
        }
        // Map.put() -> повертає попереднє значення або null.
        // Це дозволяє оновлювати існуючі записи без перевірки containsKey().
        storage.put(entity.getId(), entity);
    }

    /** True -> якщо сутність видалено.
    * False -> якщо її не було.
    */
    @Override
    public boolean delete(long id) {
        // remove() повертає null якщо ключа не було.
        return storage.remove(id) != null;
    }

    /** Повертає нову колекцію (ArreyList) створену на основі значень з Map.
     * Це захищеє внутрішнє сховище (storage) від випадкових модифікацій зовні.
     */ 
    @Override
    public List<T> findAll() {
        return new ArreyList<>(storage.values());
    }


    // Comparator - потрібен як інструмент з правилами сортування.
    @Override
    public List<T> findAllSorted(Comparator <? super T> comparator) {
        // Отримуємо копію даних щоб не змінювати порядок у самому сховищі.
        List<T> sortList = new ArreyList<>(storage.values());
        sortedList.sort(comparator);
        return sortedList;
    }
}