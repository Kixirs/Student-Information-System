package model;

import java.util.Objects;
import exception.InvalidGradeException;
import exception.InvalidNameException;
import exception.StudentDomainException;


public class Student {
    public static final double MINIMAL_SUCCESS_THRESHOLD = 30.0;
    public static final double SATISFACTORY_THRESHOLD = 50.0;
    public static final double SUFFICIENT_THRESHOLD = 70.0;
    public static final double SCHOLARSHIP_THRESHOLD = 85.0;
    public static final double INCREASED_SCHOLARSHIP_THRESHOLD = 90.0;

    private String fullName;
    private int studentId;
    private int enrollmentYear;
    private int course;
    private String email;
    private double averageGrade;
    private boolean isBudget;
    private boolean hasScholarship;
    private ScholarshipType scholarshipType;

    private double deficit;
    private String scholarshipTier;

    public Student(String fullName, 
                    int studentId, 
                    int enrollmentYear, 
                    int course,
                    String email, 
                    double averageGrade,
                    boolean isBudget,
                    boolean hasScholarship) {
        
        validateName(fullName);
        validateGrade(averageGrade);

        this.fullName = fullName;
        this.studentId = studentId;
        this.enrollmentYear = enrollmentYear;
        this.course = course;
        this.email = email;
        this.averageGrade = averageGrade;
        this.isBudget = isBudget;
        this.hasScholarship = hasScholarship;

        calculateAcademicStatus();
    }

    private void validateName(String fullName) {
        if (fullName == null || fullName.matches(".*\\d.*")) {
            System.out.println("[DEBUG] Знайдено цифри в ПІБ! Кидаю виняток...");
            throw new InvalidNameException("ПІБ не може містити цифри або бути порожнім.", fullName);
        }
    }

    private void validateGrade(double averageGrade) {
        if (averageGrade < 0 || averageGrade > 100) {
            System.out.println("[DEBUG] Бал поза межами 0-100! Кидаю виняток...");
            throw new InvalidGradeException("Середній бал має бути в діапазоні від 0.0 до 100.0.", averageGrade);
        }
    }

    private void calculateAcademicStatus() {
        if(this.averageGrade >= SCHOLARSHIP_THRESHOLD) {
            this.isBudget = true;
            this.hasScholarship = true;
        }
    }


    // Гетери та сетери
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        validateName(fullName);
        this.fullName = fullName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(int enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        if (course < 1 || course > 4) {
            throw new IllegalArgumentException("Курс має бути від 1 до 4");
        }
        this.course = course;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHasScholarship(boolean hasScholarship) {
        this.hasScholarship = hasScholarship;
    }

    public ScholarshipType getScholarshipType() {
        return scholarshipType;
    }

    @Override
    public String toString() { 
        String budgetString = isBudget ? "Так" : "Ні";
        String hasScholarshipString = hasScholarship ? "Так" : "Ні";

        return String.format(
            "[PH №%d] %s\n" +
            "├─ Курс: %d | Рік вступу: %d | Email: %s\n" +
            "├─ Середній бал: %.2f | Бюджет: %s | Стипендія: %s\n" +
            "├─ Статус: %s | Дефіцит балів: %.2f \n" +
            "└─ Тип стипендії: %s\n" +
            studentId, fullName, course, enrollmentYear, email,
            averageGrade, budgetString, hasScholarshipString, 
            scholarshipTier, deficit, scholarshipType.getDescription()
        );
    }

    public boolean hasScholarship() {
        return hasScholarship;
    }

    public boolean isBudget() {
        return isBudget;
    }

    public void setBudget(boolean budget) {
        isBudget = budget;
    }

    public double averageGrade() {
        return averageGrade;
    }

        public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        validateGrade(averageGrade);
        this.averageGrade = averageGrade;
        calculateAcademicStatus();
    }
}