package factory;

import exception.InvalidGradeException;
import exception.InvalidNameException;
import model.Student;

public class StudentFactory {
    /**
     * Фабрика для створення об'єктів Student.
     */
    public static Student createStudent(
            StudentType type,
            String fullName,
            int studentId,
            int enrollmentYear,
            int course,
            String email,
            double averageGrade,
            boolean hasAcademicDebts,
            boolean isSociallyProtected) {

        // 1. Валідація.
        validateName(fullName);
        validateGrade(averageGrade);
        validateCourse(course);

        // 2. Реалістичний розрахунок стипендії.
        boolean isEligibleForFunding = (type == StudentType.BUDGET || type == StudentType.TARGET);
        boolean hasNoDebts = !hasAcademicDebts;

        // Пільгова категорія має нижчий прохідний бал (75.0 замість 85.0)
        double requiredGrade = isSociallyProtected ? Student.SUFFICIENT_THRESHOLD : Student.SCHOLARSHIP_THRESHOLD;
        boolean meetsAcademicRequirement = averageGrade >= requiredGrade;

        boolean hasScholarship = isEligibleForFunding && hasNoDebts && meetsAcademicRequirement;
        boolean isBudget = (type == StudentType.BUDGET);

        // 3. Створення об'єкта. 
        return new Student(
            fullName,
            studentId,
            enrollmentYear,
            course,
            email,
            averageGrade,
            isBudget,
            hasScholarship
        );
    }

        private static void validateName(String fullName) {
            if (fullName == null || fullName.trim().isEmpty() ||fullName.matches(".*\\d.*")) {
                System.out.println("[DEBUG] Знайдено цифри в ПІБ! Кидаю виняток...");
                throw new InvalidNameException("ПІБ не може містити цифри або бути порожнім.", fullName);
            }
        }

        private static void validateGrade(double averageGrade) {
            if (averageGrade < 0.0 || averageGrade > 100.0) {
                System.out.println("[DEBUG] Бал поза межами 0-100! Кидаю виняток...");
                throw new InvalidGradeException("Середній бал має бути в діапазоні від 0.0 до 100.0.", averageGrade);
            }
        }

        private static void validateCourse(int course) {
            if (course < 1 || course > 4) {
                System.out.println("[DEBUG] Такого курсу не існує! Кидаю виняток...");
                throw new InvalidCourseException("Курс має бути від 1 до 4", course);
            }
        }             
   
}