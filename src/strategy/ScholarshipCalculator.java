package strategy;
import model.Student;

public interface ScholarshipCalculator {
    double calculateScholarship(Student student);
    String getCalculatorType();

// Дефолтний метод. Перевіряє чи є студент "бюджетником".
    default boolean isApplicable(Student student) {
        return student.isBudget();
    }
}