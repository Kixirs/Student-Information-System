package template;

import model.Student;

public class DetailedStudentReport extends StudentReport {
    
    @Override
    protected void printHeader() {
        System.out.println("===== ДЕТАЛЬНИЙ ЗВІТ =====");
        System.out.println("Дата генерації: " + new java.util.Date());
    }

    @Override
    protected void printFooter() {
        System.out.println("===== КІНЕЦЬ ЗВІТУ =====");
    }

    @Override
    protected void printAdditionalInfo(Student student) {
        System.out.println("Рік вступу: " + student.getEnrollmentYear());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Бюджет: " + (student.isBudget() ? "Так" : "Ні"));
        System.out.println("Стипендія: " + (student.hasScholarship() ? "Так" : "Ні"));
    }
}