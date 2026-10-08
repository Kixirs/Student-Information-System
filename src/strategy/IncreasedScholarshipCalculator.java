package strategy;
import model.ScholarshipType;
import model.Student;

public class IncreasedScholarshipCalculator implements ScholarshipCalculator {

    private static final double INCREASE_COEFFICIENT = 1.5;   

    @Override
    public double calculateScholarship(Student student) {
        return ScholarshipType.BASIC.getAmount() * INCREASE_COEFFICIENT;
    }

    @Override
    public String getCalculatorType() {
        return "Студент отримує підвищену степендію";
    }

    @Override
    public boolean isApplicable(Student student) {
        return student.isBudget() && student.getAverageGrade() >= Student.SCHOLARSHIP_THRESHOLD;
    }
}