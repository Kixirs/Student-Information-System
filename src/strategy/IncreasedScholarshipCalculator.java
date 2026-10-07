package strategy;

import model.Student;

public class IncreasedScholarshipCalculator implements ScholarshipCalculator {

    private static final double BASE_AMOUNT = 1000.0;
    private static final double INCREASE_COEFFICIENT = 1.5;   

    @Override
    public double calculateScholarship(Student student) {
        if (!student.hasScholarship()) {
            return 0.0;
        }
        return BASE_AMOUNT * INCREASE_COEFFICIENT;
    }

    @Override
    public String getCalculatorType() {
        return "Студент отримує підвищену степендію";
    }

    @Override
    public boolean isApplicable(Student student) {
        return student.isBudget() && student.averageGrade() >= 85.0;;
    }
}