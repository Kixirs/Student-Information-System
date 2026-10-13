package model;

import java.util.Objects;
import exception.InvalidGradeException;
import exception.InvalidNameException;
import exception.InvalidCourseException;
import exception.StudentDomainException;
import strategy.DefaultScholarshipResolver;

import observer.Observer;  
import observer.Subject;
import java.util.ArrayList;
import java.util.List;


public class Student implements Subject {
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

    private double deficit = 0.0;
    private String scholarshipTier = "Відсутня";

    // Список спостерігачів.
    private List<Observer> observers = new ArrayList<>();

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
        validateCourse(course);


        this.fullName = fullName;
        this.studentId = studentId;
        this.enrollmentYear = enrollmentYear;
        this.course = course;
        this.email = email;
        this.averageGrade = averageGrade;
        this.isBudget = isBudget;

        // 1. Розрахунок статусу через Strategy (ЛР №4).
        this.scholarshipType = new DefaultScholarshipResolver().resolve(this);

        // 2. Визначаємо наявність стипендії на основі типу.
        this.hasScholarship = (this.scholarshipType != ScholarshipType.NONE);

        // 3. Розрахунок дефіциту та рівня для коректного toString()
        this.deficit = Math.max(0.0, 100.0 - this.averageGrade);
        this.scholarshipTier = this.scholarshipType.getDescription();
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

    private void validateCourse(int course) {
        if (course < 1 || course > 4) {
            System.out.println("[DEBUG] Такого курсу не існує! Кидаю виняток...");
            throw new InvalidCourseException("Курс має бути від 1 до 4", course);
        }
    }   

    // Реалізація методів з патерну Subject
    @Override
    public void attsch(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer); 
    }

    @Override 
    public void notifyObservers(double oldGrade, double newGrade) {
        for (Observer observer : observers) {
            observer.update(this, oldGrade, newGrade);
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

    public void setEnrollmentYear(
        int enrollmentYear) {
            this.enrollmentYear = enrollmentYear; 
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        if (course < 1 || course > 4) {
            throw new InvalidCourseException("Курс має бути від 1 до 4", course);
        }
        this.course = course;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public double getAverageGrade() { 
        return averageGrade; 
    }
    public void setAverageGrade(double averageGrade) {
        validateGrade(averageGrade);
        double oldGrade = this.averageGrade;
        this.averageGrade = averageGrade;
        // При зміні балу оновленя статус стипендії
        this.scholarshipType = new DefaultScholarshipResolver().resolve(this);
        this.hasScholarship = (this.scholarshipType != ScholarshipType.NONE);
        this.deficit = Math.max(0.0, 100.0 - this.averageGrade);
        this.scholarshipTier = this.scholarshipType.getDescription();
        
        notifyObservers(oldGrade, this.averageGrade); 
    }
    
    public boolean hasScholarship() { 
        return hasScholarship; 
    }

    public boolean isBudget() { 
        return isBudget; 
    }

    public void setBudget(boolean budget) { 
        this.isBudget = budget; 
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
            "└─ Тип стипендії: %s\n",
            studentId, fullName, course, enrollmentYear, email,
            averageGrade, budgetString, hasScholarshipString, 
            scholarshipTier, deficit, scholarshipType != null ? scholarshipType.getDescription() : "Невизначено"
        );
    }
}