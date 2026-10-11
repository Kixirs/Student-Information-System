package observer;
/**
 * Інтерфейс спостерігача (GoF Observer).
 * Усі класи, які хочуть отримувати сповіщення про зміни стану студента,
 * повинні реалізувати цей інтерфейс.
 */
public interface Observer {
    void update(Student student);
}