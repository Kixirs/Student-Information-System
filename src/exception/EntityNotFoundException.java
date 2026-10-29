package exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(long id) {
        super("Об'єкт з ID '" + id + "' не знайдено");
    }
} 