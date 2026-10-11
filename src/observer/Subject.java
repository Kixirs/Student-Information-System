package observer;
/**
 * Інтерфейс суб'єкта (GoF Subject).
 * Визначає контракт для об'єктів, які можуть мати підписників.
 */
public interface Subject {
    void attsch(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}