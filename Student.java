import java.util.Objects;

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
        this.fullName = fullName;
        this.studentId = studentId;
        this.enrollmentYear = enrollmentYear;
        this.course = course;
        this.email = email;
        this.averageGrade = averageGrade;
        this.isBudget = isBudget;


        calculateAcademicStatus();
    }

    //Визначення степендії та бюджету на основі середнього балу.
    public void calculateAcademicStatus() {
        if(this.averageGrade < MINIMAL_SUCCESS_THRESHOLD) {
            this.deficit = MINIMAL_SUCCESS_THRESHOLD - this.averageGrade;
            this.scholarshipTier = "Мінімальна успішність";
        } 
        else if (this.averageGrade < SATISFACTORY_THRESHOLD) {
            this.deficit = SATISFACTORY_THRESHOLD - this.averageGrade;
            this.scholarshipTier = "Задовільний рівень";
        }
        else if (this.averageGrade < SUFFICIENT_THRESHOLD) {
            this.deficit = SUFFICIENT_THRESHOLD - this.averageGrade;
            this.scholarshipTier = "Достатній рівень";
        }
        else if (this.averageGrade < SCHOLARSHIP_THRESHOLD) {
            this.deficit = SCHOLARSHIP_THRESHOLD - this.averageGrade;
            this.scholarshipTier = "Нарахування стипендії";
        }
        else if (this.averageGrade < INCREASED_SCHOLARSHIP_THRESHOLD) {
            this.deficit = INCREASED_SCHOLARSHIP_THRESHOLD - this.averageGrade;
            this.scholarshipTier = "Підвищене нарахування";
        }
        else {
            this.deficit = 0.0;
            this.scholarshipTier = "Максимальне нарахування";
        }

        
        if(this.averageGrade >= SCHOLARSHIP_THRESHOLD) {
            this.isBudget = true;
            this.hasScholarship = true;
        }
    }

        public String toString() {

        String budgetString = isBudget ? "Так" : "Ні";
        String hasScholarshipString = hasScholarship ? "Так" : "Ні";

        return String.format(
            "[PH №%d] %s\n" +
            "├─ Курс: %d | Рік вступу: %d | Email: %s\n" +
            "├─ Середній бал: %.2f | Бюджет: %s | Стипендія: %s\n" +
            "└─ Статус: %s | Дефіцит балів: %.2f \n", 
            studentId, fullName, course, enrollmentYear, email,
            averageGrade, budgetString, hasScholarshipString, 
            scholarshipTier, deficit
        );
    }

    public boolean isBudget() {
        return isBudget;
    }
    public double averageGrade() {
        return averageGrade;
    }
}
