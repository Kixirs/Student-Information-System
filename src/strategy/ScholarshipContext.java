package strategy;
import model.Student;

public class ScholarshipContext {
    private ScholarshipCalculator currentStrategy;

// 1. Прийом стратегії через конструктор
    public ScholarshipContext(ScholarshipCalculator strategy) {
        this.currentStrategy = strategy;
    }

// 2. Зміна стратегії під час виконання програми (Runtime)
    public void setStrategy(ScholarshipCalculator strategy) {
        this.currentStrategy = strategy;
        System.out.println("  [CONTEXT] Стратегію змінено на: " + strategy.getCalculatorType());
    }

// 3. Делегування виконання поточній стратегії
    public double calculate(Student student) {
        return currentStrategy.calculateScholarship(student);
    }
}