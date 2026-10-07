package strategy;

import model.Student;

public class BasicScholarshipCalculator implements ScholarshipCalculator {
    @Override
    public double calculateScholarship(Student student) {
        if (!student.hasScholarship()) {
            return 0.0;
        }
        return 1000.0;
    }

    @Override
    public String getCalculatorType() {
        return "Студент отримує базову степендію";
    }
}