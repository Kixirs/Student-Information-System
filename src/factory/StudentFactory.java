package factory;

import exception.InvalidGradeException;
import exception.InvalidNameException;
import exception.InvalidCourseException;
import model.Student;
import model.BudgetStudent;
import model.ContractStudent;
import model.TargetStudent;

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
        // Замінюємо ось цей код boolean isBudget = (type == StudentType.BUDGET); на наступний
        return switch(type) {
            case BUDGET -> new BudgetStudent(
                                    fullName, 
                                    studentId, 
                                    enrollmentYear,
                                    course, 
                                    email, 
                                    averageGrade, 
                                    hasScholarship );
            
            case CONTRACT -> new ContractStudent(
                                    fullName, 
                                    studentId, 
                                    enrollmentYear,
                                    course, 
                                    email, 
                                    averageGrade );

            case TARGET -> new TargetStudent(
                                    fullName, 
                                    studentId, 
                                    enrollmentYear,
                                    course, 
                                    email, 
                                    averageGrade,
                                    hasScholarship );
        };
    }

        private static void validateName(String fullName) {
            if (fullName == null || fullName.trim().isEmpty() ||fullName.matches(".*\\d.*")) {
                System.out.println("[DEBUG] Знайдено цифри в ПІБ! Кидаю виняток...");
                throw new InvalidNameException("ПІБ не може містити цифри або бути порожнім.", fullName);
            }
        }

        private static void validateGrade(double averageGrade) {
            if (averageGrade < Student.ZERO || averageGrade > Student.HUNDRED) {
                System.out.println("[DEBUG] Бал поза межами 0-100! Кидаю виняток...");
                throw new InvalidGradeException("Середній бал має бути в діапазоні від 0.0 до 100.0.", averageGrade);
            }
        }

        private static void validateCourse(int course) {
            if (course < Student.MIN_COURSE || course > Student.MAX_COURSE) {
                System.out.println("[DEBUG] Такого курсу не існує! Кидаю виняток...");
                throw new InvalidCourseException("Курс має бути від 1 до 4", course);
            }
        }    
}         
   
