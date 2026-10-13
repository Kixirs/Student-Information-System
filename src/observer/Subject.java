package observer;
/**
 * Інтерфейс суб'єкта (GoF Subject).
 * Визначає контракт для об'єктів, які можуть мати підписників.
 */
public interface Subject {
    void attsch(Observer observer); // (додавання) нового спостерігача
    void detach(Observer observer); // (відписка) спостерігача
    void notifyObservers(double oldGrade, double newGrade); // Оповіщення всіх зареєстрованих спостерігачів про зміну стану.
}