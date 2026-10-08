package strategy;
import model.Student;
import model.ScholarshipType;

public class BasicScholarshipCalculator implements ScholarshipCalculator {
    @Override
    public double calculateScholarship(Student student) {
        return ScholarshipType.BASIC.getAmount(); 
    }

    @Override
    public String getCalculatorType() {
        return "Студент отримує базову стипендію";
    }
}