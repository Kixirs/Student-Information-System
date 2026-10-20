package observer;
import model.Student;
/**
 * Інтерфейс спостерігача (GoF Observer).
 * Усі класи, які хочуть отримувати сповіщення про зміни стану студента,
 * повинні реалізувати цей інтерфейс.
 */
public interface Observer {
    void update(Student student, double oldGrade, double newGrade);
}