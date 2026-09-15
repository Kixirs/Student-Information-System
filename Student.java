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
                    boolean isBudget) {
        this.fullName = fullName;
        this.studentId = studentId;
        this.enrollmentYear = enrollmentYear;
        this.course = course;
        this.email = email;
        this.averageGrade = averageGrade;
        this.isBudget = isBudget;

        calculateAcademicStatus();
    }
}